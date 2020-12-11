
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

let id = getUrlVars()["id"];
let table = jQuery("#bbody");
table.append("Sale ID: " +id);


function handleResult(resultData){
	console.log(resultData)
	let order_table = jQuery("#order_table_body");
	for(let i = 0; i < resultData.length;i++){
		let rowHTML = "";
		rowHTML += "<tr>";		
		rowHTML += "<th>" + resultData[i]["title"] + "</th>";
		rowHTML += "<th>"+ resultData[i]["quantity"]+ "</th>";
		rowHTML += "</tr>";
        order_table.append(rowHTML);
	}

}



jQuery.ajax({
    dataType: "json",  
    method: "GET",
    url: "api/storecart", 
    success: (resultData) => handleResult(resultData)
});