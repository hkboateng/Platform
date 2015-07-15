/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.service.impl.ProductServiceImpl;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired(required=true)
	private ProductServiceImpl productServiceImpl;
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.GET)
	public String createProduct(Model model){
		
				
		return "Product/createProduct";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addNewProduct(@Valid Product product,BindingResult result){
		if(result.hasErrors()){
			
		}

		return null;
	}
}
