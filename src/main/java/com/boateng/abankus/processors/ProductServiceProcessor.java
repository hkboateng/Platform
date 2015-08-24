/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boateng.abankus.customer.service.Client;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.service.impl.ProductServiceImpl;
import com.boateng.abankus.services.ProductService;
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

	private static Map<String, Product> productMap = new ConcurrentHashMap<String, Product>();
	
	private static Map<String, Set<Product>> customerProductMap = new ConcurrentHashMap<String,Set<Product>>();
	
	public void processProduct(Product product){
		
		String productNumber = PlatformUtils.getProductNumber();
		product.setProductNumber(productNumber);
		
		productServiceImpl.saveProduct(product);
	}
	
	public void updateProductInfo(Product newProduct, String product){
		
	}
	
	public List<Product> getAllProducts(){
		
		List<Product> productList = productServiceImpl.getAllProducts();
		/**
		 * Add Products from 'productList' to productMap. The Key is productCode and
		 * value is Product object.
		 */
		for(Product product: productList){
			productMap.put(product.getProductCode(), product);
		}
		return productList;
	}

	public Map<String,Product> listAllProduct(){
		Map<String,Product> productList = null;
		List<Product> products = getAllProducts();
		if(products != null){
			productList = new LinkedHashMap<String,Product>();
			for(Product product: products){
				productList.put(product.getProductCode(),product);
			}
		}
		return productList;
	}
	
	public Product findProductByProductCode(String productCode){
		Product product = productMap.get(productCode);
		
		return product;
	}
	
	public void addProductsToChart(String productCode,String customerAccount,HttpSession session){
		session.getAttribute("CUSTOMER_ORDER_LIST");
		Set<Product>  listProducts = null;

		Product product = findProductByProductCode(productCode);
		
		if(customerProductMap.containsKey(customerAccount)){
			listProducts = customerProductMap.get(customerAccount);
			listProducts.add(product);
		}else{
			listProducts = new HashSet<Product>();
			listProducts.add(product);
			
			
		}
		customerProductMap.put(customerAccount, listProducts);
		System.out.println(customerProductMap.size());
		session.setAttribute("CUSTOMER_ORDER_LIST", customerProductMap);
	}

}
