$( document ).ready(function() {

    // SUBMIT FORM
    $("#logTimeForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });
    function ajaxPost(){
        // PREPARE FORM DATA
        var formData;
        formData = {
            timeSpent : $("#timeSpentId").val(),
            percentage : $("#percentageId").val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/logTime" +
            "",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#logTime').modal('hide');
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});