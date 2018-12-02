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
        $("#descriptionTextField").text(appointment.descr);
        $("#detailsTextField").text(appointment.notes);
        $("#patientDetails").attr("href",ROOT_PATH + "/pages/doctor/patientDetails.html?id=" + appointment.patient.id);

        //var appointDate = $("#appointmentDate").attr("value");


       // $("#appointmentDate").change(function () {
       //     appointDate = formatDate($(this).attr("value"));
       // });

    });
});