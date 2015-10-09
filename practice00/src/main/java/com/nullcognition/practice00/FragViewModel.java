package com.nullcognition.practice00;
// ersin 01/10/15 Copyright (c) 2015+ All rights reserved.


import android.view.View;

public class FragViewModel{

	// the view model should have/or be the base observable

	private Retainable retainable;

	public FragViewModel(final Retainable retainable){
		this.retainable = retainable;
	}

	public Retainable getRetainable(){
		return retainable;
	}

	public void setRetainable(Retainable r){retainable = r;}

	public void setState(int i){retainable.setState(i);}

	public void fromButtonClick(View view){retainable.setState(6);} // view as param must be there
}
