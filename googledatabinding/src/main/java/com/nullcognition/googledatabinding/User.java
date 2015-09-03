package com.nullcognition.googledatabinding;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.


public class User{

	public final String  firstName;
	public final String  lastName;
	public       boolean isOn;

	public User(String firstName, String lastName, boolean isOn){
		this.firstName = firstName;
		this.lastName = lastName;
		this.isOn = isOn;
	}

	public static String externalModification(String string){return "Mr. " + string;}
}
