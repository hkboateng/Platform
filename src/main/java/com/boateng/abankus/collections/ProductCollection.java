/**
 * hkboateng
 */
package com.boateng.abankus.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boateng.abankus.domain.Product;

/**
 * @author hkboateng
 *
 */
public class ProductCollection {
	private  Map<String, Product> productMap = null;
	
	
	/**
	 * @return the productMap
	 */
	public Map<String, Product> getProductMap() {
		return productMap;
	}

	/**
	 * @param productMap the productMap to set
	 */
	public void setProductMap(Map<String, Product> productMap) {
		this.productMap = productMap;
	}

	public void addProduct(String productCode, Product product){
		if(productCode !=null && product !=null){
			productMap.putIfAbsent(productCode, product);
		}
	}
	
	public boolean contains(String productCode){
		return productMap.containsKey(productCode);
	}
	
	public Product findProduct(String productCode){
		return productMap.get(productCode);
			
	}

	
	
}
