$(document).ready(function(){
	
	$(".quiz-container").hide();
	
	$("#pagination-demo").twbsPagination({
        totalPages: 12,
        visiblePages: 12,
        onPageClick: function (event, page) {
        	$('div[id^="question-"]').hide();
            $('#question-'+page).show();
        }
    });
	
	$( "#startQuizBtn" ).click(function() {
		$("#startQuizBtn").hide();
		$(".quiz-container").show();
	});
	
});