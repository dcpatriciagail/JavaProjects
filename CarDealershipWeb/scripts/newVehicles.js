$(document).ready(function () {



// Loads the New Vehicles
$('#search-button').click(function (event) {
var contentRows = $('#contentRows');
    contentRows.empty();


$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/inventory/new?makeOrModel='+$('#search-value').val()+'&minYear='+$('#min-year-selection :selected').val()+'&maxYear='+$('#max-year-selection :selected').val()+'&minPrice='+$('#min-price-selection :selected').val()+'&maxPrice='+$('#max-price-selection :selected').val(),
    success: function (data, status) {
        $.each(data, function (index, vehicle) {
            var vehicleId = vehicle.vehicleId;
            var modelId = vehicle.model.modelId;
            var modelName = vehicle.model.modelName;
            var makeId = vehicle.model.make.makeId;
            var makeName = vehicle.model.make.makeName;
            var year = vehicle.year;
            var picUrl = vehicle.picURL;
            var salesPrice = vehicle.salesPrice;
            var bodyStyle = vehicle.bodyType;
            var msrp = vehicle.msrp;
            var trans = vehicle.transmission;
            var interior = vehicle.interior;
            var mileage = vehicle.mileage;
            var vin = vehicle.vin;
            var color = vehicle.color;


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
            row+= '<td>'+salesPrice+'</td>';
            row+= '</tr>';
            row+= '<tr>';
            row+= '<th>MSRP:</th>';
            row+= '<td>'+msrp+'</td>';
            row+= '</tr>';
            row+= '<tr>';
            row+= '<th></th>';
            row+= '<td><a href="vehicle-details.html?vehicleId='+vehicleId+'"><button type="button" class="btn btn-primary">Details</button></a></td>';
            row+= '</tr>';
            row+= '</table>';
            row+= '</div>';

            row+= '</div> <!-- END OF ITEM  -->';
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

    )


});
