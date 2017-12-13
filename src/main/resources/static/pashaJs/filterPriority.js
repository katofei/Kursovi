$(document).ready(function(){

    $("#creatorCheckBox").change(function () {

        var input, filter, table, tr, td, i;

        input = 'High';

        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        if ($('input:checkbox[name=checkme]').is(':checked'))
        {
            for (i = 0; i < tr.length; i++) {
                tr[i].style.display = "none";
            }
        } else
        {
            filter = input.value.toUpperCase();
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[3];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    });
});