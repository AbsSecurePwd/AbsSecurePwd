
function handle(resultData){
	let s = jQuery("#genre");
	
	for(let i = 0; i < resultData.length; i++){
		
		let rowHTML = "";
        rowHTML += "<div>" +
	        '<a href="movielist.html?genre=' + resultData[i]['name'] + '">' +resultData[i]["name"]+
	        "</a></div>";

        s.append(rowHTML);
		
	}
	
	let t = jQuery("#title");
	for(let i = 0; i < 10; i++){
		let rowHTML = "";
        rowHTML +=
            "<div>" +
            '<a href="movielist.html?title=' + i + '">' + i
            +
            "</div>";

        t.append(rowHTML);
	}
	
	chr = '';
	for (var i = 65; i <= 90; i++) {
	    chr = String.fromCharCode(i);
	    let rowHTML = "";
        rowHTML +=
            "<div>" +
            '<a href="movielist.html?title=' + chr + '">' + chr
            +
            "</a></div>";

        t.append(rowHTML);
	}
	
}

jQuery.ajax({
	dataType: "json",
	method: "GET",
	url: "api/browse",
	success: (resultData) => handle(resultData)
});












