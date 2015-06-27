package com.boateng.abankus.domain;

public enum Gender {

	
	MALE ("male"),
	
	FEMALE ("female");
	
	private final  String gender;	
	
	
	Gender (String gender){
		this.gender = gender;
	}
	
	public String getGender(Gender gender){
		return gender.name();
	}
	
	public String getGender(){
		return this.name();
	}
}
