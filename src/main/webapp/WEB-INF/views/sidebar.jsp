<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.boateng.abankus.fields.PlatformFields" %>
<style>
ul li {
	list-style:none;
}
#sidebar-list > li{
	border-bottom: 1px solid black;
	margin-bottom:10px;
	padding:10px 5px;
}
.sidebar.menu.heading{
	font-size:1em;
	font-weight:bold;
	
}
.sidebar.menu.heading:hover{
	cursor:pointer;
}
.sidebar-submenu > li{
	margin-bottom:5px;
	padding:5px;
	border-bottom: 1px solid black;
}
.sidebar-submenu > li:last-child{
	border-bottom: none;
}
#sidebar-list{
	padding:5px;
}
#sidebar-list.green{
	background-color:green;
	color:white;
}
#sidebar-list.blue{
	background-color:#0099FF;
	color:white;
}
</style>
					<div id="sidebar " class="">
						<ul id="sidebar-list" class="">
							<li>
							<span class="sidebar menu heading">
								<a href="/abankus/platform/dashboard"><i class="fa fa-home fa-lg moveR_20"></i>DashBoard</a>
							</span>
								
							</li>
							<li>
								<span class="sidebar menu heading">
								<i class="fa fa-users moveR_20"></i>Customer
								</span>
								<ul id="" class="sidebar-submenu moveL_20">
									<li>Add Customer</li>
									<li>Search Customers</li>
									<li><a href="<c:url value="/customers/listCustomer" />">List all Customers</a></li>
								</ul>							
							</li>
							<li>
								<span id="billPayment" class="sidebar menu heading">
									<i class="fa fa-money moveR_20"></i>Bill and Payments
								</span>
									<ul class="sidebar-submenu moveL_20">
										<li>
											<a href="<c:url value="/Payments/QuickPayment"/>#" class="" id="paymentExpand">
												Payments
											</a>
										</li>
										<li>View Bill History</li>
										<li>Quick Payment</li>
										<li>Search for Payment</li>
										<li>Search for Bill or Order</li>
									</ul>					
							</li>
							<li>
								<span id="" class="sidebar menu heading">
								<i class="glyphicon glyphicon-th-list moveR_20"></i>
								Products and Services
								</span>		
								<ul id="" class="sidebar-submenu moveL_20">
									<li><a href="/abankus/products/createProduct">Add Products</a></li>
									<li>Search for Products</li>
									<li><a href="<c:url value="/products/listProduct"/>">List all Products</a></li>
								</ul>				
							</li>														
							<li>
								<a href="<c:url value="/employee/listEmployee"/>">Staff</a>
							</li>
							<li>
							 	<a href="<c:url value="/Reports/index"/>" >Reports</a>
							</li>
							<li>
								<a href="<c:url value="/employee/employeSearchView"/>">Employee Search</a>
							</li>	
							<li>
								<a href="<c:url value="/platform/settings" />">
									<i class="glyphicon glyphicon-cog moveR_20"></i>Settings
							 	</a>
							</li>
							<li>
								<a href="<c:url value="/platform/logout"/>"><i class="glyphicon glyphicon-off moveR_20"></i>Log off</a>
							</li>			
						</ul>						
				
						

						<div class="list-group">

						  <div class="spaceBelow_10 ">
								<sf:form action="/abankus/customers/viewProfile" method="post">
									<label>Quick Search</label>
										<div class="spaceBelow_10">
											<label class="radio-inline">
											  <input type="radio" name="searchBill" id="customerDivId" value="customerIdDiv" checked> Customer Id:
											</label>
											<label class="radio-inline">
											  <input type="radio" name="searchBill" id="customerDivName" value="customerNameDiv"> Customer Name
											</label>
										</div>
										<div id="hideIdDiv" class="hidden">
											<label>Customer Id:</label><input type="text" name="customerId" class="form-state width-100" placeholder="Customer Id, Customer Number or Email Address" />
											
										</div>
										<div id="customerNameDiv" class="hidden">
											<label>First Name:</label><input type="text" name="firstname" class="form-state width-100">
											<label>Last Name:</label><input type="text" name="lastname" class="form-state width-100">
										</div>
										<input type="hidden" name="searchType" id="searchType" value="customerId">
										<button type="submit" class="btn btn-success btn-sm btn-block"><span class="glyphicon glyphicon-search moveR_20"></span>Search</button>
								</sf:form>	
	
						</div>	
					</div> 
				</div>
	    <script>
	    $(document).ready(function() {
	    	$("#hideIdDiv").removeClass('hidden');
	    	$('#paymentExpand').click(function(){
	    		$('#paymentDiv').toggle();
	    	});	 
			$('.sidebar-submenu').toggle();
			$('.sidebar').click(function(){
				$(this).next("ul").toggle('fast','swing');
			}); 	
			$(".mainmenu").click(function(){ 
				if($(this).children("div.submenu").css("display") == "none") {
					$(this).css('background-image', 'url(minus.png)');
					$(this).children("div.submenu").show();
				} else {
					$(this).css('background-image', 'url(plus.png)');
					$(this).children("div.submenu").hide();
				}
			});
			$('#customerDivId').on('click',function(){
				$('#searchType').val("customer");
				$("#hideIdDiv").removeClass('hidden');
				$("#customerNameDiv").addClass('hidden');
			});
			$('#customerDivName').on('click',function(){
				$('#searchType').val("customerName");
				$("#customerNameDiv").removeClass('hidden');
				$("#hideIdDiv").addClass('hidden');
				
				
			});
			$('#employee').on('click',function(){
				$('#searchType').val("employee");
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