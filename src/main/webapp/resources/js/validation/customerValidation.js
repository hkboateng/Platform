var emailRegExp = new RegExp();

var zipCodeRegExp = new Reg('((\d{5})(-\d{4})?)');

var validName = new RegExp('');
function validation(){
		$("form").validate({
			onsubmit:false,
			rules :{
				firstname: "required",
				lastname: "required",
				middlename: {
					required: true
				},
				gender : {
					required: true
				},
				IdType: {
					required: true
				},
				IdNumber : {
					required: true
				},
				company_name: {
					required: false
				},
				address1: {
					required: true
				},
				address2: {
					required: false
				},
				city: {
					required: true
				},
				state: {
					required: true
				},
				zipcode: {
					required: true
				},
				phoneNumber: {
					required: true
				},
				emailAddress: {
					required: true,
					email: true
				},
				accountStatus: {
					required: true
				},
				customerIndustry: {
					required: true
				},
				username : {
					required:true
				}
			},
			messages: {
				firstname : {
					required: "Your First Name is a required field."
				}
			}
		});
		if(!$('form').valid())
			 return false;
		 return true;

	}
	
	function isEmailValid(email){
		
	}
	function isEmailUnique(email){
		$.ajax({
			
		});
	}
	
	function customerValidation(form){
		
		if(validation()){
			form.submit();
		}
	}
