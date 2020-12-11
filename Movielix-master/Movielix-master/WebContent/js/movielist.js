//0 for sorted ascending, for descending
//1 for sorted descending
let sortByTitle = 0; 
let sortByRating = 0;




var limit=10; //default limit is 10;
var offset=0; //start at 0;
var end=false; //bool for end of results

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function changeLimit(){
	console.log("changed");
	var x = document.getElementById("resultsnumber");
	limit=x.value;
	console.log(x.value);
	console.log(limit);
	makeQuery(0);
}

function prevPage(){
	if((offset-limit < 0) == false){
		offset -= limit;
		$('#movie_table tbody').empty(); //clear the table
		makeQuery(0);
	}else{
		alert("cnat go back");
	}
}

function nextPage(){
	$('#movie_table tbody').empty(); //clear the table
	offset += limit;
	makeQuery(0);
}


function sortTable(n){
	$('#movie_table tbody').empty(); //clear the table
	makeQuery(n);
}

function makeQuery(n){
	$('#movie_table tbody').empty(); //clear the table
	let sortBy = "&sortBy="

		
	if(n == 0){ //sort by title
		sortBy+= "title"
		if(sortByTitle == 0){
			sortBy+="&ascending=asc"
		}else{
			sortBy+="&ascending=desc"
		}
		sortByTitle = !sortByTitle
	}
	else{ //sort by rating
		sortBy+= "rating"
		if(sortByRating == 0){
			sortBy+="&ascending=asc"
		}else{
			sortBy+="&ascending=desc"
		}
		sortByRating = !sortByRating
	}


	let url = "api/movielist?";

	//from browse
	let genre = getUrlVars()["genre"];
	let title = getUrlVars()["title"];

	let query = getUrlVars()["query"];
	
	//from search, rn will assume that all are filled
	let year = getUrlVars()["year"];
	let director = getUrlVars()["director"];
//	let star = getUrlVars()["star"];


	console.log(query);
	if(genre != null){ //browsing by genre
		url += "genre=" + genre;
	}
	else if(query != null){
		
		url += "query=" + query;
	}
	else{ //could be combination of title, year, director, and star
		url += "title=" + title;
		if(year != null){ //assumes that all are filled
			url += "&";
			url += "year=" + year + "&";
			url += "director=" + director; //+ "&";
//			url += "star=" + star;
		}
	}

	url+="&limit=" + limit;
	url+="&offset="+ offset;

	url+= sortBy;
	console.log(url);
	jQuery.ajax({
	    dataType: "json",  // Setting return data type
	    method: "GET",// Setting request method
	    url: url, // Setting request url, which is mapped by StarsServlet in Stars.java
	    success: (resultData) => handleResult(resultData) // Setting callback function to handle data returned successfully by the SingleStarServlet
	});
}


function handleResult(resultData){
	result=resultData;
	console.log(resultData)
	
	let table = jQuery("#movie_table_body");
	$('#movie_table tbody').empty(); //clear the table
    // Iterate through resultData, no more than 10 entries
    for (let i = 0; i < resultData.length; i++) {
    	
    	
        let rowHTML = "";
        rowHTML += "<tr>";
        rowHTML += "<th>" + resultData[i]["id"] + "</th>";
        rowHTML += "<th><a href='single-movie.html?id=" + resultData[i]["id"] + "'>" +resultData[i]["title"] + "</a></th>"
        rowHTML += "<th>" + resultData[i]["year"] + "</th>";
        rowHTML += "<th>" + resultData[i]["director"] + "</th>";
        
        rowHTML += "<th>";
        for(let j = 0; j < resultData[i]["genres"].length; j++){
        	
        	rowHTML += "<div>";
            rowHTML += '<a href="movielist.html?genre=' + resultData[i]["genres"][j]["name"] + '">' + resultData[i]["genres"][j]["name"];
            rowHTML += "</a></div>";
        }
        rowHTML += "</th>";
        
        rowHTML += "<th>";
        //add the actors
        for(let j = 0; j < resultData[i]["actors"].length; j++){
        	rowHTML += "<div>"
        	rowHTML += '<a href="single-star.html?id=' + resultData[i]["actors"][j]["id"] + '">' + resultData[i]["actors"][j]["name"]
        	rowHTML += "</a></div>"
        }
        rowHTML += "</th>";
        
        
        rowHTML += "<th>" + resultData[i]["rating"] + "</th>";
        
        //add to cart button
        rowHTML += '<th><button type="button" onclick="addToCart(' + i + ')" >Add to cart</button></th>'
        
        rowHTML += "</tr>";

        // Append the row created to the table body, which will refresh the page
        table.append(rowHTML);
    }
}

function handleCartArray(resultDataString) {
	alert("added to cart");
}

function addToCart(i){
    $.get(
        "api/cart",
        // Serialize the cart form to the data sent by POST request
        "id="+result[i]["id"],
        (resultDataString) => handleCartArray(resultDataString)
       );
	
}

let result = "";
makeQuery(0);






