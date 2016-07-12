$(document).ready( function(){
	$( '#command' ).on('submit', function(event){
		event.preventDefault();// stop submitting and refresh of page
		data = JSON.stringify(serializeObject($('#command')));
		if(!validate(data)){
			$('#result').removeClass();
			$('#result').addClass('errorMessage');
			return;
		}
		//alert(data);
		$.ajax({
	 		url: '/addStudentAjax',
	 		type: 'POST',
			data: data,
			contentType: 'application/json',
			success: function(message){
				console.log('message received: ')
				console.log(message);
				$("#result").html(message);
				$('#result').removeClass();
				$('#result').addClass('successMessage');
			},
			error: function(err){			
				console.log(err);
				alert('Error while request..' + err);
			}
		});
	});
	
	function validate(data){
		var data2 = eval( "(" + data + ")" );
		
		if(data2.age < 20){
			$("#result").html("Student with age less then 20 are not allowed");
			return false;
		}else if (data2.id > 10){
			$("#result").html("The id could not be greater then 10");
			return false;
		}
		return true;
	}
	
	function serializeObject (form){
	    var jsonObject = {};
	    var a = form.serializeArray();
	    $.each(a, function() {
	        if (jsonObject[this.name] !== undefined) {
	            if (!jsonObject[this.name].push) {
	            	jsonObject[this.name] = [jsonObject[this.name]];
	            }
	            jsonObject[this.name].push(this.value || '');
	        } else {
	        	jsonObject[this.name] = this.value || '';
	        }
	    });
	    return jsonObject;
	};
	
});