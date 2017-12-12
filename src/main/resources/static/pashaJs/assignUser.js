$( document ).ready(function() {

    // SUBMIT FORM
    $("#assignAnotherUserForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA

        var formData;

        formData = {
            executor : $("#executorId").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/assignAnotherUser",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#assignAnotherUser').modal('hide');
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});