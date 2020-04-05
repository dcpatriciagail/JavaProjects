$(document).ready(function () {

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var idOfVehicle = getUrlVars()["vehicleId"];


    var vehicleVehicleId = "";
    var vehicleYear = "";
    var vehicleTrans = "";
    var vehicleMileage = "";
    var vehicleColor = "";
    var vehicleInterior = "";
    var vehicleBodyType = "";
    var vehicleVin = "";
    var vehicleSalesPrice = "";
    var vehicleMsrp = "";
    var vehicleDescription = "";
    var vehicleUsed = "";
    var vehicleFeatured = "";
    var vehicleSpecial = "";
    var vehicleInStock = "";
    var vehiclePicUrl = "";
    var vehicleYear = "";

    var vehicleModelId = "";
    var vehicleModelName = "";
    
    var makeMakeId = "";
    var makeMakeName = "";
    
    var userUserId = "";
    var userEmail = "";
    var userFirstName = "";
    var userLastName = "";
    var userPassword = "";
    var userRole = "";
    var userPhone = "";
    
    var purchasePrice = "";

    


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cardealership/getuser/1',
        success: function (data) {
                userUserId = data.userId;
                userEmail = data.email;
                userFirstName = data.firstName;
                userLastName = data.lastName;
                userPassword = data.password;
                userRole = data.role;
            },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    })


//Start of Vehicle Description
    
        var contentRows = $('#contentRows');
            contentRows.empty();
        
 
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/cardealership/inventory/details/'+idOfVehicle,
            success: function (data) {
                    vehicleVehicleId = data.vehicleId;
                    vehicleModelId = data.model.modelId;
                    vehicleModelName = data.model.modelName;
                    makeMakeId = data.model.make.makeId;
                    makeMakeName = data.model.make.makeName;
                    vehicleYear = data.year;
                    vehiclePicUrl = data.picURL;
                    vehicleSalesPrice = data.salesPrice;
                    vehicleBodyType = data.bodyType;
                    vehicleMsrp = data.msrp;
                    vehicleTrans = data.transmission;
                    vehicleInterior = data.interior;
                    vehicleMileage = data.mileage;
                    vehicleVin = data.vin;
                    vehicleColor = data.color;
                    vehicleDescription = data.description;
                    vehicleUsed = data.used;
                    vehicleFeatured = data.featured;
                    vehicleSpecial = data.special;
                    vehicleInStock = data.inStock;
        
                    
                    var row = '';
                    
                    row+= '<div class="content-box">';
                    row+= '<h4>'+vehicleYear+' '+makeMakeName+' '+vehicleModelName+'</h4>';
                    row+= '<div class="col-lg-3 col-md-3">';
                    row+= '<img src="images/'+vehiclePicUrl+'" alt="" style="width:70%;">';
                    row+= '</div>';
                    
                    row+= '<div class="col-lg-3 col-md-3">';
                    row+= '<table>';
                    row+= '<tr>';
                    row+= '<th>Body Style:</th>';
                    row+= '<td>'+vehicleBodyType+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>Trans:</th>';
                    row+= '<td>'+vehicleTrans+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>Color:</th>';
                    row+= '<td>'+vehicleColor+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>Description:</th>';
                    row+= '<td>'+vehicleDescription+'</td>';
                    row+= '</tr>';
                    row+= '</table>';
                    row+= '</div>';
                    
                    row+= '<div class="col-lg-3 col-md-3">';
                    row+= '<table>';
                    row+= '<tr>';
                    row+= '<th>Interior:</th>';
                    row+= '<td>'+vehicleInterior+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>Mileage:</th>';
                    row+= '<td>'+vehicleMileage+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>VIN:</th>';
                    row+= '<td>'+vehicleVin+'</td>';
                    row+= '</tr>';
                    row+= '</table>';
                    row+= '</div>';
                    
                    row+= '<div class="col-lg-3 col-md-3">';
                    row+= '<table>';
                    row+= '<tr>';
                    row+= '<th>Sales Price:</th>';
                    row+= '<td>'+vehicleSalesPrice+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th>MSRP:</th>';
                    row+= '<td>'+vehicleMsrp+'</td>';
                    row+= '</tr>';
                    row+= '<tr>';
                    row+= '<th></th>';
                    row+= '<td></td>';
                    row+= '</tr>';
                    row+= '</table>';
                    row+= '</div>';
    
                    
                    row+= '</div> <!-- END OF ITEM  -->';

                    row+= '<h3>Sales Information</h3>';
  
                    contentRows.append(row);
                },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service.  Please try again later.'));
            }
        });
    
    



    function validateForm() {
        var customerName = document.forms["contactForm"]["name"].value;
        var successMsg = document.getElementById("formSuccess");
        
    
        if (customerName == "") {
            alert("Name must be filled out");
            return false;
        }
        if(purchasePrice == null || purchasePrice == "") {
            alert("Purchase price must be filled out");
        }
        else
        {
            successMsg.style.display = "block";
            return false;
        }
        
    }
    // SEND CONTACT INFO VIA POST.

$('#purchase-button').click(function (event) {
        
    // if we made it here, there are no errors so make the ajax call
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/cardealership/sales/purchase/'+idOfVehicle,
        data: JSON.stringify({
            purchasePrice: $('#purchasePrice').val(),
            purchaseType: $('#purchaseType :selected').text(),
            customerName: $('#name').val(),
            vehicle: {
                vehicleId: vehicleVehicleId,
                model: {
                    modelId: vehicleModelId,
                    modelName: vehicleModelName,
                    make: {
                        makeId: makeMakeId,
                        makeName: makeMakeName,
                    }
                },
                year: vehicleYear,
                transmission: vehicleTrans,
                mileage: vehicleMileage,
                color: vehicleColor,
                interior: vehicleInterior,
                bodyType: vehicleBodyType, 
                salesPrice: vehicleSalesPrice,
                msrp: vehicleMsrp,
                description: vehicleDescription,
                used: vehicleUsed,
                featured: vehicleFeatured,
                special: vehicleSpecial,
                inStock: vehicleInStock,
                picURL: vehiclePicUrl,
                vin: vehicleVin
            },
            user: {
                userId: userUserId,
                firstName: userFirstName,
                lastName: userLastName,
                email: userEmail,
                password: userPassword,
                phone: userPhone,
                role: userRole
            }
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        'contentType': 'application/json; charset=utf-8',
        success: function () {
            alert("Congrats car has been purchased");
            // clear errorMessages
            $('#errorMessages').empty();
            // Clear the form and reload the table
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    });
})




})
    
    
    