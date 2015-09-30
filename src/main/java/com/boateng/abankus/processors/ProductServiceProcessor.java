/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.boateng.abankus.customer.service.CustomerService;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.services.ProductService;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
@Service
public class ProductServiceProcessor {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceProcessor.class);
	
	@Autowired(required=true)
	@Qualifier(value="productServiceImpl")
	private ProductService productServiceImpl;
	
	@Autowired(required=true)
	private CustomerService customerServiceImpl;

	private static Map<String, Product> productMap = new ConcurrentHashMap<String, Product>();
	
	private static Map<String, Set<Product>> customerProductMap = new ConcurrentHashMap<String,Set<Product>>();
	
	public void processProduct(Product product){
		
		String productNumber = PlatformUtils.getProductNumber();
		product.setProductNumber(productNumber);
		
		product = productServiceImpl.saveProduct(product);
		addProductMap(product);
	}
	
	public void updateProductInfo(Product newProduct, String product){
		updateProductMap(newProduct);
	}
	public void  loadProductIntoSession(){
		logger.info("Loading Products into Session");
		productServiceImpl.getAllProducts();
	}
	public List<Product> loadProductIntoMap(){
		List<Product> productList = null;
		
			productList = productServiceImpl.getAllProducts();
			/**
			 * Add Products from 'productList' to productMap. The Key is productCode and
			 * value is Product object.
			 */			
			for(Product product: productList){
				productMap.put(product.getProductCode(), product);
			}			
		
		return productList;
		
	}
	public List<Product> getAllProducts(){
		
		List<Product> productList = null;
		if(productMap.isEmpty()){
			productList = loadProductIntoMap();
		}else{
			logger.info("Getting all Products Information...");
			productList = new ArrayList<Product>(productMap.values());
		}
		return productList;
	}

	public Map<String, Product> getProductMap(){
		return productMap;
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
		logger.info("Platform is searching for Product Details using Key: {}",productCode);
		List<Product> productList =  getAllProducts();
		Product product = null;
		for(Product prodt:productList){
			if(productCode.equalsIgnoreCase(prodt.getProductCode())){
				product = prodt;
				break;
			}
		}
		
		
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
		session.setAttribute("CUSTOMER_ORDER_LIST", customerProductMap);
	}

	private Product updateProductMap(Product product){
		return productMap.replace(product.getProductCode(), product);
	}
	
	private void addProductMap(Product product){
		if(!productMap.containsKey(product.getProductCode())){
			productMap.put(product.getProductCode(), product);
		}
	}

	/**
	 * @param customerpin
	 */
	public void authenticatePasscode(String customerpin) {
		customerServiceImpl.findCustomerByCustomerNumber("");
		BCrypt.checkpw(customerpin, "");
	}
}
