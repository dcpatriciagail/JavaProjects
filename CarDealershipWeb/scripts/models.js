var obj = {};

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



    loadModels();
    //load makes for the drop-down selection
    loadMakes();

    //



    $('#save-button').click(function(event){

      $('#test').append($('#make-selection :selected').text());

      var makeSelection = $('#make-selection :selected').text();
      var makeSuperId = 0;
      if(makeSelection in obj) {
        makeSuperId = obj[makeSelection];
      }

      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/admin/addmodel',
        data: JSON.stringify({
            modelName: $('#model-value').val(),
            make: { makeId: makeSuperId,
            makeName: $('#make-selection :selected').text() }


        }),
        headers: {
          'Accept' : 'application/json',
          'Content-Type' : 'application/json'
        },
        'dataType' : 'json',
        'contentType': 'application/json; charset=utf-8',
        success: function(data, status) {
          $('#errorMessages').empty();
          $('#model-value').val('');


          loadModels(); // THIS WILL MAKE THE NEW CONTACT AVAILABLE
        },
        error: function() {
          $('#errorMessages')
              .append($('<li>')
              .attr({class: 'list-group-item list-group-item-danger'})
              .text('Error calling web services. Please try again later.'))
        }
      })

    });


    // Loads the Models
function loadModels() {
clearModelsTable();
var contentRows = $('#contentRows');


$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/admin/models',
    success: function (data, status) {
        $.each(data, function (index, model) {
            var modelId = model.modelId;
            var modelName = model.modelName;
            var makeName = model.make.makeName


            var row = '<tr>';
                row += '<td>' + makeName + '</td>';
                row += '<td>' + modelName + '</td>';
                row += '<td>' + today + '</td>';
                row += '<td>' + "Bob Smith" + '</td>';
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


// Loads the Makes
function loadMakes() {

var makeDropdown = $('#make-selection');


$.ajax({
type: 'GET',
url: 'http://localhost:8080/cardealership/admin/makes',
success: function (data, status) {
    $.each(data, function (index, make) {
        var makeId = make.makeId;
        var makeName = make.makeName;

         obj[makeName] = makeId;


       makeDropdown.append('<option value="' + make + '">' + make.makeName + '</option>');

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

//prevents existing models from loading after adding a new one
function clearModelsTable() {
  $('#contentRows').empty();
}



}
) //End of loadFeaturedVehicles
