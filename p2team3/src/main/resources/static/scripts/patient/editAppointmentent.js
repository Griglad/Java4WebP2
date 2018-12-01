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
        $("#descriptionTextArea").attr("value", appointment.descr);
        $("#notesTextArea").attr("value", appointment.notes);

    });

    var editedData = {};
    $("#descriptionTextArea").change(function () {
        editedData.descr = $(this).val();
    });

    $("#dateTextArea").change(function () {
        editedData.date = formatDate($(this).val());
    });

    $("#notesTextArea").change(function () {
        editedData.notes = $(this).val();
    });

    var editedDatajson = JSON.stringify(editedData);
    


    $("#buttonUpdate").click(function () {
        alert(editedDatajson);
        alert(editedData);
        var testjson = {};
        if (JSON.stringify(editedData) === JSON.stringify(testjson)) {
            return;
        }
        $.ajax({
            url: ROOT_PATH + "/appointments/" + appId,
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            type: 'PUT',
            data: editedDatajson
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

