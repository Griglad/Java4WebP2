$(document).ready(function() {

    $.ajax({
        url: ROOT_PATH + "/specialties"
    }).then(function(specialties) {
        populateDropdown(specialties);
    });
})


function populateDropdown(specialties) {
jQuery.each(specialties, function(i,specialty) {
    $("#dp1").append("<a class='dropdown-item' href='#'>" + specialty.name + "</a>" );
    alert(specialty.name);
    
 });
}