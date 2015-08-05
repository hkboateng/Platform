function createRegionList(){
	var str = "";
		str += "<select name='state' class='form-control'>";
		str += "<option value=''> Select </option>";
		str += "<option value='AR'> Ashanti </option>";
		str += "<option value='BA'> Brong-Ahafo </option>";
		str += "<option value='CR'> Central </option>";
		str += "<option value=ER'> Eastern </option>";
		str += "<option value='GAR'> Greater Accra </option>";
		str += "<option value='NR'> Northern </option>";
		str += "<option value='UER'> Upper-East </option>";
		str += "<option value='UWR'> Upper West </option>";
		str += "<option value='VR'> Volta </option>";
		str += "<option value='WR'> Western </option>";
		str += "</select>";
		
		document.write(str);
}

function genderList(){
	var str = "";
	str += "<select name='gender' class='form-control'>";
	str += "<option value=''> Select </option>";
	str += "<option value='male'> Male </option>";
	str += "<option value='female'> Female </option>";	
	str += "</select>";
	
	document.write(str);	
}

/*jQuery time*/
$(document).ready(function(){
	
	$("#btnSarchCustomer").click(function(){
		var accountNo = $("#accountNumber").val();
		
		$.ajax({
			url: 'findCustomer',
			data: {
				accountNumber:accountNo,
			},
			dataType: "json",
			beforeSend:function(){
				$("#pending").text("Loading...");
			},
			success: function(results){
				
				if(results == null){
					results = "No Account Information was found";
				}
				$("#pending").text(results);
			},
			error :function(data){
				console.log(data);
			}
		});			
	});
	
	$("#accordian h3").click(function(){
		$("#accordian ul ul").slideUp();
		if(!$(this).next().is(":visible"))
		{
			$(this).next().slideDown();
		}
	});
})

