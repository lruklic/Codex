/**
 * Javascript that handles effects for quiz HTML.
 */

$(document).ready(function(){
	
    $( ".draggable" ).draggable({ 
    	cursor: "crosshair", 
    	revert: false
    });
    
    $( ".droppable" ).droppable({
    	drop: function( event, ui ) {
    		
    		var draggedAnswer = $(ui.draggable).attr("id");
    		
    		$(this).attr('answer', draggedAnswer);
    		
    		$(this).addClass( "ui-state-highlight" );
    	},
    	out: function(event, ui) {
    		if ($(ui.draggable).attr("id") === $(this).attr('answer')) {
        		$(this).attr('answer', null);
    		}
    	}
    });
	
	$(".quiz-container").hide();
	
	var numberOfQuestions = $('#questions').children().size();
	var visiblePages = (numberOfQuestions < 5 ? 5 : numberOfQuestions);
	
	$("#pagination").twbsPagination({
		first: Messages("pagination.first"),
		prev: Messages("pagination.previous"),
		next: Messages("pagination.next"),
		last: Messages("pagination.last"),
        totalPages: numberOfQuestions,
        visiblePages: visiblePages,
        onPageClick: function (event, page) {
        	$('div[id^="question-"]').hide();
            $('#question-'+page).show();
        }
    });
	
	$("#startQuizBtn").click(function() {
		$("#startQuizBtn").hide();
		$(".quiz-container").show();
		$('div[id^="question-"]').hide();
		$("#question-1").show();
	});
	
	$("#finishQuizBtn").click(function() {
		createQuestionJSON();
		$(".quiz-container").hide();
		
	});
});

function getConnectCorrect(questionId) {
	
	var answerPairs = [];
	
	$("#q-"+questionId+"-answers>div.droppable").each(function() {
		
		var answerPair = [];
		
		answerPair[0] = $(this).attr('answer');
		answerPair[1] = $(this).attr('id');
	
		answerPairs.push(answerPair);
	});
	
	return answerPairs;
}

/**
 * Method that is called when player wants to evaluate his quiz answers. All answers are collected from HTML and are
 * dispatched in JSON for via AJAX to method mapped to /quiz/evaluate. Ajax recei ves data with results in form of HTML 
 * page generated by QuizController and that data is displayed in quiz_start HTML page.
 */
function createQuestionJSON() {
	
	var questions = [];
	
	$('#questions').children('div').each(function () {
		// Fetch data for each question for HTML
		var question = $(this);
		var questionId = question.children(".question-id").val();
		var questionType = question.children(".question-type").val();
		
		// Create JSON object
		var qsJson = new Object();
		qsJson.id = questionId;
		qsJson.answers = getAnswers(questionId, questionType);

		questions.push(qsJson);
	    // alert(qsJson.toString()); // "this" is the current element in the loop
	});

	$.ajax({
		type: "POST",
		traditional: true,
		url: "/quiz/evaluate",
		data: JSON.stringify(questions),
		contentType: 'application/json',
		success: function(data) {
			$(".result").html(data);
		}
		
	});
}

/**
 * Method that gets selected/inputed answers from HTML.
 */
function getAnswers(questionId, questionType) {
	
	switch(questionType) {
		case "MULTIPLE_CHOICE":
		case "TRUE_FALSE":
			var answer = $("input[name="+questionId+"]:checked").val();
			return answer;
		case "MULTIPLE_ANSWER":
			var answers = [];
			$("input[name="+questionId+"]:checked").each(function() {
				answers.push(this.value);
			});
			return answers;
		case "INPUT_ANSWER":
			var answer = $("input[name="+questionId+"]").val();
			return answer;
		case "CONNECT_CORRECT":
			var answers = getConnectCorrect(questionId);
			return answers;
	}
}