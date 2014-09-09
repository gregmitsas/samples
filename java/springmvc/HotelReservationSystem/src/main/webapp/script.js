$(document).ready(
			function(){
				$.getJSON('http://localhost:8080/HotelReservationSystem/roomType.json', {
					ajax : 'true'
				}, function(data){
					var html = '<option value="">Select Type</option>';
					var length = data.length;
					for(var i=0; i<length; i++)
					{
						html += '<option value="' + data[i].description + '">' + data[i].description + '</option>';
					}
					html += '</option>';
					$('#roomType').html(html);
				});
			});

$(document).ready(
			function(){
				$.getJSON('http://localhost:8080/HotelReservationSystem/paymentMethod.json', {
					ajax : 'true'
				}, function(data){
					var html = '<option value="">Select Method</option>';
					var length = data.length;
					for(var i=0; i<length; i++)
					{
						html += '<option value="' + data[i].description + '">' + data[i].description + '</option>';
					}
					html += '</option>';
					$('#paymentMethod').html(html);
				});
			});

$(document).ready(
		function(){
			$.getJSON('http://localhost:8080/HotelReservationSystem/mealPlan.json', {
				ajax : 'true'
			}, function(data){
				var html = '<option value="">Select Plan</option>';
				var length = data.length;
				for(var i=0; i<length; i++)
				{
					html += '<option value="' + data[i].description + '">' + data[i].description + '</option>';
				}
				html += '</option>';
				$('#mealPlan').html(html);
			});
		});