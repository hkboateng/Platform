/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String addNewProduct(@Valid Product product,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributess){
		log.info("Started processing product information to save into Database........");
		if(bindingResult.hasErrors()){
			redirectAttributess.addFlashAttribute("error", bindingResult.getFieldErrors());
			return "redirect:/products/createProduct"; 
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

	@RequestMapping(value="/viewProduct/{productCode}", method=RequestMethod.GET)
	public String viewProduct(@PathVariable String productCode,Model model){
		Product product = productServiceProcessor.findProductByProductCode(productCode);
		model.addAttribute("product", product);
		return "Product/ViewProductDetail";
	}
	
	@RequestMapping(value="/updateProducts", method=RequestMethod.POST)
	public String updateProduct(HttpServletRequest request,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributess){
		if(bindingResult.hasErrors()){
			model.addAttribute("error", bindingResult.getFieldErrors());
			return "Product/ViewProductDetail"; 
		}		
		
		model.addAttribute("info", null);
		return "Product/listProducts";
	}
	
	public void removeProduct(@RequestParam int productId, Model model){
		//TODO 
	}
}
