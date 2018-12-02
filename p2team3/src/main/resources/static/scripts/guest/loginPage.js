$(document).ready(function () {

    $('.dropdown-menu a').click(function(){
        $('#dropdownSpecialty').text($(this).text());
    });

    $('#loginButton').click(function(){
        login($('#inputUsername').val(),$('#InputPassword').val(),$('#dropdownSpecialty').text());
    });
});

