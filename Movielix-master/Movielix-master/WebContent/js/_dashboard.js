function handleSubmitResult(resultData){
//	alert("called");
	console.log(resultData);
	$('div.total-title').text(resultData["message"]);
}

function handleResult(resultData){
	let table = jQuery("#TableInfo");
	for (let i = 0; i < resultData.length; i++) {	
		let h = "<h3>" + resultData[i]["tableName"];
		h+= "</h3><table class='table table-striped'><thead><tr><th>Column Name</th><th>Type</th></tr></thead><tbody>"; //table header
		for(let j = 0; j < resultData[i]["columns"].length;j++){
			h += "</tr>"
			h += "<th>" + resultData[i]["columns"][j]["columnName"] + "</th>";
	        h += "<th>" + resultData[i]["columns"][j]["type"] + "</th>"; 
	        h += "</tr>"
		}
		h+= "</tbody></table>"; //table end
		table.append(h);
	}
}

function starSubmitForm(){
	
	$.post(
	        "api/dashboard",
	        // Serialize the login form to the data sent by POST request
	        $("#star_form").serialize(),
	        (resultDataString) => handleSubmitResult(resultDataString)
	    );
	document.getElementById('star_form').reset();

}

function movieSubmitForm(){
	$.post(
        "api/dashboard",
        // Serialize the login form to the data sent by POST request
        $("#movie_form").serialize(),
        (resultDataString) => handleSubmitResult(resultDataString)
    );
	document.getElementById('movie_form').reset();
}


jQuery.ajax({
    dataType: "json",  // Setting return data type
    method: "GET",// Setting request method
    url: "api/dashboard", // Setting request url, 
    success: (resultData) => handleResult(resultData) // 
});