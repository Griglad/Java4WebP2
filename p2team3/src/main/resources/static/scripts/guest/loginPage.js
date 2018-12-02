$(document).ready(function () {

    $('.dropdown-menu a').click(function(){
        $('#dropdownSpecialty').text($(this).text());
    });

    $('#loginButton').click(function(){
        alert($('#inputUsername').val());
        alert($('#InputPassword').val());
        login($('#inputUsername').val(),$('#InputPassword').val(),$('#dropdownSpecialty').text());
    });
});

