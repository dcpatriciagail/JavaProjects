$(document).ready(function () {


    loadFeaturedVehicles();


    // Loads the Featured Vehicles
function loadFeaturedVehicles() {

var contentRows = $('#contentRows');


$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/cardealership/home/index',
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

            
            var row = '';
            
                row += '<div class="col-lg-3 col-md-3" style="margin-bottom:15px">';
                row += '<a href="vehicle-details.html?vehicleId='+vehicleId+'"><img src="images/'+ picUrl + '" alt=""></a>';
                row += '<div class="centered featured">';
                row += '<span>'+year + ' ' + makeName + ' ' + modelName + '</span><br>';
                row += '<span>' +salesPrice+'</span>';
                row += '</div>'
                row += '</div>'


            // if (poster != null) {
            //     row += '<div class="wrapper">';
            //     row += '<div class="containerx">';
            //     row += '<div class="content">';

            //     row += '<div class="content-overlay"></div>'
            //     row += '<img class="content-image" src="' + poster + '" style="width:150px; height:241px; padding:5px;">';
            //     row += '<div class="content-details fadeIn-bottom">';
            //     row += '<h3 class="content-title">' + title + '</h3>';
            //     row += '<p class="content-text">';
            //     row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> MOVIE INFO | </a>  ';
            //     row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
            //     row += '</div>';

            //     row += '</div>';
            //     row += '</div>';
            //     row += '</div>';
            // }
            // else {
            //     row += '<div class="wrapper">';
            //     row += '<div class="containerx">';
            //     row += '<div class="content">';

            //     row += '<div class="content-overlay"></div>'
            //     row += '<img class="content-image" src="http://i68.tinypic.com/ml4m0g.png" style="width:150px; height:241px; padding:5px;">';
            //     row += '<div class="content-details fadeIn-bottom">';
            //     row += '<h3 class="content-title">' + title + '</h3>';
            //     row += '<p class="content-text">';
            //     row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> EDIT | </a>  ';
            //     row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
            //     row += '</div>';

            //     row += '</div>';
            //     row += '</div>';
            //     row += '</div>';
            // }
            // row += '<td>' + title + '</td>';
            // row += '<td>' + userRating + '</td>';
            // row += '<td>' + id + '</td>';
            // row += '<td><a onclick="showEditForm(' + id + ')">Edit</a></td>';
            // row += '<td><a onclick="deleteMovie(' + id + ')">Delete</a></td>';
            // row += '</tr>';
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




}
) //End of loadFeaturedVehicles