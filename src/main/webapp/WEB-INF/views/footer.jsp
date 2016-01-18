	<footer class="footer">
		<div class="container">
			<p>Copyright &copy; 2015</p>
		</div>		
	</footer>
	    <script>
	    $(document).ready(function() {

			$('#customer').on('click',function(){
				$('#searchType').val("customer");
				$("#customerIdDiv").removeClass('hidden');
				$('#customerNameDiv').addClass('hidden');
				$('#customerOrderDiv').addClass('hidden');
			});
			$('#customerPayments').on('click',function(){
				$('#searchType').val("customerPayments");
				$('#customerNameDiv').removeClass('hidden');
				$("#customerIdDiv").addClass('hidden');
				$('#customerOrderDiv').addClass('hidden');
			});
			$('#employee').on('click',function(){
				$('#searchType').val("employee");
				$('#customerOrderDiv').removeClass('hidden');
				$("#customerIdDiv").addClass('hidden');
				$('#customerNameDiv').addClass('hidden')
				
			});		
			 

		});
		</script>