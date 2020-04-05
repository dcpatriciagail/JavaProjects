$(document).ready(function(){

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var user = getUrlVars()["x"];

    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/cardealership/admin/edituser/' + user,
      success: function(data, status){

        $('#firstName').val(data.firstName);
        $('#lastName').val(data.lastName);
        $('#email').val(data.email);
        $('#role').val(data.role);
        $('#password').val(data.password);
        $('#confirmPassword').val(data.password);
      },

      error: function() {
        alert("could not load user info");
      }


    })

      $('#save-edit-button').click(function addUser(){

        if($('#password').val() != $('#confirmPassword').val()){
            alert("Make sure the password match!");
            return null;
        }

        $.ajax({
          type: 'PUT',
          url: 'http://localhost:8080/cardealership/admin/edituser/' + user,
          data: JSON.stringify({
            userId: user,
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
            alert("User Edited");

            $('#firstName').val('');
            $('#lastName').val('');
            $('#email').val('');
            $('#role').val('');
            $('#password').val('');
          },
          error: function() {
            alert("Something Went Wrong");
          }
        })
      })
    });
