/**
 * hkboateng
 */
package com.boateng.abankus.utils;

/**
 * @author hkboateng
 *
 */
public class NumberFormatUtils {

	public static double parseDoubleAmount(String s,char c){
		if(s  == null ){
			throw new IllegalArgumentException();
		}
		return Double.parseDouble(s.replace(c, ' ').trim());
	}
}
