function createRegionList(){
	var str = "";
		str += "<select name='state' class='form-state  width-100'>";
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
	str += "<select name='IdType' class='form-state  width-100'>";
	str += "<option value=''> Select Identification </option>";
	str += "<option value='healthInsurance'> National Health Insurance </option>";
	str += "<option value='passport'> Passport </option>";	
	str += "<option value='driverLicense'> Driver's Licence </option>";
	str += "</select>";
	
	document.write(str);	
}
function periodList(){
	var str = "";
	str += "<select name='period' class='form-state pull-right' onchange='javascript:changeAnalystics(this.value);'>";
	str += "<option value=''>Select Year </option>";
	str += "<option value='2015'>2015</option>";
	str += "<option value='2016'>2016</option>";	
	str += "<option value='2017'>2017</option>";
	str += "</select>";
	
	document.write(str);	
}
function displayMessage(result,messageDiv){
	$(messageDiv).addClass('platform-info-caution');
	$.each(result,function(key,value){
		messageDiv.text(result);
	});
}
function hideMessageDiv(message){
	message.removeClass('platform-alert-caution');
	message.removeClass('platform-info-caution ');	
	message.html(" ");
	message.addClass('hidden');
}

function showMessage(message,level,messageDiv){
	if(level === "alert"){
		messageDiv.addClass('platform-alert-caution');
	}else if(level === "info"){
		messageDiv.addClass('platform-info-caution ');	
	}
	messageDiv.html(message);
	messageDiv.removeClass('hidden');	
	window.scrollTo(0, 0);
}
function genderList(){
	var str = "";
	str += "<select name='gender' class='form-state width-100'>";
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

function formatAmount(elementId,input){
	if(isMoney(input.value)){
		var amount = 0.00;
		amount = parseFloat(input.value).toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
		if(amount === 'NaN'){
			amount = 'InValid';
		}
		$('#'+elementId).val(amount);
	}else{
		
	}
	
	
}
function isCharacter(word){
	return $.isNumeric(word);
}

function isMoney(str){
	return str.replace(/^\d+$/);
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
function showBankCardInfoDiv(select){
	if(select.value === "check"){
		$("#bankInfoDiv").removeClass('hidden');
		$("#cardInfoDiv").addClass('hidden');
	}else if(select.value === "card"){
		$("#cardInfoDiv").removeClass('hidden');
		$("#bankInfoDiv").addClass('hidden');
	}else{
		$("#bankInfoDiv").addClass('hidden');
		$("#cardInfoDiv").addClass('hidden');
	}
}
function submitCustomerURL(form,value){
	if( value !== undefined){
		form.customerNumber.value = value;
	}
	submitForm(form);
	
}

function submitForm(form){
	if(form){
		form.submit();
	}
}
function validateForm(formName){
	$(formName).validate({
		onsubmit:false,
		rules : {
			firstname: {
				required :true
			},
			lastname: {
				required :true
			},
			emailAddress: {
				required :true,
				email:true
			},
			phonenumber: {
				required :true,
				digits: true
			},
			association: {
				required :true
			},
			address : {
				required: true
			},
			zipcode: {
				required: true,
				zipCodeGh:true
			},
			city: {
				required: true
			},
			state: {
				required: true
			},
			phoneType: {
				required: true
			},
			companyname: {
				required:true
			},
			password: {
				required: true,
				maxlength:12
			},
			confirmpassword: {
				required: true,
				equalTo: '#password'
			},
			username : {
				required:true
			}
		},
		messages :{
			confirmpassword: {
				equalTo: 'Both Password\'s do not match'
			},
			state: {
				required: ''
			},
			zipcode: {
				zipCodeGh: 'Enter valid Zip or Postal Code.'
			}
		}
	});
	if(!$('form').valid())
		 return false;
	 return true;	
}
function validatePaymentForm(){
	$("#submitPayment").validate({
		onsubmit:false,
		rules : {
			amountPaid: {
				required :true,
				number:true
			},
			paymentMethod : {
				required: true
			},
			checkNumber : {
				required: true,
				digits: true
			},
			bankCustName : {
				required: true
			},
			bankRoutingNumber : {
				required: true,
				digits: true
			},
			bankAccountNumber: {
				required: true,
				digits: true
			},
			confirmAccountNumber: {
				required: true,
				equalTo: '#bankAccountNumber'
			},
			bankName: {
				required: true
			},
			nameOnCard : {
				required: true
			},
			securityNumber : {
				required : true
			},
			expirationDate : {
				required: true
			},
			cardNumber : {
				required:true,
				creditcard: true
			}
		},
		messages : {
			amountPaid : {
				required: "Enter the amount that the Customer is paying",
				numbers: "Enter a valid Payment Amount"
			},
			paymentMethod : {
				required: 'Select a Payment Method to continue'
			},
			checkNumber : {
				required : 'Enter the Check Number',
				digits: 'Enter a valid check number'
			},
			bankName : {
				required: "Enter the name of financial institution."
			},
			bankCustName: {
				required: "Enter the Name on the Account."
			},
			expirationDate : {
				required: "Exp. Date required"
			}
		}
	});
	if(!$('#submitPayment').valid())
		 return false;
	 return true;			
}