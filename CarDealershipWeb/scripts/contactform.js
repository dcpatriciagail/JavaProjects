$(document).ready(function () {

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var vinForMessage = getUrlVars()["vin"];
    if(vinForMessage === undefined) {
    }
    else{
    $('#message').val('Contacting in regards to: ' + vinForMessage);
    };
});

function validateForm() {
    var customerName = document.forms["contactForm"]["name"].value;
    var customerEmail = document.forms["contactForm"]["email"].value;
    var customerPhone = document.forms["contactForm"]["phone"].value;
    var emailPattern = /\S+@\S+\.\S+/;
    var successMsg = document.getElementById("formSuccess");
    

    if (customerName == "") {
        alert("Name must be filled out");
        return false;
    }
    // This checks to see if email is in a valid format
    else if (emailPattern.test(customerEmail) == false) {
            alert("Email is invalid. Please enter a valid email address. Ex. someone@something.domain");
            return false;
    }
    // This checks to see if string contains letters or if phone number is not 10 digits, note* ignores formatting so (555)-555-5555 would be fine.
    else if ((/[a-z]/i.test(customerPhone) == true || customerPhone.match(/\d/g).length!==10)) {
        alert("Phone number is invalid.  Please enter a valid 10 digit phone number");
        return false;
    }
    else
    {
        successMsg.style.display = "block";
        return false;
    }
    
}


// SEND CONTACT INFO VIA POST.

$('#submit-button').click(function (event) {
        
    // if we made it here, there are no errors so make the ajax call
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/home/contact',
        data: JSON.stringify({
            firstName: $('#fname').val(),
            lastName: $('#lname').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            message: $('#message').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        'contentType': 'application/json; charset=utf-8',
        success: function () {
            // clear errorMessages
            $('#errorMessages').empty();
            // Clear the form and reload the table
            $('#fname').val('');
            $('#lname').val('');
            $('#email').val('');
            $('#phone').val('');
            $('#message').val('');
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    });
});



