package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


public class User{

	private String price;
	private String imageUrl;

	public User(final String price, final String imageUrl){
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public User(final String price){this.price = price;}

	public String getPrice(){ return price; }

	public void setPrice(String in){ price = in; }

	public String getImageUrl(){ return imageUrl; }
}
