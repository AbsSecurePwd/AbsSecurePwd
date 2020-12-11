
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}




function handleResult(resultData){
	console.log(resultData)
	let table = jQuery("#container");
	table.append("<div>Name: " + resultData["name"] + "</div>");
	table.append("<div>Born in: " + resultData["birthYear"] + "</div>");
	table.append("<div>Staring in:</div>");
	for(let i = 0; i < resultData["movies"].length/2; i++){
		table.append("<div>" + '<a href="single-movie.html?id=' + resultData["movies"][i]["movieId"] + '">' +resultData["movies"][i]["movieTitle"] + "</a></div>");
	}
    
}
let name=getUrlVars()["name"];
let id = getUrlVars()["id"];
jQuery.ajax({
    dataType: "json",  // Setting return data type
    method: "GET",// Setting request method
    url: "api/single-star?id=" + id+"&name="+name, // Setting request url, which is mapped by StarsServlet in Stars.java
    success: (resultData) => handleResult(resultData) // Setting callback function to handle data returned successfully by the SingleStarServlet
});