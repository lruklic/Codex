$(document).ready(function(){
	
	$(".delete-button").on("click", function(e) {
	    var link = this;

	    e.preventDefault();

	    $("<div>Are you sure you want to continue?</div>").dialog({
	        buttons: {
	            "Ok": function() {
	                window.location = link.href;
	            },
	            "Cancel": function() {
	                $(this).dialog("close");
	            }
	        }
	    });
	});
    
//	$(".table-icon").hover(function(){
//		$(this).animate({ margin: -10, width: "+=20", height: "+=20" });
//	
//	}, function(){
//		$(this).animate({ margin: 0, width: "-=20", height: "-=20" });
//	});
	
});