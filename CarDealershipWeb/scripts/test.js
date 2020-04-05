$(document).ready(function () {

    loadMovies();
    hideEditForm();
    $('#urlError').hide();

    // Add Button onclick handler
    $('#add-button').click(function (event) {
        
        
        // check for errors and display any that we have
        // pass the input associated with the add form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }

        // if we made it here, there are no errors so make the ajax call
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/movies',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#datepicker').val(),
                mpaaRating: $('#add-mpaaRating').val(),
                directorsName: $('#add-directorsName').val(),
                studio: $('#add-studio').val(),
                userRating: $('#add-userRating').val(),
                poster: $('#add-poster').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            'contentType': 'application/json; charset=utf-8',
            success: function () {
                // clear errorMessages
                $('#errorMessages').empty();
                // Clear the form and reload the table
                $('#add-title').val('');
                $('#datepicker').val('');
                $('#add-mpaaRating').val('');
                $('#add-directorsName').val('');
                $('#add-studio').val('');
                $('#add-userRating').val('');
                $('#add-poster').val('');
                loadMovies();
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service.  Please try again later.'));
            }
        });
});

    
    function checkURL() {
        var url = $('#add-poster').val();
        $('#urlError').hide();
        if(url==null) {
            return true;
        }
        var result = url.match(/\.(jpeg|jpg|gif|png)$/) != null;
        if(!result){
            $('#urlError').show();
        }
        return result;
    }
    






    // Update Button onclick handler
    $('#edit-button').click(function (event) {

        // check for errors and display any that we have
        // pass the input associated with the edit form to the validation function
        var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-form').find('input'));

        

        // if we have errors, bail out by returning false
        if (haveValidationErrors) {
            return false;
        }
        

        // if we get to here, there were no errors, so make the Ajax call
       
        
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/api/movies/edit/' + $('#edit-movie-id').val() + '?rating=' + $('#edit-userRating').val(),
            data: JSON.stringify({
                
                userRating: $('#edit-userRating').val()
            }),
            
            
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            'contentType': 'application/json; charset=utf-8',
            success: function () {
                
                // clear errorMessages
                $('#errorMessages').empty();
                hideEditForm();
                loadMovies();
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service.  Please try again later.'));
            }
        })
    });
});

function loadMovies() {
    // we need to clear the previous content so we don't append to it
    clearMovieTable();

    // grab the the tbody element that will hold the rows of movie information
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/movies',
        success: function (data, status) {
            $.each(data, function (index, movie) {
                var poster = movie.poster;
                var title = movie.title;
                var userRating = movie.userRating;
                var id = movie.movieId;

                // var row = '<tr>';
                var row = '';
                // if(poster!=null){
                //     row += '<td>' + '<img src="' + poster + '" style="width:150px; height:241px;">' + '</td>';
                // }
                // else {
                //     row += '<td>' + '<img src="http://i68.tinypic.com/ml4m0g.png" style="width:150px; height:241px;">' + '</td>';
                // }
                //                     <div class="hvrbox">
                // 	<img src="img/photos/photo1.jpg" alt="Mountains" class="hvrbox-layer_bottom">
                // 	<div class="hvrbox-layer_top hvrbox-layer_scale">
                // 		<div class="hvrbox-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitor ligula porttitor, lacinia sapien non.</div>
                // 	</div>
                // </div>
                if (poster != null) {
                    row += '<div class="wrapper">';
                    row += '<div class="containerx">';
                    row += '<div class="content">';

                    row += '<div class="content-overlay"></div>'
                    row += '<img class="content-image" src="' + poster + '" style="width:150px; height:241px; padding:5px;">';
                    row += '<div class="content-details fadeIn-bottom">';
                    row += '<h3 class="content-title">' + title + '</h3>';
                    row += '<p class="content-text">';
                    row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> MOVIE INFO | </a>  ';
                    row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
                    row += '</div>';

                    row += '</div>';
                    row += '</div>';
                    row += '</div>';
                }
                else {
                    row += '<div class="wrapper">';
                    row += '<div class="containerx">';
                    row += '<div class="content">';

                    row += '<div class="content-overlay"></div>'
                    row += '<img class="content-image" src="http://i68.tinypic.com/ml4m0g.png" style="width:150px; height:241px; padding:5px;">';
                    row += '<div class="content-details fadeIn-bottom">';
                    row += '<h3 class="content-title">' + title + '</h3>';
                    row += '<p class="content-text">';
                    row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> EDIT | </a>  ';
                    row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
                    row += '</div>';

                    row += '</div>';
                    row += '</div>';
                    row += '</div>';
                }
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

//DATE PICKER
$( function() {
    $( "#datepicker" ).datepicker({
        format: 'yyyy-mm-dd'
    });
  } );



$('#search-button').click(function (event) {
    // we need to clear the previous content so we don't append to it
    clearMovieTable();
    $('#getMovieTable').hide();
    hideEditForm();

    // grab the the tbody element that will hold the rows of movie information
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/movies/search?term=' + $('#search-term').val(),
        success: function (data, status) {
            $.each(data, function (index, movie) {
                var poster = movie.poster;
                var title = movie.title;
                var userRating = movie.userRating;
                var id = movie.movieId;

                var row = '';

                if (poster != null) {
                    row += '<div class="wrapper">';
                    row += '<div class="containerx">';
                    row += '<div class="content">';

                    row += '<div class="content-overlay"></div>'
                    row += '<img class="content-image" src="' + poster + '" style="width:150px; height:241px; padding:5px;">';
                    row += '<div class="content-details fadeIn-bottom">';
                    row += '<h3 class="content-title">' + title + '</h3>';
                    row += '<p class="content-text">';
                    row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> EDIT | </a>  ';
                    row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
                    row += '</div>';

                    row += '</div>';
                    row += '</div>';
                    row += '</div>';
                }
                else {
                    row += '<div class="wrapper">';
                    row += '<div class="containerx">';
                    row += '<div class="content">';

                    row += '<div class="content-overlay"></div>'
                    row += '<img class="content-image" src="http://i68.tinypic.com/ml4m0g.png" style="width:150px; height:241px; padding:5px;">';
                    row += '<div class="content-details fadeIn-bottom">';
                    row += '<h3 class="content-title">' + title + '</h3>';
                    row += '<p class="content-text">';
                    row += '<a onclick="showEditForm(' + id + ')" style="font-size:15px;"> EDIT | </a>  ';
                    row += '<a onclick="deleteMovie(' + id + ')" style="font-size:15px;"> DELETE </a> </p>';
                    row += '</div>';

                    row += '</div>';
                    row += '</div>';
                    row += '</div>';
                }
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

})



function clearMovieTable() {
    $('#contentRows').empty();
}

function clearGetInfoTable() {
            $('#get-movie-id').empty();
            $('#get-poster').empty();
            $('#get-title').empty();
            $('#get-releaseDate').empty();
            $('#get-mpaa').empty();
            $('#get-rating').empty();
            $('#get-studio').empty();
            $('#get-director').empty();

}

function showEditForm(movieId) {
    // clear errorMessages
    $('#errorMessages').empty();
    clearGetInfoTable();
    // get the movie details from the server and then fill and show the
    // form on success
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/movies/' + movieId,
        success: function (data) {
            $('#edit-userRating').val(data.userRating);
            $('#edit-movie-id').val(data.movieId);
            $('#get-movie-id').append(data.movieId);
            $('#get-poster').append(data.poster);
            $('#get-title').append(data.title);
            $('#get-releaseDate').append(data.releaseDate);
            $('#get-mpaa').append(data.mpaaRating);
            $('#get-rating').append(data.userRating);
            $('#get-studio').append(data.studio);
            $('#get-director').append(data.directorsName);
            $('#edit-options').show();
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }
    });
    $('#movieTableDiv').hide();
    $('#getMovieTable').show();
    $('#editFormDiv').show();
}

function hideEditForm() {
    // clear errorMessages
    $('#edit-options').hide();
    $('#errorMessages').empty();
    // clear the form and then hide it
    $('#edit-userRating').val('');
    $('#editFormDiv').hide();
    $('#movieTableDiv').show();
}


function deleteMovie(movieId) {
    var result = confirm("Are you sure you want to delete?");
    if (result) {
        $.ajax({
            type: 'DELETE',
            url: "http://localhost:8080/api/movies/" + movieId,
            success: function (status) {
                loadMovies();
            }
        });
    }
}

// processes validation errors for the given input.  returns true if there
// are validation errors, false otherwise
function checkAndDisplayValidationErrors(input) {
    // clear displayed error message if there are any
    $('#errorMessages').empty();
    // check for HTML5 validation errors and process/display appropriately
    // a place to hold error messages
    var errorMessages = [];

    // loop through each input and check for validation errors
    input.each(function () {
        // Use the HTML5 validation API to find the validation errors
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    // put any error messages in the errorMessages div
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({ class: 'list-group-item list-group-item-danger' }).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }



    // Get the modal
    function openModal() {

            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks on the button, open the modal 
            btn.onclick = function() {
            modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function() {
            modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
            }

    }

}
