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

    const patientId = getUrlParameter('id');

    $.ajax({
        url: ROOT_PATH + "/patients/" + patientId
    }).then(function (patient) {
        $("#nameField").text(patient.lastName + " " + patient.firstName);
        $("#amkaField").text(patient.amka);
        $("#phoneField").text(patient.mobilePhone);
        $("#emailField").text(patient.email);

    });
});