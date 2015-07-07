$(document).ready(function(){
	
	$("#question-table").tablesorter();

	$(".delete-button").on("click", function(e) {
	    var link = this;

	    e.preventDefault();
	    
	    var href = link.href;
	    var hrefArray = href.split("/");
	    var hrefLen = hrefArray.length;
	    
	    var questionId = hrefArray[hrefLen-1];
	    var trName = "question-"+questionId;
	    var questionText = $("#"+trName + " > .table-questionText").html();

	    $("<div>Jeste li sigurni da želite obrisati pitanje? <br><br> Id: "+questionId+"<br> Tekst: "+questionText+"</div>").dialog({
	        buttons: {
	            "U redu": function() {
	                window.location = link.href;
	            },
	            "Odustani": function() {
	                $(this).dialog("close");
	            }
	        }
	    });
	});
	
});
