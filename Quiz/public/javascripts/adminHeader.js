function initHeader() {
	$(".nav-questions").hide();
}

$(document).ready(function(){
	initHeader();
	
	$('.questions').click(function(){
		$(".nav li").css('width', '200px');
	
    	$(".nav-home").hide();
    	$(".nav-questions").show();
	});
	
	$('.back').click(function(){
    	$(".nav-questions").hide();
    	$(".nav li").css('width', '120px');
    	$(".nav-home").show();
	});
});