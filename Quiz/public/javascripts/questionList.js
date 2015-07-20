$(document).ready(function(){
	
	// Hide detailed export buttons
	$(".export").hide();
	
	// Predefined method for table sorting
	//$("#question-table").tablesorter();

	$('#question-table').DataTable( {
		"searching": false,
		"language": {
			"url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Croatian.json"
		}
	});
	
	// Method call for filtering table content
	$("#filter").on('change keyup paste mouseup', function() {
		tableFilter();
	});
	
	$(".export-questions").on("click", function() {
		$(".export").toggle();
	});
	
	// Starts dialog button on delete
	$(".delete-button").on("click", function() {
	    var link = this;

	    e.preventDefault();
	    
	    var href = link.href;
	    var hrefArray = href.split("/");
	    var hrefLen = hrefArray.length;
	    
	    var questionId = hrefArray[hrefLen-1];
	    var trName = "question-"+questionId;
	    var questionText = $("#"+trName + " > .table-questionText").html();
	     
	    // i18n via jsMessages
	    $("<div>Jeste li sigurni da želite obrisati pitanje? <br><br> Id: "+questionId+"<br> Tekst: "+questionText+"</div>").dialog({
	        buttons: {
	            "Obriši" : function() {
	                jsRoutes.controllers.QuestionController.deleteQuestion(questionId).ajax({
	                	success: function(data) {
	                		location.reload();
	                	}
	                });
	                
	            },
	            "Odustani": function() {
	                $(this).dialog("close");
	            }
	        }
	    });
	});
	
});



// Method that filters question table based on filter input and filter type
function tableFilter() {
	var rows = $('#question-table > tbody > tr');
	
	var filterInput = $("#filter").val();
	
	var filterType = getTableCellClass($("#filterType").val());
	
	$.each(rows, function(index, row) {
		$this = $(this);
		var value = $this.find(filterType).html();
		
		// add ignore case or not ignore; add trim 
		if (value.toLowerCase().indexOf(filterInput.toLowerCase()) >= 0) {
			$this.show();
		} else {
			$this.hide();
		}
		
	});
	
}

function getTableCellClass(filterType) {
	switch(filterType) {
		case "TEXT_FILTER":
			return ".table-questionText";
		case "TYPE_FILTER":
			return ".table-questionType";
		case "SUBJECT_FILTER":
			return ".table-subject";
		case "CHAPTER_FILTER":
			return ".table-chapters";
		case "DIFFICULTY_FILTER":
			return ".table-difficulty";
		case "SUBMITTER_FILTER":
			return ".table-submitter";
		default:
			return ".table-questionText";
	}
}