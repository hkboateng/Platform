package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the products database table.
 * 
 * Product can be Service items also.
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;

	@NotNull
	private String description;

	@NotNull
	@Size(min=2, max=8)
	private String productCode;

	@NotNull
	private String productName;
	

	private String productNumber;

	public Product() {
	}

	
	/**
	 * @param description
	 * @param productCode
	 * @param productName
	 */
	public Product(String description, String productCode, String productName) {
		this.description = description;
		this.productCode = productCode;
		this.productName = productName;
	}


	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

}