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
@NamedQueries({
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
@NamedQuery(name="Product.findProductByCode", query="SELECT p FROM Product p where p.productCode=:productCode"),
@NamedQuery(name="Product.findProductBProductId", query="SELECT p FROM Product p where p.productCode=:productId")
})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;

	@NotNull (message="Product Description cannot be null or empty.")
	private String description;

	@NotNull(message="Product Code cannot be null or empty.")
	@Size(min=2, max=8, message="Product Code should be 2 and 8 characters.")
	private String productCode;

	@NotNull(message="Product Name cannot be null or empty.")
	private String productName;
	

	private String productNumber;

	@NotNull
	private float unitCost;
	
	/**
	 * @return the unitCost
	 */
	public float getUnitCost() {
		return unitCost;
	}


	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}


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

	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(getProductCode());
		str.append(" - ");
		str.append(getProductName());
		return str.toString();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((productNumber == null) ? 0 : productNumber.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (productCode == null) {
			if (other.productCode != null) {
				return false;
			}
		} else if (!productCode.equals(other.productCode)) {
			return false;
		}
		if (productId == null) {
			if (other.productId != null) {
				return false;
			}
		} else if (!productId.equals(other.productId)) {
			return false;
		}
		if (productNumber == null) {
			if (other.productNumber != null) {
				return false;
			}
		} else if (!productNumber.equals(other.productNumber)) {
			return false;
		}
		return true;
	}
	
	
}