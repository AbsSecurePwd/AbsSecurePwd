
function handleLoginResult(resultDataString) {
	console.log(resultDataString)
    resultDataJson = JSON.parse(resultDataString);
    // If login succeeds, it will redirect the user to index.html
    if (resultDataJson["status"] === "success") {
        window.location.replace("browse.html");
    } else {
        // If login fails, the web page will display 
        // error messages on <div> with id "login_error_message"
        console.log("show error message");
        console.log(resultDataJson["message"]);
        $("#login_error_message").text(resultDataJson["message"]);
        document.getElementById('login_form').reset();
    }
}


function submitLoginForm(formSubmitEvent) {
    console.log("submit login form");
    console.log(document.getElementsByName("email")[0].value);
    console.log(document.getElementsByName("password")[0].value);
    
    formSubmitEvent.preventDefault();
    window.location.replace("browse.html");
    console.log($("#login_form").serialize());
    $.post(
    	
        "api/login",
        // Serialize the login form to the data sent by POST request
        $("#login_form").serialize(),
        (resultDataString) => handleLoginResult(resultDataString)
    );
}

// Bind the submit action of the form to a handler function
$("#login_form").submit((event) => submitLoginForm(event));

