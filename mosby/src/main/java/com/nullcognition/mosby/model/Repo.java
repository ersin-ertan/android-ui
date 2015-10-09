package com.nullcognition.mosby.model;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


public class Repo{

	long   id;
	String name;
	String description;

	User owner;

	public long getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public User getOwner(){
		return owner;
	}
}
