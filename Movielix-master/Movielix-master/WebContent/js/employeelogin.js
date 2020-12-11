function handleLoginResult(resultDataString) {
	console.log(resultDataString)
    resultDataJson = JSON.parse(resultDataString);
    if (resultDataJson["status"] === "success") {
        window.location.replace("_dashboard.html");
    } else {
        $("#login_error_message").text(resultDataJson["message"]);
        document.getElementById('login_form').reset();
    }
}


function submitLoginForm(formSubmitEvent) {
	let f = $("#login_form").serialize() + '&employee=true'
	console.log(f);
	
    formSubmitEvent.preventDefault();
    $.post(
        "api/login",
        // Serialize the login form to the data sent by POST request
        f,
        (resultDataString) => handleLoginResult(resultDataString)
    );
}

// Bind the submit action of the form to a handler function
$("#login_form").submit((event) => submitLoginForm(event));