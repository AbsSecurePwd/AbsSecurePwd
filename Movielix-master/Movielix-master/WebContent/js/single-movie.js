var movieId;

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function handleCartInfo(cartEvent) {
	cartEvent.preventDefault();
	
    $.get(
        "api/cart",
        // Serialize the cart form to the data sent by POST request
        $("#cart").serialize(),
        (resultDataString) => handleCartArray(resultDataString)
        );
}

function handleCartArray(resultDataString) {
	alert("added to cart");
}

function handleResult(resultData){
	console.log(resultData)
	movieId = resultData["id"];
	let table = jQuery("#container");
	
	let i = jQuery("[name='id']") 
	i.val(movieId);
	
	table.append("<div>Title: " + resultData["title"] + "</div>");
	table.append("<div>Movie ID: " + resultData["id"] + "</div>");
	table.append("<div>Released In: " + resultData["year"] + "</div>");
	table.append("<div>Directed By: " + resultData["director"] + "</div>");
	table.append("<div>Genres: " + resultData["genres"] + "</div>");
	table.append("<div>Starring:</div>");
	for(let i =0; i < resultData["actorsArray"].length/2; i++){
		table.append("<div><a href='single-star.html?id=" + resultData["actorsArray"][i]["starId"] + "'>" + resultData["actorsArray"][i]["name"] + "</a></div>");
	}
   
}

//Bind the submit action of the form to a event handler function
$("#cart").submit((event) => handleCartInfo(event));

//get movie
let id = getUrlVars()["id"];
jQuery.ajax({
    dataType: "json",  // Setting return data type
    method: "GET",// Setting request method
    url: "api/single-movie?id=" + id, // Setting request url, which is mapped by StarsServlet in Stars.java
    success: (resultData) => handleResult(resultData) // Setting callback function to handle data returned successfully by the SingleStarServlet
});