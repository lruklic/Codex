$(document).ready(function(){
	
    $("#questionType").change(function(){
    	alert($("#questionType").val());
    });
    
    // Difficulty picker
    $('#difficulty').on('input',function(){
    	if ($.isNumeric($('#difficulty').val()) && $('#difficulty').val() < 6 && $('#difficulty').val() > 0) {
    		var difficulty = $('#difficulty').val();
    		switch (difficulty) {
    			case "1": 
    				$('#difficulty').css('background-color', '#8CFA70');
    				break;
    			case "2": 
    				$('#difficulty').css('background-color', '#BFFA87');
    				break;
    			case "3": 
    				$('#difficulty').css('background-color', '#ECFF6E');
    				break;
    			case "4": 
    				$('#difficulty').css('background-color', '#F7CE28');
    				break;
    			case "5": 
    				$('#difficulty').css('background-color', '#FA661B');
    				break;
    		}
    		
    	} else {
    		$('#difficulty').val(1);
    	}
    });
    
    $('#multipleNumber').on('input',function(){
    	if ($.isNumeric($('#multipleNumber').val()) && $('#multipleNumber').val() < 7 && $('#multipleNumber').val() > 2) {
    		var numberOfAnswers = $('#multipleNumber').val();
    		
    		// Remove extra inputs
    		for (var int = 3; int < 6; int++) {
        		$('.incorrect-'+int).remove();
			}
    		
    		for (var int = 3; int < numberOfAnswers; int++) {
        		$('.multiple-incorrect').append("<p class=\"incorrect-"+ int +"\">" +
        				"<label>Incorrect Answer: </label>" +				// promijeniti Incorrect Answer; da dohvaća iz zadatka
        				"<input type=\"text\" id=\"incorrect-"+ int + "\" /></p>"
        				)
			}
    		
    		
    	} else {
    		$('#multipleNumber').val(3);
    		
    		// Remove extra inputs
    		for (var int = 3; int < 6; int++) {
        		$('.incorrect-'+int).remove();
			}
    	}
    });
}); 