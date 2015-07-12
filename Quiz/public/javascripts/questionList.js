$(document).ready(function(){
	
	$("#question-table").tablesorter();

	$("#filter").on('change keyup paste mouseup', function() {
		tableFilter();
	});
	
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
		case "SUBJECT_FILTER":
			return ".table-subject";
		default:
			return ".table-questionText";
	}
}