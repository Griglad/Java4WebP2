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
        $("#descriptionTextArea").text(appointment.descr);
        $("#notesTextArea").text(appointment.notes);
        $("#patientName").val(appointment.patient.lastName + " " + appointment.patient.firstName);
        $("#patientAmka").val(appointment.patient.amka);
        $("#dateTime").val(appointment.dateTime);
        $("#patientDetails").attr("href",ROOT_PATH + "/pages/doctor/patientDetails.html?id=" + appointment.patient.id + "&appid=" + appointment.id);

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