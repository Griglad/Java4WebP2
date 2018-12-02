$(document).ready(function () {

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };

    var appId = getUrlParameter('id');

    $.ajax({
        url: ROOT_PATH + "/appointments/" + appId
    }).then(function (appointment) {
        $("#specialty").attr("value", appointment.doctor.specialty.name);
        $("#doctor").attr("value", appointment.doctor.firstName + " " + appointment.doctor.lastName);
        $('#appointmentDate').attr("value", appointment.dateTime);
        $("#descriptionTextArea").text(appointment.descr);
        $("#notesTextArea").text(appointment.notes);

        var appointDate = $("#appointmentDate").attr("value");


        $("#appointmentDate").change(function () {
            appointDate = formatDate($(this).attr("value"));
        });




        $("#buttonUpdate").click(function () {
            if ($("#appointmentDate").val() != appointDate) {
                appointDate = formatDate($("#appointmentDate").val());
            }
            var editedData = {
                "descr": $("#descriptionTextArea").val(),
                "date": appointDate,
                "notes": $("#notesTextArea").val()
            };
            var editedDatajson = JSON.stringify(editedData);
            var testjson = {};
            if (JSON.stringify(editedData) === JSON.stringify(testjson)) {
                return;
            }
            $.ajax({
                url: ROOT_PATH + "/appointments/" + appId,
                data: editedDatajson,
                processData: false,
                contentType: 'application/json',
                type: 'PUT'
            }).then(function () {
                alert("Το Ραντεβού ενημερώθημε με επιτυχία!");
            });
        });

    });


    $("#buttonDelete").click(function () {
        if (confirm("Το Ραντεβού θα διαγραφεί οριστικά.")) {
            $.ajax({
                url: ROOT_PATH + "/appointments/" + appId,
                type: 'DELETE'
            });
        };
    });

});

function formatDate(initDate) {
    const splitDate = initDate.split(" ");
    const date = splitDate[0];
    let time = splitDate[1];
    const pm = splitDate[2];
    const hoursmins = time.split(":");
    let hours = hoursmins[0];
    const mins = hoursmins[1];
    if(pm=="PM"){
        const editedhours= Number(hours)+12;
        time = editedhours.toString().concat(":",mins);
    }
    const dateParts = date.split("/");
    const year = dateParts[2];
    const month = dateParts[0];
    const day = dateParts[1];
    const formattedDate = year.concat("/", month, "/", day, " ", time);
    return formattedDate;
}

