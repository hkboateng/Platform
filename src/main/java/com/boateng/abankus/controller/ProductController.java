/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.employee.interfaces.ProductService;
import com.boateng.abankus.employee.service.EmployeeServiceImpl;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.service.impl.ProductServiceImpl;
import com.boateng.abankus.utils.PlatformUtils;

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
		if(bindingResult.hasErrors()){
			model.addAttribute("error", bindingResult.getFieldErrors());
			return "Product/createProduct"; 
		}

		log.info("Validation has passed..");
		
		productServiceProcessor.processProduct(product);
		log.info("Product: "+product.getProductName()+" has being saved successfully.");
		
		model.addAttribute("info","Product "+product.getProductName()+" has being saved successfully.");
		return "redirect:Product/createProduct";
	}
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String getProductList(Model model){
		
				
		return "Product/listProduct";
	}
	
}
