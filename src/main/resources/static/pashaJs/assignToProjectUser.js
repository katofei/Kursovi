$(document).ready(function () {

    // SUBMIT FORM
    $("#assignUserToProjectForm").submit(function (event) {

        var elements = document.getElementsByClassName('selected');

        var userId;

        for (var i = 0; i < elements.length; i++) {
            userId = elements[i].id;
        }

        event.preventDefault();
        ajaxPost(userId);
    });

    function ajaxPost(userId) {
        var formData;

        formData = {
            id: userId,
            project : $("#project").val(),
            estimationDate : $("#estimationDate").val()
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",

            url: window.location + "/user-assign",
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function (data) {
                $('#assignUserToProject').modal('hide');
                console.log(data);
            },
            error: function (e) {
                $('#assignUserToProject').modal('hide');
                console.log("ERROR: ", e);
            }
        });
    }
    // todo look here motherFucker and UserRestController
});