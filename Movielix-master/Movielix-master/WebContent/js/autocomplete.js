
var resultDict = {};



/*
 * This function is called by the library when it needs to lookup a query.
 * 
 * The parameter query is the query string.
 * The doneCallback is a callback function provided by the library, after you get the
 *   suggestion list from AJAX, you need to call this function to let the library know.
 */
function handleLookup(query, doneCallback) {
//	console.log("autocomplete initiated")
	
	
//	console.log(resultDict); 
	var myLength = $("#autocomplete").val().length;
	if(myLength < 3){
//		console.log("Too Short")
	}else{
		console.log("initiating autocomplete query");
		if (query in resultDict){ // check past query results first
			handleLookupAjaxSuccess(resultDict[query], query, doneCallback)
			console.log("suggestion was retrieved from cache")
		}else{
		// sending the HTTP GET request to the Java Servlet endpoint hero-suggestion
		// with the query data
			console.log("sending AJAX request to backend Java Servlet")
			jQuery.ajax({
				dataType: "json",
				"method": "GET",
				// generate the request url from the query.
				// escape the query string to avoid errors caused by special characters 
				"url": "movie-suggestion?query=" + escape(query),
				"success": function(data) {
					// pass the data, query, and doneCallback function into the success handler
					handleLookupAjaxSuccess(data, query, doneCallback) 
				},
				"error": function(errorData) {
					console.log("lookup ajax error")
					console.log(errorData)
				}
			})
		}
	
	}
}


/*
 * This function is used to handle the ajax success callback function.
 * It is called by our own code upon the success of the AJAX request
 * 
 * data is the JSON data string you get from your Java Servlet
 * 
 */
function handleLookupAjaxSuccess(data, query, doneCallback) {

	console.log(data) //log suggestion list 

	
	
	resultDict[query] = data;//cache the result into a global variable
	
	

	// call the callback function provided by the autocomplete library
	// add "{suggestions: jsonData}" to satisfy the library response format according to
	//   the "Response Format" section in documentation
	doneCallback( { suggestions: data } );
}


function handleSelectSuggestion(suggestion) {
//	console.log("you select " + suggestion["value"] + " with ID " + suggestion["data"]["movieID"])
	window.location.replace("single-movie.html?id=" + suggestion["data"]["movieID"]);
}


/*
 * This statement binds the autocomplete library with the input box element and 
 *   sets necessary parameters of the library.
 * 
 * The library documentation can be find here: 
 *   https://github.com/devbridge/jQuery-Autocomplete
 *   https://www.devbridge.com/sourcery/components/jquery-autocomplete/
 * 
 */
// $('#autocomplete') is to find element by the ID "autocomplete"
$('#autocomplete').autocomplete({
	// documentation of the lookup function can be found under the "Custom lookup function" section
    lookup: function (query, doneCallback) {
    		handleLookup(query, doneCallback)
    },
    onSelect: function(suggestion) {
    		handleSelectSuggestion(suggestion)
    },
    deferRequestBy: 300,// set delay time
});


/*
 * do normal full text search if no suggestion is selected 
 */
function handleNormalSearch(query) {
	window.location.replace("movielist.html?query=" + query);
}

// bind pressing enter key to a handler function
$('#autocomplete').keypress(function(event) {
	// keyCode 13 is the enter key
	if (event.keyCode == 13) {
		// pass the value of the input box to the handler function
		handleNormalSearch($('#autocomplete').val())
	}
})


$('#search').click(function(){ //bind on click for search button
	handleNormalSearch($('#autocomplete').val())
});
