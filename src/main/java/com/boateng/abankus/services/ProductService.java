/**
 * hkboateng
 */
package com.boateng.abankus.services;

import java.util.List;
import java.util.Set;

import com.boateng.abankus.domain.Product;

/**
 * @author hkboateng
 *
 */
public interface ProductService {


	/**
	 * @param product
	 * @return
	 */
	Product saveProduct(Product product);

	/**
	 * @param productId
	 * @return
	 */
	Product updateProduct(int productId);

	/**
	 * @param productNumber
	 * @return
	 */
	boolean isProductNumberUnique(String productNumber);

	/**
	 * @return
	 */
	List<Product> getAllProducts();
	
	public Product findProductByProductCode(String productCode);
}
