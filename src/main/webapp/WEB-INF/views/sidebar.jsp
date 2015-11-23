<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="sidebar">
<div class="list-group">
  <a href="<c:url value="/customers/create"/>" class="list-group-item">Add Customer<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
  <a href="<c:url value="/customer/createOrders"/>" class="list-group-item">New Order<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
  <a href="javascript:makePayment('makePayment',document.makeCustomerPayment);" class="list-group-item">Make A Payment<i class="glyphicon glyphicon-chevron-right pull-right"></i></a>
  <button type="button" class="list-group-item">View Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i></button>
  <button type="button" class="list-group-item">Print Invoice<i class="glyphicon glyphicon-chevron-right pull-right"></i></button>
  <div class="list-group-item">
  <sf:form action="/abankus/customers/viewProfile" method="post">
				<label>Quick Search</label>
					<div class="spaceBelow_10">
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Name
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Account No.
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> Email
					</label>
					</div>
				<input type="text" name="customerId" class="form-state"/>
				<button type="submit" class="btn btn-success btn-sm btn-block">
				<span class="glyphicon glyphicon-search moveR_20"></span>Search
				</button>
				<input type="hidden" name="searchType" id="searchType" value="customerId">
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