var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; //January is 0!
var yyyy = today.getFullYear();
if (dd < 10) {
  dd = '0' + dd;
}
if (mm < 10) {
  mm = '0' + mm;
}
today = mm + '/' + dd + '/' + yyyy;
$(document).ready(function () {
    loadMakes();
    $('#save-button').click(function(event){
      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/admin/addmake',
        data: JSON.stringify({
            makeName: $('#make-value').val()
        }),
        headers: {
          'Accept' : 'application/json',
          'Content-Type' : 'application/json'
        },
        'dataType' : 'json',
        success: function(data, status) {
          $('#errorMessages').empty();
          $('#make-value').val('');
          loadMakes(); // THIS WILL MAKE THE NEW CONTACT AVAILABLE
        },
        error: function() {
          $('#errorMessages')
              .append($('<li>')
              .attr({class: 'list-group-item list-group-item-danger'})
              .text('Error calling web services. Please try again later.'))
        }
      })
    });
    // Loads the Makes
function loadMakes() {
clearMakesTable();
var contentRows = $('#contentRows');
$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/admin/makes',
    success: function (data, status) {
        $.each(data, function (index, make) {
            var makeId = make.makeId;
            var makeName = make.makeName;
            var row = '<tr>';
                row += '<td>' + makeName + '</td>';
                row += '<td>' + today + '</td>';
                row += '<td>' + "BobSmith" + '</td>';
                row += '</tr>';
            contentRows.append(row);
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
//prevents existing makes from loading after adding a new one
function clearMakesTable() {
  $('#contentRows').empty();
}
}
) //End of loadFeaturedVehicles
