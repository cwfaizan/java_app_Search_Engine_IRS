$( document ).ready(function() {
	var technique = null;
	var techniques = [ "nnn-atc", "ntc-atc" ];
	var loc = window.location.pathname;
	var dir = loc.substring(0, loc.lastIndexOf('/'));
	
	$( '#similarity li' ).click(function() {
	    if($(this).attr('id') == "nnn-atc")
	    	$( '#search_concept' ).text("NNN,ATC");	
	    else
	    	$( '#search_concept' ).text("NTC,ATC");
	    technique = $(this).attr('id');
	});
	
	$('#search_query').click(function() {
		if(technique == null)
			alert("Please choose SMART notation");
		else if(jQuery.inArray( technique, techniques ) > -1)
			{
				var query = $( '#query' ).val();
				if (!isEmpty(query)) 
				{
					$.ajax({
						type : "POST",
						url : dir+"/"+technique+"/search",
						data: {
							query: function() {
								return query;
							}},
						timeout : 100000,
						beforeSend: function() {
							$('#index-table > tbody').html("");
							$('#loader').show()
					    },
						success : function(response) {
							$(function() {
								var trHTML = '';
					            $.each(response, function(i, doc) {
					            	trHTML += '<tr><td>' + doc + '</td></tr>';
					            });
					            $('#loader').hide()
								$('#index-table').show()
					            $('#index-table').append(trHTML);
					        });
						},
						error : function(e) {
							$('#loader').hide()
							alert("We are sorry, Server didn't response please try again later");
						},
						done : function(e) {
							alert("done2 ");
						}
					});
				}
				else{
					alert("Please Enter valid Query");
				}
			}
	});
});

function isEmpty(value) {
	var res = value.split(",");
	if(res.length != 2)
		return true;
	else if(res[0].length < 3)
		return true;
	else if(res[1].length < 3)
		return true;
  return typeof value == 'string' && !value.trim() || typeof value == 'undefined' || value === null;
}

