package com.boateng.abankus.products.domain;

public class Products implements Product{

	private long Id;
	private String productId;
	
	private String product;
	
	private String description;
	
	private String productCode;
	
	private String category;
	
	public Products(){
		//do nothing
	}
	
	public Products(String productName){
		this.product = productName;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return getProductName();
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.domain.Product#getProductName()
	 */
	@Override
	public String getProductName() {
		// TODO Auto-generated method stub
		return product;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
