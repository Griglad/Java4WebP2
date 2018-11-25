$(document).ready(function(){

    $("form[data-enable-validation] input[data-validation][data-validation != 'submit']").on("keyup change focus",function(){
        try{
            const value = $(this).val() || "";
            const valid_type = $(this).attr("data-validation");

            switch (valid_type){
                case "firstname":
                    validate(this,value,/^[A-Za-zΑ-Ωα-ωίϊΐόάέύϋΰήώ]{1,}$/); 
                    break;
                case "lastname":
                    validate(this,value,/^[A-Za-zΑ-Ωα-ωίϊΐόάέύϋΰήώ]{1,}$/); 
                    break;
                case "amka":
                    validate(this,value,/^[0-9]{9,11}$/);
                    break;
                case "mobile":
                    validate(this,value,/^(\d{10,14})$/); 
                    break;
                case "mail":
                    validate(this,value,/.+@.+\..+/); 
                    break;
                case "password":
                    validate(this,value,/^[a-zA-Z0-9]{6,30}$/); 
                    break;
                case "username":
                    validate(this,value,/^[a-zA-Z0-9]{6,30}$/); 
                    break;
                case "confirmpassword":
                    let pass = $("input[name=\"password\"]").val();
                    var regex = new RegExp(pass+"{1,}$");
                    validate(this,value,regex); 
                    break;
                default:
                    console.warn("Wrong validation name.", validation_type);
                    break;    
            }
        
        }catch(e){
            console.error(e);
        }

    })

    function validate(element, value, regex) {

        // Test element value
        const isValid = regex.test(value);

        if (!isValid) {
            if ($(element).hasClass("valid-data")) {
                $(element).removeClass("valid-data");
            }

            if (!$(element).hasClass("invalid-data")) {
                $(element).addClass("invalid-data");
            }

        }
        else {
            if ($(element).hasClass("invalid-data")) {
                $(element).removeClass("invalid-data");
            }

            if (!$(element).hasClass("valid-data")) {
                $(element).addClass("valid-data");
            }

        }

        changeSubmitButtonStatus(element);
    }

    function changeSubmitButtonStatus(element) {
        // Disable/ enable button

        const closestForm = $(element).closest("form");
        if (!closestForm.length) {
            console.warn("Submit button is undefined.");
            return;
        }

        const submitButton = $(closestForm).find("[data-validation='submit']");
        if (!submitButton.length) {
            console.warn("Submit button is undefined.");
            return;
        }
        
        const allElements = $(closestForm).find("[data-validation][data-validation!='submit']").length;
        const validElements = $(closestForm).find(".valid-data").length;

        submitButton.attr("disabled", allElements !== validElements);
    }






})