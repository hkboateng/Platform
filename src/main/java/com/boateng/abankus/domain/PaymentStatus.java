/**
 * hkboateng
 */
package com.boateng.abankus.domain;

/**
 * @author hkboateng
 *
 */
public enum PaymentStatus {
	REPAYMENT("repayment"),
	
	DEFAULT("default"),
	
	PAID("paid");
	
	private String paymentStatus;
	
	PaymentStatus(String status){
		this.paymentStatus = status;
	}
	
	public String getPaymentStatus(){
		return this.paymentStatus;
	}
}
