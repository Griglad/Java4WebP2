$(document).ready(function () {


    $.ajax({
        url: ROOT_PATH + "/specialties"
    }).then(function (specialties) {
        populateDropdown1(specialties);
        $('#dp1 a').click(function () {
            $('#selected1').text($(this).text());
            $.ajax({
                url: ROOT_PATH + "/doctors/spec/" + $(this).text()
            }).then(function (doctors) {
                $('#dp2').empty();
                $('#selected2').text("Γιατρός");
                populateDropdown2(doctors);
                var id=0;
                $('#dp2 a').click(function () {
                    $('#selected2').text($(this).text());
                    id=$(this).attr("id");

                })

                $('#newAppbutton').click(function(){
                    var appointment = {
                        "doctorId": id,
                        "date": $("input[name=dateTime]").val(),
                        "descr": $("input[name=Descr]").val(),
                        "notes": $("input[name=Notes]").val()
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
        $("#dp1").append("<li><a href='#' id='" + specialty.id + "'>" + specialty.name + "</a></li>");

    });
}

function populateDropdown2(doctors) {
    jQuery.each(doctors, function (i, doctor) {
        $("#dp2").append("<li><a href='#' id='" + doctor.id + "'>" + doctor.firstName + " " + doctor.lastName + "</a></li>");

    });
}

