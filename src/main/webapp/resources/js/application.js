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