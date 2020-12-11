


function submit(submitEvent) {
    console.log("submit login form");
    submitEvent.preventDefault();
    
    
    var title = document.getElementById("title").value.trim();
    var year = document.getElementById("year").value.trim();
    var director = document.getElementById("director").value.trim();
    var star = document.getElementById("star").value.trim();

    if(star== ""){
	    var link = "movielist.html?";
	    link += "title=" + title.replace(/\s/g, "+") + "&";
	    link += "year=" + year + "&";
	    link += "director=" + director.replace(/\s/g, "+") + "&";
	    link += "star=" + star.replace(/\s/g, "+"); 
	    
	    console.log(link);
	    window.location.replace(link);
    }else{ //searching for star
    	var link = "single-star.html?";
    	link+= "name=" + star.replace(/\s/g, "+"); 
    	console.log(link);
    	window.location.replace(link);
    }
    
    

   
}

// Bind the submit action of the form to a handler function
$("#search_form").submit((event) => submit(event));