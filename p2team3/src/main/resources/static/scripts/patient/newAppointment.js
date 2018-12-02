$(document).ready(function () {


    $.ajax({
        url: ROOT_PATH + "/specialties"
    }).then(function (specialties) {
        populateDropdown1(specialties);
        $('#dp1 a').click(function () {
            $('#selected1').text($(this).text());
            $.ajax({
                url: ROOT_PATH + "/doctors/spec/" + $(this).attr("data-id")
            }).then(function (doctors) {
                $('#dp2').empty();
                $('#dropdownMenuButton').text("Γιατρός");
                populateDropdown2(doctors);
                var id=0;
                $('#dp2 a').click(function () {
                    $('#dropdownMenuButton').text($(this).text());
                    id=$(this).attr("id");

                })

                $('#buttonCreate').click(function(){
                    var appointment = {
                        "doctorId": id,
                        "date": formatDate($("#dateTime").val()),
                        "descr": $("#descriptionTextArea").val(),
                        "notes": $("#notesTextArea").val()
                    };

                    var json = JSON.stringify(appointment);

                    $.ajax({
                        url: 'http://localhost:8080/appointments',
                        data: json,
                        processData: false,
                        contentType: 'application/json',
                        type: 'POST',
                        success: function(resultData){
                            alert("Το νέο ραντεβού έχει προστεθεί!");
                            location.replace("./index.html");
                        },
                        error: function(resultData){
                        var data=resultData.responseText;
                        var jsonResponse = JSON.parse(data);
                        alert(jsonResponse["message"]);
                            }
                
                    }
                    );
                })


            }).fail(function(){
                $('#dp2').empty();
                $('#selected2').text("Γιατρός");
            });
        });
    });

});


function populateDropdown1(specialties) {
    jQuery.each(specialties, function (i, specialty) {
        $("#dp1").append("<li><a href='#' data-id='" + specialty.id + "'>" + specialty.name + "</a></li>");

    });
}

function populateDropdown2(doctors) {
    jQuery.each(doctors, function (i, doctor) {
        $("#dp2").append("<li><a href='#' id='" + doctor.id + "'>" + doctor.firstName + " " + doctor.lastName + "</a></li>");

    });
}

function formatDate(initDate) {
    var splitDate = initDate.split(" ");
    var date = splitDate[0];
    var time = splitDate[1];
    var dateParts = date.split("/");
    var year = dateParts[2];
    var month = dateParts[0];
    var day = dateParts[1];
    var formattedDate = year.concat("/", month, "/", day, " ", time);
    return formattedDate;
}
