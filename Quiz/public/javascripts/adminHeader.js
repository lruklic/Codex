function initHeader() {
	$(".nav-questions").hide();
}

$(document).ready(function(){
	initHeader();
	
	fbLog = false;
	
	window.fbAsyncInit = function() {
    	FB.init({
      		appId      : '909346752488844',
      		xfbml      : true,
      		version    : 'v2.4'
    	});
    		
  	};
  	
  	(function(d, s, id){
    	var js, fjs = d.getElementsByTagName(s)[0];
    	if (d.getElementById(id)) {return;}
     	js = d.createElement(s); js.id = id;
     	js.src = "//connect.facebook.net/en_US/sdk.js";
     	fjs.parentNode.insertBefore(js, fjs);
   	}(document, 'script', 'facebook-jssdk'));
	
	$('.logout').click(function(ev){

		ev.preventDefault();
		
		FB.getLoginStatus(function (response) {
			if (response && response.status === 'connected') {
				FB.logout(function(response) {
					fbLog = true;
					window.location = "/logout";
				});
			}
		});
		
	});
	
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