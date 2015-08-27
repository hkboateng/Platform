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

function typeOfIdentification(){
	var str = "";
	str += "<select name='IdType' class='form-control'>";
	str += "<option value=''> Select Identification </option>";
	str += "<option value='healthInsurance'> National Health Insurance </option>";
	str += "<option value='passport'> Passport </option>";	
	str += "<option value='driverLicense'> Driver's Licence </option>";
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
		if(isCharacter(accountNo )){
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
					populateOrderForm(results);
				},
				error :function(data){
					displayErrorMessage(data.responseText);
				}
			});			
		}else{
			displayErrorMessage("Value you enttered is invalid");
		}
			
	});
	
	$("#accordian h3").click(function(){
		$("#accordian ul ul").slideUp();
		if(!$(this).next().is(":visible"))
		{
			$(this).next().slideDown();
		}
	});
})

function populateOrderForm(results){
	console.log(results);
	var t = "";
	$.each(results,function(index,row){
		$("#clientFullName").text(results.accountNumber);
		$("#accountStatus").text(results.status);
		$("#customerAccount").val(results.accountNumber);
		console.log(index+" : "+row);
	});
	if(results.customerActive == false){
		$("#pending").html("Customer is not eligible to make order");

	}
	
}

function displayErrorMessage(error){
	$("#pending").html("<p class='alert alert-danger'><span class='glyphicon glyphicon-info-sign  moveR_10'></span>"+error+"</p>");
}

function isCharacter(word){
	return $.isNumeric(word);
}