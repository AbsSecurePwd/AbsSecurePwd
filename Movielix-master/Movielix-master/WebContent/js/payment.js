
function handlePaymentResult(resultDataString) {
	console.log(resultDataString);
	console.log(resultDataString.substring(0,resultDataString.length/2))
    resultDataJson = JSON.parse(resultDataString.substring(0,resultDataString.length/2));
//	console.log(resultDataJson["status"])
//	console.log(resultDataJson["salesId"])
//    // If login succeeds, it will redirect the user to index.html
    if (resultDataJson["status"] === "success") {
        window.location.replace("confirmation.html?id=" + resultDataJson["salesId"] );
    } else {
////        // If login fails, the web page will display 
////        // error messages on <div> with id "login_error_message"
//        console.log("show error message");
        console.log(resultDataJson["message"]);
        $("#payment_error_message").text(resultDataJson["message"]);
        document.getElementById('payment_form').reset();
    }
}


function submitPaymentForm(formSubmitEvent) {
    formSubmitEvent.preventDefault();
    $.post(
        "api/payment",
        // Serialize the login form to the data sent by POST request
        $("#payment_form").serialize(),
        (resultDataString) => handlePaymentResult(resultDataString)
    );
}

// Bind the submit action of the form to a handler function
$("#payment_form").submit((event) => submitPaymentForm(event));

