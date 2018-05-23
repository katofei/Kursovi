window.onload = function () {
    document.getElementsByClassName("mySelect")[0].value = window.location.search.split('userId=')[1] != undefined &&
    window.location.search.split('userId=')[1] !== "" ? window.location.search.split('userId=')[1] : '0';
    document.getElementsByClassName("mySelect")[0].addEventListener('change', function () {
        var userId = $(this).val();

        if (window.location.search.split('userId=')[1] != undefined) {
            if (window.location.search.split('userId=')[1] !== "") {
                window.location.href = window.location.href.replace(window.location.search.split('userId=')[1], userId);
            } else {
                window.location.href = window.location.href + userId;
            }
        } else {
            window.location.href = window.location.href + "?userId=" + userId;
        }
    });
};