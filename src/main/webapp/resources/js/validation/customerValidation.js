function employeeFormValidation(formId){
	try{
	$(formId).validate({
		onsubmit:false,
		debug : true,
		rules :{
			firstname: {
				required: true
			},
			lastname: {
				required: true
			},
			gender : {
				required: true
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
				required: true,
				zipCodeGh:true
			},
			phoneNumber: {
				required: true
			},
			emailAddress: {
				required: true
			}
		},
		messages: {
			firstname : {
				required: "Your First Name is a required field."
			},
			zipcode : {
				zipCodeGh : "Enter a valid 5-digit postal code."
			}
		}
	});
	if(!$(formId).valid())
		 return false;
	 return true;
	 
	}catch(err){
		console.log(err)
	}
}
function validation(){
		$("form").validate({
			onsubmit:false,
			rules :{
				firstname: {
					required: true
				},
				lastname: {
					required: true
				},
				middlename: {
					optional: true
					//validname: true
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
					required: true,
					zipCodeGh:true
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
	
	function validateCustomerNumberSearch(){
		try{
			$("#searchCustomerNumberForm").validate({
				onsubmit:false,
				rules: {
					customerIdentity: {
						required: true
					}
				},
				messages: {
					customerIdentity: {
						required: "You must enter a value to search for Customer"
					}
				}
			});
			
			if(!$("#searchCustomerNumberForm").valid())
				 return false;
			 return true;			
		}catch(err){
			console.log(err);
		}
	}
	function validateCustomerNameSearch(){
		$("#searchCustomerNameForm").validate({
			
			onsubmit:false,
			rules: {
				firstName: {
					required: true
				},
				lastName: {
					required: true
				}
			},
			messages: {
				firstName: {
					required: "You must enter a value Customer First Name "
				},
				lastName: {
					required: "You must enter a value Customer Last Name "
				}
			}
		});
		if(!$('#searchCustomerNameForm').valid())
			 return false;
		 return true;
		 
		 
	}
	function validateCustomerPaymentSearch(){
		$('#searchCustomerPaymentForm').validate({
			onsubmit:false,
			rules : {
				customerId : {
				},
				transactionFrom  : {
					required: true
				},
				transactionTo : {
					required: true
				}			
			},
			messages : {
				customerId : {
					
				},
				transactionFrom: {
					required: "Select Payment Transaction Date From:"
				},
				transactionTo: {
					required: "Select Payment Transaction Date To:"
				}
			}
		});
		if(!$('#searchCustomerPaymentForm').valid())
			 return false;
		 return true;
	}

	function validateEmployeePaymentSearch(){
		$('#searchEmployeePaymentForm').validate({
			debug: true,
			onsubmit:false,
			rules: {
				employee : {
					digits:true
				},
				empTransactionFrom: {
					require_from_group : [1,".employee_search"]
				},
				empTransactionTo : {
					require_from_group : [1,".employee_search"]
				}
			},
			messages: {
				employee: {
					require_from_group: "You can search by Employee Number or Email Address."
				},
				empTransactionFrom: {
					require_from_group: "Select Payment Transaction Date From:",
					required: true
				},
				empTransactionTo: {
					require_from_group: "Select Payment Transaction Date To:",
					required: true
				}
			}
		});
		if(!$('#searchEmployeePaymentForm').valid())
			 return false;
		 return true;
	}