
$(document).ready(function () {

    loadUserNames();

    $('#search-button').click(function (event) {
      var contentRows = $('#newTableBody');
      contentRows.empty();



      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/reports/sales',
        data: JSON.stringify({
            userName: $('#user-selection :selected').val(),
            fromDate: $('#from-search-value').val(),
            toDate: $('#to-search-value').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        'contentType': 'application/json; charset=utf-8',
        success: function(namesArray){
            $.each(namesArray, function(index,report) {

                var row = '';

                row+= '<tr>';
                row+= '<td>'+report.userName+'</td>';
                row+= '<td>$'+report.totalSales+'</td>';
                row+= '<td>'+report.totalVehicles+'</td>';
                row+= '</tr>';

                contentRows.append(row);
            });
        },
        error: function() {
            $('#errorMessages')
                    .append('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error loading userNames');
        }
    });

    });

});

function loadUserNames() {
    $('#errorMessages').empty();
    var userDropdown = $('#user-selection');

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/reports/sales',
        data: JSON.stringify({
            userName: $('#user-selection :selected').val(),
            fromDate: $('#from-search-value').val(),
            toDate: $('#to-search-value').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        'contentType': 'application/json; charset=utf-8',
        success: function(namesArray){
            $.each(namesArray, function(index,report) {
                userDropdown.append('<option value="' + report.userName + '">' + report.userName + '</option>');
            });
        },
        error: function() {
            $('#errorMessages')
                    .append('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error loading userNames');
        }
    });

    $( function() {
        $( ".date-format" ).datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    });



}
