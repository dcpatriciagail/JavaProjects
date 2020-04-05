$(document).ready(function () {

    loadInventory();
});

function loadInventory() {
    $('#errorMessages').empty();
    var newTable = $('#newTableBody');
    var usedTable = $('#usedTableBody');

    $.ajax({

        type: 'GET',
        'url': 'http://localhost:8080/cardealership/reports/inventory',
        success: function(InventoryArray) {
            var newVehicles = InventoryArray[0];
            var usedVehicles = InventoryArray[1];
            $.each(newVehicles, function(index,report) {

                var tableRow = '<tr>';
                $.each(newVehicles[index], function(index2,data) {
                   tableRow += '<td>'+data+'</td>';
                });
                tableRow += '</tr>';

                newTable.append(tableRow);
            });

            $.each(usedVehicles, function(index,report) {

                var tableRow = '<tr>';
                $.each(usedVehicles[index], function(index2,data) {
                   tableRow += '<td>'+data+'</td>';
                });
                tableRow += '</tr>';

                usedTable.append(tableRow);
            });


        },
        error: function() {
            $('#errorMessages')
                    .append('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error loading inventory');
        }
    });

}
