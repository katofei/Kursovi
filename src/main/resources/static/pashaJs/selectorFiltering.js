$(document).ready(function (){
    var table = $('#example1').DataTable({
          dom: 'lrtip'
    });

    $('#prioritySelector').on('change', function(){
       table.search(this.value).draw();
    });

    $('#statusSelector').on('change', function(){
           table.search(this.value).draw();
     });

 });
