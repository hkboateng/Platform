<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="sidebar">
	<div id="popular" class="mainmenu">
		<a href="#">Client/Customer Services</a>
		<div class="submenu">
			<ul>
				<li><a href="/abankus/client/createOrders"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Create Orders</a></li>
				<li><a href="<c:url value="/customers/create"/>">New Customer</a></li>
				<li><a href="#">Send Message to Customer</a></li>
				<li><a href="#">Golf Set</a></li>
			</ul>
		</div>
	</div>
		<c:set var="isCustomer" value="${not empty customer  }"/>
		<c:if test="${isCustomer}">
			<div id="recent" class="mainmenu">
				<a href="#">Transactions</a>
				<div class="submenu">
					<ul id="" >
				       <li><a href="#"><span class="glyphicon glyphicon-list-alt moveR_20"></span>Transaction History</a></li>
					   <li>
						<a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class=""><i class="fa fa-money moveR_20"></i>Make A Payment</a>  				 	
					   </li>	
					   <li>
						 <a href="javascript:submitCustomerURL('orderHistory',document.formOrderHistroy);" ><i class="fa fa-exchange moveR_20"></i>View Customer Order History</a>
					   </li>
					   <li>
						  <a href="javascript:submitURL('createCustomerOrder','document.createCustomerOrder');"><i class="fa fa-exchange moveR_20"></i>Add New Order</a>
						</li>	
				     </ul>
				</div>
			</div>
			<sf:form  name="formOrderHistroy" method="post" action="">
			</sf:form>
	        <sf:form name="frmUpdateAccountStatus" method="post" action="updateAccountStatus">
	          	  <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>	          
	        </sf:form>
	        <sf:form name="makeCustomerPayment" method="post" action="">
	          <input type="hidden" value="${customer.customerNumber }" name="customerNumber"/>
	        </sf:form>			
		</c:if>


	<div id="recent" class="mainmenu">
		<a href="#"><i class="glyphicon glyphicon-tasks moveR_20"></i>Bill and Payments</a>
		<div class="submenu">
			<ul id="" >
		       <li><a href="/abankus/client/createOrders"><i class="fa fa-pencil-square-o fa-lg moveR_5"></i>Create Orders</a></li>
		       
		       <li><a href="/abankus/customers/listCustomer"><i class="fa fa-sign-out fa-lg moveR_5"></i>List Customers</a></li>
		     </ul>
		</div>
	</div>	
	<div id="category" class="mainmenu">
		<a href="#"><span class="glyphicon glyphicon-cog moveR_20"></span>Account Settings</a>
		<div class="submenu">
			<ul>
			<%-- Customer Account Settings --%>
				<li><a href="#">Update Personal Information</a></li>
				<li><a href="#">Update Account</a></li>
				<li><a href="#">Magnet Toys</a></li>
				<li><a href="#">Soft Toys</a></li>
			</ul>
		</div>
	</div>
	<div id="loginStatus" class="mainmenu">
	<a href="#">Sign Out</a>
	<div class="submenu">
	<ul>
		<li>
			<a href="#">Log off</a>
		</li>
	</ul>
	</div>
	</div>
	<div class="pad_10">
		<sf:form action="/abankus/customers/viewProfile" method="get">
			<label>Quick Search</label>
			<div class="spaceBelow_10">
			<label class="radio-inline">
			  <input type="radio" name="customerId" id="customerId" value="customerIdDiv"> Customer Id
			</label>
			<label class="radio-inline">
			  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Account No.
			</label>
			<label class="radio-inline">
			  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> Email
			</label>
			</div>
			<input type="text" name="customerId" class="form-state"/>
			<input type="hidden" name="searchType" id="searchType" value="customerId">
			<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
		</sf:form>
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