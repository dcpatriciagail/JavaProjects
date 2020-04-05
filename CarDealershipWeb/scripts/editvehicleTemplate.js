var makeObj = {};
var modelObj = {};
$(document).ready(function() {
  loadVehicle();
  // Submitting the form
  $('#submit-button').click(function (event) {
    var makeName = $("#make :selected").text();
    var makeId = makeObj[makeName];
    var modelName = $("#model :selected").text();
    var modelId = modelObj[modelName];
    var type = $("#type :selected").text();
    var used = false;
    if (type === "New") {
      used = false;
    } else {
      used = true;
    }
    var featured = $("#featured").is(":checked");
    var year = $("#year").val();
    $.ajax({
      type: 'PUT',
      url: 'http://localhost:8080/cardealership/admin/editvehicle/' + id,
      data: JSON.stringify({
        vehicleId: id,
        model: {
          make: {
            makeId: makeId,
            makeName: makeName
          },
          modelId: modelId,
          modelName: modelName
        },
        used: used,
        bodyType: $("#bodyType :selected").text(),
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
        featured: featured,
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
        $("#featured").prop('checked', false);
        window.location = "admin.html";
      },
      error: function () {
        $('#errorMessages')
        .append($('<li>')
        .attr({ class: 'list-group-item list-group-item-danger' })
        .text('Error calling web service.  Please try again later.'));
      }
    });
  });
  // Delete the vehicle
  $('#delete-button').click(function (event) {
    var makeName = $("#make :selected").text();
    var makeId = makeObj[makeName];
    var modelName = $("#model :selected").text();
    var modelId = modelObj[modelName];
    var type = $("#type :selected").text();
    var used = false;
    if (type === "New") {
      used = true;
    } else {
      used = false;
    }
    var featured = $("#featured").is(":checked");
    var year = $("#year").val();
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/cardealership/admin/editvehicle/' + id,
      success: function (data, status) {
        $("#errorMessages").empty();
        $("#year").val("");
        $("#VIN").val("");
        $("#mileage").val("");
        $("#salesPrice").val("");
        $("#msrp").val("");
        $("#description").val("");
        $("#featured").val("");
      }
    });
  });
});
// Gets ID for vehicle
function getUrlVars() {
       var vars = {};
       var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
           vars[key] = value;
       });
       return vars;
}
var id = getUrlVars()["id"];

function loadVehicle() {
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/admin/editvehicle/' + id,
    success: function(vehicle, status) {
      var used = vehicle.used;
      var type;
      if (used === true) {
        type = "Used";
      } else {
        type = "New";
      }
      var makeName = vehicle.model.make.makeName;
      makeObj[makeName] = vehicle.model.make.makeId;
      var modelName = vehicle.model.modelName;
      modelObj[modelName] = vehicle.model.modelId;
      $("#make").append('<option value="' + makeName + '">' + makeName + '</option>');
      $("#make").prop(makeName, true);
      $("#type").prop(type, true);
      $("#year").val(vehicle.year);
      $("#color").prop(vehicle.color, true);
      $("#mileage").val(vehicle.mileage);
      $("#msrp").val(vehicle.msrp);
      $("#model").append('<option value="' + modelName + '">' + modelName + '</option>');
      $("#model").prop(modelName, true);
      $("#bodyType").val(vehicle.bodyType);
      $("#transmission").prop(vehicle.transmission, true);
      $("#interior").prop(vehicle.interior, true);
      $("#VIN").val(vehicle.vin);
      $("#salesPrice").val(vehicle.salesPrice);
      $("#description").val(vehicle.description);
      $("#photo").val(vehicle.picURL);
      loadMakes();
    },
    error: function () {
      $('#errorMessages')
      .append($('<li>')
      .attr({ class: 'list-group-item list-group-item-danger' })
      .text('Error calling web service. Please try again later.'));
    }
  });
}
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
        if (!makeName in makeObj) {
          // Creates key/value pair for make name & make ID
          makeObj[makeName] = makeId;
          makeDropdown.append('<option value="' + makeName + '">' + makeName + '</option>');
        }
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
  if (vin.length !== 16) {
    alert("VIN must be 16 characters.");
    return false;
  }
  else {
    successMsg.style.display = "block";
    return false;
  }
}
