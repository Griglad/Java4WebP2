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
        $("#notesTextArea").text( appointment.notes);

        var appointDate = $("#appointmentDate").attr("value");
    alert(appointDate);
    alert($("#appointmentDate").attr("value"));

    $("#appointmentDate").change(function () {
        alert("Hey");
         appointDate = formatDate($(this).attr("value"));
    });

    


    $("#buttonUpdate").click(function () {
        if($("#appointmentDate").val()!=appointDate){
            alert("imerominia");
            appointDate= formatDate($("#appointmentDate").val());
        }
        var editedData = {
           "descr": $("#descriptionTextArea").val(),
            "date" : appointDate,
            "notes" : $("#notesTextArea").val()
        };
        var editedDatajson = JSON.stringify(editedData);
       // alert(editedData);
        //alert(editedDatajson);
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
        });
    });

    });

   // var editedData = {}; 
    //$("#descriptionTextArea").change(function () {
    ///    editedData.descr = $(this).val();
    //    alert("Hey"+$(this).val());
   //     alert(JSON.stringify(editedData));
  //  });

   // $("#appointmentDate").change(function () {
       // editedData.date = formatDate($(this).val());
   // });

   // $("#notesTextArea").change(function () {
   //     editedData.notes = $(this).val();
   //     alert(JSON.stringify(editedData));
   // });

    

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

