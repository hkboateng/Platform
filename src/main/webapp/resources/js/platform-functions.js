function isCharacter(word){
	return $.isNumeric(word);
}
function getElementById(id){
	if(id){
		var element = document.getElementById(id);
		
		return element;	
	}
}
/** Checks if the String is null or is empty spaces**/
function isEmpty(str){
    return !str.replace(/^\s+/g, '').length; // boolean (`true` if field is empty)
}

//Checks if the String is blank
function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}
/**
 * How it Works
 * 			^         start of string
			[a-z0-9]  a or b or c or ... z or 0 or 1 or ... 9
			+         one or more times (change to * to allow empty string
			$         end of string
			
			/i        case-insensitive
 * @param str
 * @returns
 */
function isAlphaNumeric(str){
	var alphanumeric = /^[a-zA-Z0-9]+$/i;
	return this.isBlank(str) && alphanumeric.test(str);
}

/**
 *   case-insensitive
 * @param str
 * @returns
 */
function isAlphaNumericAndDash(str){
	var alphanumeric = /^[a-zA-Z0-9-]+$/i;
	return alphanumeric.test(str);
}
function toTitleCase(str) {
    return str.replace(/(?:^|\s)\w/g, function(match) {
        return match.toUpperCase();
    });
}
function submitCustomerURL(url,form){
	if(isEmpty(url) || !isAlphaNumeric(url)){
		return false;
	}	
	form.action = "/abankus/customer/"+url;
	form.submit();		
}
function commaSeparateNumber(val){
    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    return val;
  }

function getRadioBtnValue(elementId,ele){
	$.each($(elementId), function (idx,group){
		 var checked = $(group).find('[type=radio]:checked');
		 if(checked){
			 var title = checked.attr('title'); 
			 $(ele).html(title);
		 }
	});
}