$(document).ready(function () {

    var contentRows = $('#contentRows');
    $('#contentRows').empty();

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var idOfVehicle = getUrlVars()["vehicleId"];


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cardealership/inventory/details/'+idOfVehicle,
        success: function (data) {
                var vehicleId = data.vehicleId;
                var modelId = data.model.modelId;
                var modelName = data.model.modelName;
                var makeId = data.model.make.makeId;
                var makeName = data.model.make.makeName;
                var year = data.year;
                var picUrl = data.picURL;
                var salesPrice = data.salesPrice;
                var bodyStyle = data.bodyType;
                var msrp = data.msrp;
                var trans = data.transmission;
                var interior = data.interior;
                var mileage = data.mileage;
                var vin = data.vin;
                var color = data.color;
                var vehicleDescription = data.description;
    
                
                var row = '';
                
                row+= '<div class="content-box">';
                row+= '<h4>'+year+' '+makeName+' '+modelName+'</h4>';
                row+= '<div class="col-lg-3 col-md-3">';
                row+= '<img src="images/'+picUrl+'" alt="" style="width:70%;">';
                row+= '</div>';
                
                row+= '<div class="col-lg-3 col-md-3">';
                row+= '<table>';
                row+= '<tr>';
                row+= '<th>Body Style:</th>';
                row+= '<td>'+bodyStyle+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th>Trans:</th>';
                row+= '<td>'+trans+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th>Color:</th>';
                row+= '<td>'+color+'</td>';
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
                row+= '<td>'+interior+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th>Mileage:</th>';
                row+= '<td>'+mileage+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th>VIN:</th>';
                row+= '<td>'+vin+'</td>';
                row+= '</tr>';
                row+= '</table>';
                row+= '</div>';
                
                row+= '<div class="col-lg-3 col-md-3">';
                row+= '<table>';
                row+= '<tr>';
                row+= '<th>Sales Price:</th>';
                row+= '<td>$'+salesPrice+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th>MSRP:</th>';
                row+= '<td>$'+msrp+'</td>';
                row+= '</tr>';
                row+= '<tr>';
                row+= '<th></th>';
                row+= '<td><a href="contact.html?vin='+vin+'"><button type="button" class="btn btn-primary">Contact us</button></a></td>';
                row+= '</tr>';
                row+= '</table>';
                row+= '</div>';


         
                // row+= '<a href="contact.html"><button type="button" class="btn btn-primary">Contact us</button></a>' ;

                row+= '</div>';
                row+= '</div>';
                
                row+= '</div> <!-- END OF ITEM  -->';
                contentRows.append(row);
            },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    });

});