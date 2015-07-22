/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.employee.interfaces.ProductService;
import com.boateng.abankus.service.impl.ProductServiceImpl;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
@Service
public class ProductServiceProcessor {

	@Autowired(required=true)
	@Qualifier(value="productServiceImpl")
	private ProductService productServiceImpl;

	public void processProduct(Product product){
		
		String productNumber = PlatformUtils.getProductNumber();
		product.setProductNumber(productNumber);
		
		productServiceImpl.saveProduct(product);
	}
	
	public void updateProductInfo(Product newProduct, String product){
		
	}
	
	public Set<Product> getAllProducts(){
		
		Set<Product> productList = productServiceImpl.getAllProducts();
		
		return productList;
	}
}
