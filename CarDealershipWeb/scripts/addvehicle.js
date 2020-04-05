var makeObj = {};
var modelObj = {};
$(document).ready(function() {

  // Get all Makes
  loadMakes();

  // Submitting the form
  $('#submit-button').click(function (event) {

    var makeName = $("#make :selected").text();
    var makeId = makeObj[makeName];

    var modelName = $("#model :selected").text();
    var modelId = modelObj[modelName];

    var type = $("#type :selected").text();
    var used = false;
    if (type == "New") {
      used = false;
    } else {
      used = true;
    }

    var year = $("#year").val();

    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/cardealership/admin/addvehicle',
      data: JSON.stringify({
        model: {
          make: {
            makeId: makeId,
            makeName: makeName
          },
          modelId: modelId,
          modelName: modelName
        },
        used: used,
        bodyType: $("#bodyStyle :selected").text(),
        year: $("#year").val(),
        transmission: $("#transmission :selected").text(),
        color: $("#color :selected").text(),
        interior: $("#interior :selected").text(),
        mileage: $("#mileage").val(),
        vin: $("#VIN").val(),
        msrp: $("#msrp").val(),
        salesPrice: $("#salesPrice").val(),
        description: $("#description").val(),
        picURL: $("#photo").val(),
        featured: false,
        special: false,
        inStock: true
      }),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'JSON',
      'contentType': 'application/json; charset=utf-8',
      success: function () {
        $("#errorMessages").empty();

        $("#year").val("");
        $("#VIN").val("");
        $("#mileage").val("");
        $("#salesPrice").val("");
        $("#msrp").val("");
        $("#description").val("");
      },
      error: function () {
        $('#errorMessages')
        .append($('<li>')
        .attr({ class: 'list-group-item list-group-item-danger' })
        .text('Error calling web service.  Please try again later.'));
      }
    });
  });
});

function loadMakes() {

  var makeDropdown = $('#make');

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/admin/makes',
    success: function (returnedMakes) {
      // like an enhanced for loop
      $.each(returnedMakes, function (index, make) {
        var makeId = make.makeId;
        var makeName = make.makeName;

        // Creates key/value pair for make name & make ID
        makeObj[makeName] = makeId;

        makeDropdown.append('<option value="' + makeName + '">' + makeName + '</option>');
      });
    },
    error: function () {
      $('#errorMessages')
      .append($('<li>')
      .attr({ class: 'list-group-item list-group-item-danger' })
      .text('Error calling web service. Please try again later.'));
    }
  });
}

// Load Models when Make is selected
function loadModels() {

  var makeSelection = $('#make :selected').text();
  var modelDropdown = $('#model');

  modelDropdown.empty();

  var makeSuperId = 0;
  if(makeSelection in makeObj) {
    makeSuperId = makeObj[makeSelection];
  }

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/sales/index/models/' + makeSuperId,
    success: function (returnedModels) {
      // like an enhanced for loop
      $.each(returnedModels, function (index, model) {
        var modelId = model.modelId;
        var modelName = model.modelName;

        // Creates key/value pair for make name & make ID
        modelObj[modelName] = modelId;

        modelDropdown.append('<option value="' + modelName + '">' + modelName + '</option>');
      });
    },
    error: function () {
      $('#errorMessages')
      .append($('<li>')
      .attr({ class: 'list-group-item list-group-item-danger' })
      .text('Error calling web service. Please try again later.'));
    }
  });
}

// Validating form
function validateForm() {
  var vin = $("#VIN").val();
  var successMsg = document.getElementById("formSuccess");


  if (vin.length != 16) {
    alert("VIN must be 16 characters.");
    return false;
  }
  else {
    successMsg.style.display = "block";
    return false;
  }

}
