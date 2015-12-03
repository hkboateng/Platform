<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.boateng.abankus.fields.PlatformFields" %>
					<div id="sidebar " class=''>
						<div class="list-group">
						  <a href="<c:url value="/customers/create"/>" class="list-group-item">Add Customer<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  <c:if test="${not empty viewTransactionDetailsOrderNumber }">
						  	<a href="<c:url value="/customer/createOrders"/>" class="list-group-item">New Order<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  </c:if>
						  
						  <c:if test="${not empty billing }">
						  	<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class="list-group-item">Make A Payment<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  </c:if>
						  <a href="<c:url value="/customers/contactCustomer"/>" class="list-group-item">Send Email Message<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  <button type="button" class="list-group-item">
						  	View Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i>
						  </button>
						  <button type="button" class="list-group-item spaceBelow_10">
						  	Print Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i>
						  </button>
						  <div class="spaceBelow_10 ">
								<sf:form action="/abankus/customers/viewProfile" method="post">
									<label>Quick Search</label>
										<div class="spaceBelow_10">
											<label class="radio-inline">
											  <input type="radio" name="searchBill" id="customerId" value="customerIdDiv" checked> Customer Id:
											</label>
											<label class="radio-inline">
											  <input type="radio" name="searchBill" id="customerName" value="customerNameDiv"> Customer Name
											</label>
											<label class="radio-inline">
											  <input type="radio" name="searchBill" id="customerOrder" value="customerOrderDiv"> Order #:
											</label>
										</div>
										<div id="customerIdDiv" class="">
											<label>Customer Id:</label><input type="text" name="customerId" class="form-state"/>
											
										</div>
										<div id="customerNameDiv" class="hidden">
											<label>First Name:</label><input type="text" name="firstname" class="form-state">
											<label>Last Name:</label><input type="text" name="lastname" class="form-state">
										</div>
										<div class="hidden" id="customerOrderDiv">
											<label>Order Number:</label><input type="text" name="OrderNumber" class="form-state">
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerId">
										<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
								</sf:form>	
					        <sf:form name="makeCustomerPayment" method="post" action="">
					          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
					        </sf:form>			
						</div>	
					</div>
				</div>
	    <script>
	    $(document).ready(function() {
			$(".mainmenu").click(function(){ 
				if($(this).children("div.submenu").css("display") == "none") {
					$(this).css('background-image', 'url(minus.png)');
					$(this).children("div.submenu").show();
				} else {
					$(this).css('background-image', 'url(plus.png)');
					$(this).children("div.submenu").hide();
				}
			});
			$('#customerId').on('click',function(){
				$('#searchType').val("customerId");
				$("#customerIdDiv").removeClass('hidden');
				$('#customerNameDiv').addClass('hidden');
				$('#customerOrderDiv').addClass('hidden');
			});
			$('#customerName').on('click',function(){
				$('#searchType').val("customerName");
				$('#customerNameDiv').removeClass('hidden');
				$("#customerIdDiv").addClass('hidden');
				$('#customerOrderDiv').addClass('hidden');
			});
			$('#customerOrder').on('click',function(){
				$('#searchType').val("customerId");
				$('#customerOrderDiv').removeClass('hidden');
				$("#customerIdDiv").addClass('hidden');
				$('#customerNameDiv').addClass('hidden')
				
			});				
		});


	function makePayment(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}
		form.action="/abankus/customers/"+url
		form.submit();
	}
	
	function orderHistory(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}		
		form.action = "/abankus/client/"+url;
		form.submit();
	}
	
	function createOrder(url,form){
		if(isEmpty(url) || !isAlphaNumeric(url)){
			return false;
		}			
		
		form.action = "/abankus/client/"+url;
		form.submit();
	}
	</script>			  