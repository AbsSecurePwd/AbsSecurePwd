/**
 * 
 */

$(document).ready(function(){
	
		$("#Authentication_0").button(); //enabling jQuery submit UI button
		$("#Authentication_0").css("height","2em");
		$("#Authentication_0").css("width","6em");
		
		
		var message = $("#message").val(); //get value of the message
		if( message != ""){ //if not empty fire function
			$("#message-dialog").children('p').append(message); //append message to div 
			$("#message-dialog").dialog({ //fire modal window
			      modal: true,
			      buttons: {
			        Ok: function() {
			          $(this).dialog( "close" );
			        }
			      }
			 });
		}
	});
	
	//if( $("#message").val() != ""){
	//   alert($("#message").val());
	//}	
