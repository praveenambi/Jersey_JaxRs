package com.praveen.jax.model;

public class Profile {
	
	private long id;
	private String profilename ;
	private  java.util.Date crtaed;
	private String firstname;
	private String lastname;
	
	public  Profile() {
		
	}
	
	public  Profile(long id,String profilename, String firstname,String lastname) {
		
		this.id=id;
		this.profilename=profilename;
		this.firstname=firstname;
		this.lastname=lastname;

		
		
	}
	
	
	

}
