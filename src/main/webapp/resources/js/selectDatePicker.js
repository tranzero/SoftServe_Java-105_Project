		$(function() {
	        var dateField = $('#DateField');
	        var SelectDateDiv = $('#SelectDate');
	        
	        $('#DateField').live('click', function() {
	                $('<p><input type="text" id="selectDateF" size="20" name="selectDateF"  /></label> <a href="#" id="remDateField">Hide</a></p>').appendTo(SelectDateDiv);
	            $('#DateField').remove();
	                return false;
	        });
	        
	        $('#remDateField').live('click', function() { 
	                $('#selectDateF').remove();
	                $('#remDateField').remove();
	            $('<h2><a href="#" id="DateField">Select Date</a></h2>').appendTo('#addDate');
	                return false;
	        });
	});		
