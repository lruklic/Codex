$(document).ready(function(){
	
	// Admin page question input initialization - starting question type is Multiple Choice
	$(".true-false").hide();
	$(".input-answer").hide();
	
	for (var int = 3; int < 6; int++) {
    	$('.incorrect-'+int).hide();
	}
	
    $("#qType").change(function(){
    	var qType = $("#qType").val();
    	switch(qType) {
    		case "MULTIPLE_CHOICE":
    			$(".multiple").show();
    			$(".true-false").hide();
    			$(".input-answer").hide();
    			break;
    		case "YES_NO":
    			$(".true-false").show();
    			$(".multiple").hide();
    			$(".input-answer").hide();
    			break;
    		case "INPUT_ANSWER":
    			$(".input-answer").show();
    			$(".multiple").hide();
    			$(".true-false").hide();
    			break;
    	}
    });
    
    $(".chapter-trigger").change(function(){
    	var currentGrade = $("#grade").val();
    	var currentSubject = $("#subject").val();
    	var numberOfChapters = $("#chapter > option").length;
    	for (int = 0; int < numberOfChapters; int++) {
			var chapter = $("#check-"+int).val();
			var chapterValues = chapter.split('-');
			if (currentGrade !== chapterValues[0] || currentSubject !== chapterValues[1]) {
				$("#check-"+int).hide();
			} else {
				$("#check-"+int).show();
			}
		}
    });
    
//    $("#chapter").change(function(){
//    	var t1 = $.now();
//    	$.ajax({
//    		url: '/question/chapters/GIMN_1ST/HISTORY',
//    		success: function(data) {
//    			data;
//    			var t2 = $.now();
//    			alert(t2 - t1);
//    		},
//    		type: 'GET'
//    	});
//    });
    
    // Difficulty picker color change
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
    
    
    // Method that creates or deletes incorrect answer input forms when number of answers is changed
    $('#multipleNumber').on('input',function(){
    	if ($.isNumeric($('#multipleNumber').val()) && $('#multipleNumber').val() < 7 && $('#multipleNumber').val() > 2) {
    		var numberOfAnswers = $('#multipleNumber').val();
    		
    		// Hide extra inputs
    		for (var int = 3; int < 6; int++) {
        		$('.incorrect-'+int).hide();
			}
    		
    		// Dynamicly generate input text boxes for incorrect answers
    		for (var int = 3; int < numberOfAnswers; int++) {
        		$('.incorrect-'+int).show();
			}
    	} else {
    		$('#multipleNumber').val(3);
    		
    		// Hide extra inputs
    		for (var int = 3; int < 6; int++) {
        		$('.incorrect-'+int).hide();
			}
    	}
    });
}); 