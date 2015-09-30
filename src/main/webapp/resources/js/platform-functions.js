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
	return alphanumeric.test(str);
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
