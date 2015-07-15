/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.service.impl.ProductServiceImpl;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
public class ProductServiceProcessor {

	@Autowired(required=true)
	private ProductServiceImpl productServiceImpl;
	
	public ProductServiceProcessor(){
		
	}
	public void processProduct(Product product){
		
		String productNumber = PlatformUtils.getProductNumber();
		product.setProductNumber(productNumber);
		
		productServiceImpl.saveProduct(product);
	}
	
	public void updateProductInfo(Product newProduct, String product){
		
	}
}
