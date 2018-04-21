$( document ).ready(function() {

    // SUBMIT FORM
    $("#changePriorityForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });
    function ajaxPost(){

        // PREPARE FORM DATA
        var formData;
        formData = {
            taskPriority : $("#priorityId").val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/changePriority",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#changePriority').modal('hide');
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
});