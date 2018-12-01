$(document).ready(function () {


    $.ajax({
        url: ROOT_PATH + "/specialties"
    }).then(function (specialties) {
        populateDropdown(specialties);
        $('#dp1 a').click(function () {
            $('#dropdownMenuButton1').text($(this).text());
            $('#dropdownMenuButton1').attr('data-id',$(this).attr('id'));
        });
    });

    $('#searchButton').click(function(){
        var myData={};
        if($("#dropdownMenuButton1").text().trim()!='Ειδικότητα'){
            alert('Test1');
            alert($("#dropdownMenuButton1").text());
            myData.spec = $("#dropdownMenuButton1").attr('data-id');
        }
        if($('#date1').val()!=''){
            alert('Test2');
            var date1 =  $('#date1').val();
            var fixedDate1 = formatDate(date1);
            alert(fixedDate1);
            myData.from = fixedDate1;
        }

        if($('#date2').val()!=''){
            alert('test3');
            var date2 =  $('#date2').val();
            var fixedDate2 = formatDate(date2);
            alert(fixedDate2);
            myData.to = fixedDate2;
        }
        
        //myData.from = '2010/11/01 11:07';
        //myData.to = '2020/11/01 11:07';
        var table = $('#table_id').DataTable({
            ajax:{
                url:'http://localhost:8080/patients/appointments',
                dataSrc:'',
                data: myData
            },
            "searching": false,
            "bLengthChange": false,
            columns:[
                {data: 'dateTime'},
                {data: 'id'},
                {data: 'doctor.lastName',
                "render":function(data, type, full, meta){
                    return full.doctor.lastName + ' ' + full.doctor.firstName;}
                },
                {data: 'doctor.specialty.name'},
                {data: null,
                    "render":function(data, type, row, meta){
                        return "<a class='btn btn-primary' href='http://localhost:8080/pages/patient/editAppointment.html?id=" +data.id+ "'>Επεξεργασία</a>";}
                }
            ]
        });

        $('#editme').click( function () {
            alert('Hey!!!!!!!!!');
            var data = table.row( $(this).parents('tr') ).data();
            location.replace(ROOT_PATH +"/pages/patient/editAppointment.html?id="+data[1]);
        } );
    })


});



function populateDropdown(specialties) {
    jQuery.each(specialties, function (i, specialty) {
        $("#dp1").append("<a class='dropdown-item' href='#' id='" + specialty.id + "'>" + specialty.name + "</a>");
    });
}

function formatDate(initDate){
    var splitDate = initDate.split(" ");
    var date = splitDate[0];
    var time = splitDate[1];
    var dateParts = date.split("/");
    var year = dateParts[2];
    var month = dateParts[0];
    var day = dateParts[1];
    var formattedDate = year.concat("/",month,"/",day," ",time);
    return formattedDate;
}

