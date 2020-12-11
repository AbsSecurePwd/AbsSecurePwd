
function handleCheckoutResult(resultDataString) {
	console.log(resultDataString)
	window.location.replace("payment.html");
}



function checkout(){
	
	
	let r = "";
	r += result[0] + "=" + document.getElementsByName("quantity")[0].value;
	for(let i = 1; i < result.length; i++){
		r += "&" + result[i] + "=" + document.getElementsByName("quantity")[i].value
	}
		
	$.post(
        "api/storecart",
        // Serialize the login form to the data sent by POST request
        r,
        (resultDataString) => handleCheckoutResult(resultDataString)
    );
}


function handleResult(resultData){
	result = resultData;
	
	for(let i =0; i < resultData.length; i++){
		console.log(resultData[i])
	}
	
	let table = jQuery("#cart_table_body");
	for(let i = 0; i < resultData.length;i++){
		let rowHTML = "";
		rowHTML += "<tr>";		
		rowHTML += "<th>" + resultData[i] + "</th>";
		rowHTML += "<th><input type='number' min='0'  value=1 name='quantity'></th>";
		rowHTML += "</tr>";
        table.append(rowHTML);
	}

}


let result = "";

jQuery.ajax({
    dataType: "json",  // Setting return data type
    method: "GET",// Setting request method
    url: "api/getcart", // Setting request url, which is mapped by StarsServlet in Stars.java
    success: (resultData) => handleResult(resultData), // Setting callback function to handle data returned successfully by the SingleStarServlet
});
