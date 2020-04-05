$(document).ready(function(){


  $('#save-add-button').click(function addUser(){

    if($('#password').val() != $('#confirmPassword').val()){
        alert("Make sure the password match!");
        return null;
    }

    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/cardealership/admin/adduser',
      data: JSON.stringify({
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        email: $('#email').val(),
        role: $('#role').val(),
        password: $('#password').val()
      }),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      'contentType': 'application/json',
      success: function() {
        alert("User Added");

        $('#firstName').val('');
        $('#lastName').val('');
        $('#email').val('');
        $('#role').val('');
        $('#password').val('');
        $('#confirmPassword').val('');
      },
      error: function() {
        alert("Something Went Wrong");
      }
    })
  })
});
