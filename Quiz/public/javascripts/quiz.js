$(document).ready(function(){
	
	$(".quiz-container").hide();
	
	$("#pagination").twbsPagination({
        totalPages: 16,
        visiblePages: 5,
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
	});
});

function createQuestionJSON() {
	$('#questions').children('div').each(function () {
		// Fetch data for each question for HTML
		var question = $(this);
		var questionId = question.children(".question-id").val();
		var questionType = question.children(".question-type").val();
		
		// Create JSON object
		var qsJson = new Object();
		qsJson.id = questionId;
		qsJson.answers = getAnswers(questionId, questionType);

		$.ajax({
			type: "POST",
			traditional: true,
			url: "/quiz/evaluate",
			async: false,
			data: qsJson,
			dataType: "json",
			success: function(data) {
				// success
			}
			
		});
	    // alert(qsJson.toString()); // "this" is the current element in the loop
	});
}

//Method that gets selected/inputed answers from HTML 
function getAnswers(questionId, questionType) {
	
	switch(questionType) {
		case "MULTIPLE_CHOICE":
			var checked = $("input[name="+questionId+"]:checked").val();
			return checked;
		case "MULTIPLE_ANSWER":
			var selected = $("input[name="+questionId+"]:checked").map(function() {return this.value});
			return selected;

	}
}