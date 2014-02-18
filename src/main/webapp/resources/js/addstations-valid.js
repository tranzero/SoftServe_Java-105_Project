$(document).ready(function(){

    $("#addStation").validate({
        
       rules:{ 
        
    	   stationCode:{
                required: true,
                minlength: 4,
                maxlength: 16,
                digits:   true,
            },
            
            stationName:{
                required: true,
                minlength: 6,
                maxlength: 16,
            },
       },
        
    });


}); 
