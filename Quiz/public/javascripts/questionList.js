$(document).ready(function(){
	
	// Hide detailed export buttons
	$(".export").hide();
	
	// Predefined method for table sorting
	$('#question-table').DataTable( {
		// "searching": false,
		"language": {
			"url": "//cdn.datatables.net/plug-ins/1.10.7/i18n/Croatian.json"
		}
	});
	
	// DataTable
    var table = $('#question-table').DataTable();
    
    table.columns().eq(0).each( function (index) {
    	
    	var bla = table.column(index).header();
    	var bla = table.column(index).footer();
    	
        $( 'input', table.column(index).header() ).on( 'keyup change', function () {
 
            table.column(index)
                .search( this.value )
                .draw();
        } );
        
    } );
	
	// Method call for filtering table content
	$("#filter").on('change keyup paste mouseup', function() {
		
		var filter = $("#filter").val();
		var searchColumn = getColumnNumber($("#filterType").val());
		
		table.column(searchColumn).search(filter).draw();

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

// Predefines column numbers
function getColumnNumber(filterType) {
	switch(filterType) {
		case "TEXT_FILTER":
			return 0;
		case "TYPE_FILTER":
			return 1;
		case "SUBJECT_FILTER":
			return 2;
		case "CHAPTER_FILTER":
			return 3
		case "DIFFICULTY_FILTER":
			return 4;
		case "SUBMITTER_FILTER":
			return 5;
		default:
			return 1;
	}
}


