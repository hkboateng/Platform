function createRegionList(){
	var str = "";
		str += "<select name='state' class='form-control'>";
		str += "<option value=''> Select </option>";
		str += "<option value='ashanti'> Ashanti </option>";
		str += "<option value='brong'> Brong-Ahafo </option>";
		str += "<option value='central'> Central </option>";
		str += "<option value='eastern'> Eastern </option>";
		str += "<option value='accra'> Greater Accra </option>";
		str += "<option value='north'> Northern </option>";
		str += "<option value='uppereast'> Upper-East </option>";
		str += "<option value='upperwest'> Upper West </option>";
		str += "<option value='volta'> Volta </option>";
		str += "<option value='western'> Western </option>";
		str += "</select>";
		
		document.write(str);
}

function genderList(){
	var str = "";
	str += "<select name='gender' class='form-control'>";
	str += "<option value=''> Select </option>";
	str += "<option value='male'> Male </option>";
	str += "<option value='female'> Female </option>";	
	str += "</select>";
	
	document.write(str);	
}
var platformApp = angular.module('platformApp', []);

platformApp.controller('ProductController', function ($scope) {

  $scope.name = "hubert";

});

var platformEmpoyee= angular.module('platformEmpoyee', []);

platformEmpoyee.controller('ProductController', function ($scope) {

  $scope.name = "hubert";

});
/*jQuery time*/
$(document).ready(function(){
	$("#accordian h3").click(function(){
		//slide up all the link lists
		$("#accordian ul ul").slideUp();
		//slide down the link list below the h3 clicked - only if its closed
		if(!$(this).next().is(":visible"))
		{
			$(this).next().slideDown();
		}
	})
})