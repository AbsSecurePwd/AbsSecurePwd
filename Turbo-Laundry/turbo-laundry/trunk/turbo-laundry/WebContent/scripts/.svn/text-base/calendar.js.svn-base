$(function() {
    $( "#fromstr" ).datepicker({
      dateFormat: "dd-MM-yy",
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#tostr" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#tostr" ).datepicker({
      dateFormat: "dd-MM-yy",
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#fromstr" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  });

