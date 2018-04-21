$(document).ready(function () {

    // SUBMIT FORM
    $("#assignUserToProjectForm").submit(function (event) {
        event.preventDefault();
        ajaxPost();
    });

    function ajaxPost() {
        var formData;

        formData = {
            project : $("#project").val(),
            estimationDate : $("#estimationDate").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location + "/user-assign/",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#assignUserToProjectForm').modal('hide');
                console.log(data);
            },
            error: function (e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });
    }
    // todo look here motherFucker and UserRestController
});