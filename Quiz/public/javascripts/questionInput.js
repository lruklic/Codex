function initAdmin() {
	// Admin page question input initialization - starting question type is Multiple Choice
	
	var editId = $('[name=id]').val();
	var editChapters = $('[name=editChapters]').val();
	
	// Default number of answers is set to 3
	for (var int = 2; int < 5; int++) {
    	$('.incorrect-'+int).hide();
	}
	
	// If difficulty is not set, set it to 1 (redundant?)
	if($('#difficulty').val() === "") {
		$('#difficulty').val(1);
	}
	
	if($('#multipleNumber').val() === "") {
		$('#multipleNumber').val(3);
	} else {
		var numberOfAnswers = $('#multipleNumber').val();
		for (var int = 1; int < numberOfAnswers; int++) {
	    	$('.incorrect-'+int).show();
		}
	}
	
	// Set Question Type based on current question
	setQuestionType();
	changeDifficultyColor();
	
	// Initial subject is HISTORY and initial grade is 1ST
	var numberOfChapters = $("#chapter > option").length;
	
	var isEdit = false;
	if (editId !== "/") {
		isEdit = true;
	}
	
	chapterChange();
	
	// Set chapters based on current grade and subject
	for (int = 0; int < numberOfChapters; int++) {
		var chapter = $("#check-"+int).val();
		var chapterValues = chapter.split('-');
		var currentChapters = editChapters.replace(/\[|\]| /g, "").split(',');
		
		if (chapterValues[0] !== $("#grade").val() || chapterValues[1] !== $("#subject").val()) {	// why gimn_1st
			$("#check-"+int).hide();
		} else {
			$("#check-"+int).show();
		}
		
		// If question is open for editing, set selected chapters
		if (isEdit) {
			
			for (var j = 0; j < currentChapters.length; j++) {
				if (currentChapters[j] === chapterValues[2]) {
					$("#check-"+int).prop('selected', true);
				}
			}	
		} 
	}
}

function setQuestionType() {
	var qType = $("#qType").val().toLowerCase();
	
	$(".numberOfAnswers").hide();
	$(".multiple_choice").hide();
	$(".multiple_answer").hide();
	$(".true_false").hide();
	$(".input_answer").hide();
	$(".connect_correct").hide();

	if (qType === "multiple_choice" || qType === "multiple_answer") {
		$(".numberOfAnswers").show();
		questionNumberChange();
	}
	$("."+qType).show();
	
}

function changeDifficultyColor() {
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
}

function questionNumberChange() {
	if ($.isNumeric($('#multipleNumber').val()) && $('#multipleNumber').val() < 7 && $('#multipleNumber').val() > 2) {
		var numberOfAnswers = $('#multipleNumber').val();
		
		// case with Multiple Choice selected
		if ($("#qType").val() === "MULTIPLE_CHOICE") {
    		// Hide extra inputs
    		for (var int = 2; int < 5; int++) {
        		$('.incorrect-'+int).hide();
			}
    		// Dynamicly show input text boxes for incorrect answers
    		for (var int = 2; int < numberOfAnswers-1; int++) {
        		$('.incorrect-'+int).show();
			}
    		
    	// case with Multiple Answer selected
		} else {
			// Hide extra inputs
    		for (var int = 0; int < 6; int++) {
        		$('.multiple-'+int).hide();
			}
    		// Dynamicly show input text boxes for answers
    		for (var int = 0; int < numberOfAnswers; int++) {
        		$('.multiple-'+int).show();
			}
			
		}

	} else {
		$('#multipleNumber').val(3);
		
		// Hide extra inputs
		for (var int = 2; int < 5; int++) {
    		$('.incorrect-'+int).hide();
		}
		for (var int = 3; int < 6; int++) {
    		$('.multiple-'+int).hide();
		}
	}
}

function chapterChange() {
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
}

$(document).ready(function(){
	
	initAdmin();
	
    $("#qType").change(function(){
    	setQuestionType();
    });
    
    $(".chapter-trigger").change(function(){
    	chapterChange();
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
    	changeDifficultyColor();
    });
    
    
    // Method that creates or deletes incorrect answer input forms when number of answers is changed
    $('#multipleNumber').on('input',function(){
    	questionNumberChange();
    });
}); 