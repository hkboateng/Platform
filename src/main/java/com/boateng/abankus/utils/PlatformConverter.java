/**
 * hkboateng
 */
package com.boateng.abankus.utils;

import java.io.IOException;
import java.util.List;

import com.boateng.abankus.domain.CustomerTransaction;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */
public class PlatformConverter {

	public static String convertTransactionToString(List<CustomerTransaction> transaction) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, transaction);
		String convert = mapper.writeValueAsString(transaction);
		return convert;
	}
}
