<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.boateng.abankus.fields.PlatformFields" %>
					<div id="sidebar " class="pad_10 bg-primary">
						<div class="list-group bg-primary">
							<a href="/abankus/platform/dashboard" class="list-group-item"><i class="fa fa-home fa-lg moveR_20"></i>DashBoard</a>
							<a href="<c:url value="/customers/listCustomer" />" class="list-group-item">
							<i class="fa fa-users moveR_20"></i>Customers
							  </a>
							  <a href="<c:url value="/Payments/receivePayment"/>" class="list-group-item"><i class="fa fa-money moveR_20"></i>Payments</a>
							  <a href="#" class="list-group-item">Morbi leo risus</a>
							  <a href="#" class="list-group-item">Porta ac consectetur ac</a>
							  <a href="#" class="list-group-item">Vestibulum at eros</a>					
						</div>
						<div class="list-group bg-primary">
							<a href="<c:url value="/platform/settings" />" class="list-group-item">
							<i class="glyphicon glyphicon-cog moveR_20"></i>Settings
							  </a>
							  <a href="<c:url value="/platform/logout"/>" class="list-group-item"><i class="glyphicon glyphicon-off moveR_20"></i>Log off</a>				
						</div>						
						<%--
						

						<div class="list-group bg-primary">
						  <a href="<c:url value="/customers/create"/>" class="list-group-item bg-primary">Add Customer<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  <c:if test="${not empty viewTransactionDetailsOrderNumber }">
						  	<a href="<c:url value="/customer/createOrders"/>" class="list-group-item">New Order<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  <button type="button" class="list-group-item">
						  	View Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i>
						  </button>
						  <button type="button" class="list-group-item spaceBelow_10">
						  	Print Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i>
						  </button>						 
						 
						  </c:if>
						  
						  <c:if test="${not empty billing }">
						  	<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class="list-group-item">Make A Payment<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
						  </c:if>
						  <a href="<c:url value="/customers/contactCustomer"/>" class="list-group-item">Send Email Message<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>

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
											<label>Customer Id:</label><input type="text" name="customerId" class="form-state width-100"/>
											
										</div>
										<div id="customerNameDiv" class="hidden">
											<label>First Name:</label><input type="text" name="firstname" class="form-state width-100">
											<label>Last Name:</label><input type="text" name="lastname" class="form-state width-100">
										</div>
										<div class="hidden" id="customerOrderDiv">
											<label>Order Number:</label><input type="text" name="OrderNumber" class="form-state width-100">
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerId">
										<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
								</sf:form>	
	
						</div>	
					</div> --%>
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