$( document ).ready(function() {

    // SUBMIT FORM
    $("#changeStatusForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });
    function ajaxPost(){
        // PREPARE FORM DATA
        var formData;
        formData = {
            taskStatus : $("#statusId").val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/changeStatus",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#changeStatus').modal('hide');
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});