function getFilteredDocuments(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-filtered-documents",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, doc) {
	            	trHTML += '<tr><td>' + doc.documentId + '</td><td>' + doc.document + '</td><td>' + doc.documentLabel + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#filtered-documents-table').show()
	            $('#filtered-documents-table').append(trHTML);
	        });
		},
		error : function(e) {
			alert("We are sorry, Server didn't response please try again later");
		},
		done : function(e) {
			alert("done2 ");
		}
	});
}

function getTokens(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-tokens",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, term) {
	            	trHTML += '<tr><td>' + term.termId + '</td><td>' + term.term + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#filtered-terms-table').show()
	            $('#filtered-terms-table').append(trHTML);
	        });
		},
		error : function(e) {
			alert("We are sorry, Server didn't response please try again later");
		},
		done : function(e) {
			alert("done2 ");
		}
	});
}

function getInvertedIndex(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-inverted-index",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, term) {
	            	trHTML += '<tr><td>' + term.termId + '</td><td>' + term.term + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#filtered-inverted-index-table').show()
	            $('#filtered-inverted-index-table').append(trHTML);
	        });
		},
		error : function(e) {
			alert("We are sorry, Server didn't response please try again later");
		},
		done : function(e) {
			alert("done2 ");
		}
	});
}