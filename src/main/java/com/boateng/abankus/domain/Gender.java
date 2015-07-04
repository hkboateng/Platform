package com.boateng.abankus.domain;

public enum Gender {

	
	MALE ("male"),
	
	FEMALE ("female");
	
	private  String gender;	
	
	
	Gender (String gender){
		this.setGender(gender);
	}
	
	public String getGender(Gender gender){
		return gender.name();
	}
	
	public String getGender(){
		return this.name();
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
