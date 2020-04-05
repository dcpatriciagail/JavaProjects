$(document).ready(function () {


    loadUsers();

    function loadUsers() {

    var UsercontentRows = $('#UsercontentRows');


    $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/users',
    success: function (data, status) {
        $.each(data, function (index, user) {
            var uid = user.userId;
            var email = user.email;
            var firstName = user.firstName;
            var lastName = user.lastName;
            var password = user.password;
            var role = user.role;

            var row = '<tr>';
                row += '<td>' + lastName + '</td>';
                row += '<td>' + firstName + '</td>';
                row += '<td>' + email + '</td>';
                row += '<td>' + role + '</td>';
                row += '<td><a href="editUser.html?x='+user.userId+'">Edit</a></td>'
                row += '</tr>';

            UsercontentRows.append(row);

        });
    },
    error: function () {
        $('#errorMessages')
            .append($('<li>')
                .attr({ class: 'list-group-item list-group-item-danger' })
                .text('Error calling web service.  Please try again later.'));
    }
    });
    }

})
