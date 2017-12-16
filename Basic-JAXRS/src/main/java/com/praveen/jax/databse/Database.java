package com.praveen.jax.databse;

import java.util.HashMap;
import java.util.Map;

import com.praveen.jax.model.Message;
import com.praveen.jax.model.Profile;

public class Database {
	
	
	private static  Map<Long, Message> message = new HashMap<>();
	
	
	private static  Map<Long, Profile> profiles  = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return message;
		
	}
	
	public static Map<Long, Profile> getProfiles() {
		return profiles;
		
	}
	
	

}
