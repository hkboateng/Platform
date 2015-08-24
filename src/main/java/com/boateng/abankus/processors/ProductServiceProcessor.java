/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

	public void processProduct(Product product){
		
		String productNumber = PlatformUtils.getProductNumber();
		product.setProductNumber(productNumber);
		
		productServiceImpl.saveProduct(product);
	}
	
	public void updateProductInfo(Product newProduct, String product){
		
	}
	
	public List<Product> getAllProducts(){
		
		List<Product> productList = productServiceImpl.getAllProducts();
		
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
		Product product = productServiceImpl.findProductByProductCode(productCode);
		
		return product;
	}
	
	@SuppressWarnings("unchecked")
	public void addProductsToChart(String productCode,HttpSession session){
		Set<Product>  listProducts = (Set<Product>) session.getAttribute("LIST_OF_PRODUCTS_PER_SESSION");
		if(listProducts == null){
			listProducts = new LinkedHashSet<Product>();
		}
		Product product = findProductByProductCode(productCode);
		if(product != null){
			listProducts.add(product);
		}
		session.setAttribute("LIST_OF_PRODUCTS_PER_SESSION", listProducts);
	}
}
