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
	
	$("#btnSearchCustomer").click(function(){
		$("#customerOrderForm").addClass("hidden");
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
					$("#pending").text(" ");
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
	$("#resultHeading").removeClass("hidden");
	$("#customerOrderForm").removeClass("hidden");
	$.each(results,function(index,row){
		$("#clientFullName").text(results.customer.firstname+" "+results.customer.lastname);
		//$("#clientFullName").text(results.accountNumber);
		$("#accountStatus").text(results.status);
		$("#customerId").val(results.customer.customerId);
	});
	if(results.customerActive == false){
		$("#pending").html("Customer is not eligible to make order");

	}
	
}

function displayErrorMessage(error){
	$("#resultHeading").removeClass("hidden");
	$("#pending").html("<p class='alert alert-danger'><span class='glyphicon glyphicon-info-sign  moveR_10'></span>"+error+"</p>");
}

function isCharacter(word){
	return $.isNumeric(word);
}

function isEmpty(str){
    return !str.replace(/^\s+/g, '').length; // boolean (`true` if field is empty)
}
function paymentConfirmation(form){
	
	var accountnumber = getElementById("orderNumber") ;
	var paymentamount = getElementById("paymentAmount");
	var paymenttype = getElementById("paymentForm");
	if(isEmpty(paymentamount.value)){
		$("#paymentMessage").addClass('platform-alert-caution');
		getElementById("paymentMessage").innerHTML = "Payment Amount cannot by empty.";
	}else{
		getElementById("paymentMessage").innerHTML = "";
		$("#paymentMessage").removeClass('platform-alert-caution');
	}
}

function showPaymentDetails(select){
	if(select.value == "installmentPayment"){
		$("#paymentDetails").removeClass('hidden');
	}else{
		$("#paymentDetails").addClass('hidden');
	}
}
/**
 * customer is the Customer PIN number
 * @param customer
 * @param customerId
 */
function validateCustomerCredential(customer,customerId){
	var valid = false;
	if(customer === '' || customer === null || customer === undefined){
		valid = false;
	}else if(customer.length < 6){
		valid = false;
	}else{
		$.trim(customer);
		$.ajax({
			url:'',
			data: {pin:customer,customerId:customerId},
			dataType: 'html'
		});
	}
	
	return valid;
}
function showBankInfoDiv(select){
	if(select.value === "check"){
		$("#bankInfoDiv").removeClass('hidden');
	}else{
		$("#bankInfoDiv").addClass('hidden');
	}
}
function submitCustomerURL(form,value){
	if( value !== undefined){
		form.customerNumber.value = value;
	}
	form.submit();
	
}