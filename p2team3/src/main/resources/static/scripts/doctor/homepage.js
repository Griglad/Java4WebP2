$(document).ready(function () {


    $('#buttonSearch').click(function(){
        var myData={};
        if($('#dateFrom').val()!=''){
            var date1 =  $('#dateFrom').val();
            var fixedDate1 = formatDate(date1);
            myData.from = fixedDate1;
        }

        if($('#dateTo').val()!=''){
            var date2 =  $('#dateTo').val();
            var fixedDate2 = formatDate(date2);
            myData.to = fixedDate2;
        }

        if($('#inputdisease').val()!=''){
            const disease = $('#inputdisease').val();
            myData.descr = disease;
        }

        if ($('#resultsTable').DataTable) {
            $('#resultsTable').DataTable().destroy()
        }

        var table = $('#resultsTable').DataTable({
            ajax:{
                url:'http://localhost:8080/doctors/appointments',
                dataSrc:'',
                data: myData
            },
            "searching": false,
            "bLengthChange": false,
            columns:[
                {data: 'dateTime'},
                {data: 'id'},
                {data: 'patient.lastName',
                "render":function(data, type, full, meta){
                    return full.patient.lastName + ' ' + full.patient.firstName;}
                },
                {data: 'descr'},
                {data: null,
                    "render":function(data, type, row, meta){
                        return "<a class='btn btn-primary' href='http://localhost:8080/pages/doctor/AppointmentDetails.html?id=" +data.id+ "'>Λεπτομέρειες</a>";}
                }
            ]
        });

    })


});

function formatDate(initDate) {
    const splitDate = initDate.split(" ");
    const date = splitDate[0];
    const time = splitDate[1];
    const dateParts = date.split("/");
    const year = dateParts[2];
    const month = dateParts[0];
    const day = dateParts[1];
    const formattedDate = year.concat("/", month, "/", day, " ", time);
    return formattedDate;
}



