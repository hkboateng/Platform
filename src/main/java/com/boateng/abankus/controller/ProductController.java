/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.processors.ProductServiceProcessor;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	private final Logger log = Logger.getLogger(ProductController.class);
	@Autowired(required=true)
	private ProductServiceProcessor productServiceProcessor;
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String createProduct(Model model){
		
				
		return "Product/createProduct";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addNewProduct(@Valid Product product,BindingResult bindingResult,Model model){
		log.info("Validation has passed..");
		if(bindingResult.hasErrors()){
			model.addAttribute("error", bindingResult.getFieldErrors());
			return "Product/createProduct"; 
		}

		log.info("Validation has passed..");
		
		productServiceProcessor.processProduct(product);
		log.info("Product: "+product.getProductName()+" has being saved successfully.");
		
		model.addAttribute("info","Product "+product.getProductName()+" has being saved successfully.");
		return "redirect:listProduct";
	}
	
	@RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public String listProductList(Model model){
		List<Product> list = productServiceProcessor.getAllProducts();
		
		model.addAttribute("productList", list);
				
		return "Product/listProducts";
	}
	
	@RequestMapping(value="/editProduct", method=RequestMethod.GET)
	public String editProduct(@RequestParam int productId){
		
		return "Product/editProduct";
	}

	@RequestMapping(value="/updateProducts", method=RequestMethod.POST)
	public String updateProduct(@Valid Product product,BindingResult bindingResult,Model model){
		if(bindingResult.hasErrors()){
			model.addAttribute("error", bindingResult.getFieldErrors());
			return "Product/createProduct"; 
		}		
		
		model.addAttribute("info", null);
		return "Product/listProducts";
	}
	
	public void removeProduct(@RequestParam int productId, Model model){
		
	}
	
	@RequestMapping(value="/createProductDetails", method=RequestMethod.GET)
	public void createProductDetail(){
		
	}
	
	@RequestMapping(value="/saveProductDetail" , method=RequestMethod.POST)
	public String saveProductDetail(){
		
		return null;
	}
}
