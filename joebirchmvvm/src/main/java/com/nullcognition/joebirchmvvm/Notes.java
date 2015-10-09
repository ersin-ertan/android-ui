package com.nullcognition.joebirchmvvm;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


public class Notes{/*

	https://medium.com/ribot-labs/approaching-android-with-mvvm-8ceec02d5442

	to abstract state and behaviour from a view, allowing separate development of view and business logic
	model - data model for business and validation logic
	view - structure, layout and appearance on screen
	view model - link between v and m, has view logic

	view databinds with viewmodel, vm updates the model

	The Controller is replaced by a View Model, which sits below the UI layer
	This View Model exposes the data and command objects that the View requires
	The View Model receives its data from the Model

	view model introduces two way communication between components

	view declares the components used, view model will return click listener when comments is triggered
	model contains the data and formatting?, which is accessed by the view model set on the view

	model contains properties, and models bunsiness logic, including parcelable

	view is mostly xml, no business logic receiving data from view model updated at runtime
	something like public static BinderHolder extends RecyclerView.ViewHolder which holds a reference
	to the binding

	onBindViewHolder() method is where the actual binding of the ViewModel and View takes place.

	then in the layout xml, reference the view model which would have methods like: getPostTitle(), getPostAuthor(),
	getCommentsVisibility(), onClickPost()

	easy to provide test class

	in the onCreateViewHolder, if using two different layout types then created the correct binding based on which
	you are using, then create the proper view model for the layout and binding

*/}
