<%@ page import="com.boateng.abankus.utils.PlatformUtils" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<sf:form class="forms" name="paymentForm" action="submitOrderPayment"
	method="post">
	<div class="row">
		<div class="col-md-6">

			<label for="accountNumber">Order Number:</label> <input type="text"
				name="accountNumber" id="accountNumber" class="form-state  width-75"
				value="${order.orderNumber }" /> <label for="paymentType">Select Payment
				Type:</label> <select name="paymentType" id="paymentType" class="form-state"
				onchange="javascript:showBankInfoDiv(this);">
				<option value="cash">Cash</option>
				<option value="check">Bank Draft (Check)</option>

			</select>
			<div id="bankInfoDiv" class="hidden moveL_100">
				<label for="bankName">Name of Bank:</label> <input type="text"
					name="bankName" class="form-state width-100" id="bankName" value="" />
				<label for="bankAccountNumber">Account Number:</label> <input
					type="text" id="bankAccountNumber" name="bankAccountNumber"
					class="form-state  width-100" />
				<div class="help-text bold">Confirm Account Number</div>
				<input type="text" id="confirmAccountNumber"
					name="confirmAccountNumber" class="form-state  width-100" /> <label
					for="bankRoutingNumber">Routing Number:</label> <input type="text"
					id="bankRoutingNumber" name="bankRoutingNumber"
					class="form-state  width-100" /> <label for="bankCustName">Name
					on Bank Account:</label> <input type="text" name="bankCustName"
					class="form-state width-50" id="bankCustName" value="" />
			</div>
			<label for="paymentSchedule">Payment Schedule:</label> <select
				name="paymentSchedule" id="paymentSchedule"
				class="form-state  width-75"
				onchange="javascript:showPaymentDetails(this);">
				<option value="fullPayment">Full Payment</option>
				<option value="partialPayment">Partial Payment</option>
				<option value="installmentPayment">Installment Payment</option>

			</select>
			<div id="paymentDetails" class="hidden">
				<p>Hello EveryOne</p>
			</div>
			<label for="paymentAmount">Payment Amount:</label>
			 <input type="text"	name="paymentAmount" class="form-state  width-75" id="paymentAmount" value="" />
		</div>
		<div class="col-md-6">
			<label>Order Details</label><hr>
			<p><label>Total Cost:&nbsp; </label>$ ${order.totalAmount }</p>
	
			<p><label>Order Date:&nbsp;</label>${order.convertOrderDate() }</p>
			<p><label>Product Code:&nbsp;</label>${order.productCode}</p>
		</div>
		<div class="col-md-6">
			<h4>Order Transactions</h4>
			<c:choose>
			<c:when test="${not empty orderTransaction }">
			<table>
				<thead>
					<tr>
						<th>Date:</th>
						<th>Amount Paid:</th>
						<th>Payment Type</th>
					</tr>
				</thead>
				<c:forEach items="${orderTransaction}" var="orderTransaction">
				
				</c:forEach>
			</table>
			</c:when>
			<c:otherwise>
				<p>No past transaction is found for this order.</p>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<hr>

</sf:form>
<p>
	<button name="paymentSubmitBtn" id="paymentSubmitBtn"
		onclick="javascript:paymentConfirmation(document.paymentForm);return false;"
		class="btn btn-success">Continue with Payment</button>
</p>
<!-- Payment Summary Modal -->
<div class="modal fade" id="paymentSummaryModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Payment Summary and Confirmation</h4>
      </div>
      <div class="modal-body">
			<div class="" id="paymentSummary">
				<p><label for="accountNumberSummary">Order Number:</label><span id="accountNumberSummary"></span></p>
				<p><label for="paymentamountSummary">Payment Amount:</label><span id="paymentamountSummary"></span></p>
				<p><label for="paymentTypeSummary">Payment Type:</label><span id="paymentTypeSummary"></span></p>	
				<p><label for="paymentTypeSummary">Payment Type:</label><span id="paymentscheduleSummary"></span></p>	
				<p><label>Product Information:</label><span id="summaryProductInfo"></span></p>								
					<label for="customerPin">Customer PIN Number:</label>									
						<div id="customerPin-error" class="help-text-inline-error">
						</div>	
					<input type="password" name="customerPIN" id="customerPIN" class="form-state" />	
					<label for="customerPin">Customer PIN Number:</label>											
					<button name="btnSubmitPayment" id="btnSubmitPayment" onclick="" class="btn btn-success">Submit Payment</button><br>	
					<div class="help-text-inline">
						Click only once because clicking more than once will submit the payment more than once.
					</div>													
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<div class="col-md-6 hidden-xs hidden-sm hidden ">

						
	</div>	
	<script>
	
	$(document).ready(function(){
		
		$('#paymentAmount').mask('000,000.00', {reverse: true});
		
		$("#customerPIN").blur(function(){
			var isAlpha = isAlphaNumeric(this);
			if(!isAlpha){
				$("#customerPin-error").text("Please! enter valid PIN Number.");
				$(this).focus();
			}else{
				$("#customerPin-error").text("");
				$(this).focusout();				
			}
		});
			
	});
	
	/**
	 * Shows the Summary of Order Payment and pops up the Modal window.
	 */
	function paymentConfirmation(form){
		
		var accountnumber = $("#accountNumber").val() ;
		var paymentamount = $("#paymentAmount").val();
		var paymenttype = $("#paymentType").val();
		var paymentschedule = $("#paymentSchedule").val();
		
		if(isEmpty(paymentamount)){
			$("#paymentMessage").addClass('platform-alert-caution');
			getElementById("paymentMessage").innerHTML = "Payment Amount cannot by empty.";
		}else{
			getElementById("paymentMessage").innerHTML = "";
			$("#paymentMessage").removeClass('platform-alert-caution');
			
			$("#accountNumberSummary").html(accountNumber);
			$("#paymentamountSummary").html(paymentamount);
			$("#paymentTypeSummary").html(paymenttype.toUpperCase());
			$("#paymentscheduleSummary").html(paymentschedule.toUpperCase());
			// Openning Modal
			$("#paymentSummaryModal").modal({
				backdrop:"static"
			});			
		}

	}
	
	function findCustomerOrder(){
		var orderNumber = $("#searchOrderId").val();
		if(isEmpty(orderNumber)){
			$("#paymentMessage").addClass('platform-alert-caution');
			getElementById("paymentMessage").innerHTML = "Order Number cannot by empty.";			
		}
		if(isAlphaNumericAndDash(orderNumber)){
			$("#paymentMessage").addClass('platform-alert-caution');
			getElementById("paymentMessage").innerHTML = "You have entered invalid characters";		
		}
		
		$.ajax({
			url: 'findCustomerOrder',
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
	}
	</script>	