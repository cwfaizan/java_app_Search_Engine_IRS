function getNnn(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-nnn",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, documentInfo) {
	            	trHTML += '<tr><td>' + documentInfo.documentId + '</td><td>' + documentInfo.document + '</td><td>' + documentInfo.weight + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#techniques-nnn-table').show()
	            $('#techniques-nnn-table').append(trHTML);
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

function getNtc(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-ntc",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, documentInfo) {
	            	trHTML += '<tr><td>' + documentInfo.documentId + '</td><td>' + documentInfo.document + '</td><td>' + documentInfo.weight + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#techniques-ntc-table').show()
	            $('#techniques-ntc-table').append(trHTML);
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

function getAtc(){
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	$.ajax({
		type : "GET",
		url : dir+"/get-atc",
		timeout : 100000,
		beforeSend: function() {
//			$('#loader').show()
	    },
		success : function(response) {
			$(function() {
				var trHTML = '';
	            $.each(response, function(i, documentInfo) {
	            	trHTML += '<tr><td>' + documentInfo.documentId + '</td><td>' + documentInfo.document + '</td><td>' + documentInfo.weight + '</td></tr>';
	            });
	            $('#loader').hide()
				$('#techniques-atc-table').show()
	            $('#techniques-atc-table').append(trHTML);
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