package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class Controller{

	public final String CONTROLLER_BUTTON_TEXT = "Controller Button";
	public PresentationModel presentationModel;
	EditText et;
	public TextWatcher tw = new TextWatcher(){
		@Override public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after){

		}

		@Override public void onTextChanged(final CharSequence s, final int start, final int before, final int count){

		}

		@Override public void afterTextChanged(final Editable s){
			presentationModel.text.set(s.toString());
		}
	};


	public Controller(final View mainFragmentView, final PresentationModel pm){
		presentationModel = pm;
		et = (EditText) mainFragmentView.findViewById(R.id.editText);
		setTextWatcher(et, tw);
	}

	@BindingAdapter("textWatcher")
	public static void setTextWatcher(EditText et, TextWatcher textWatcher){
		et.addTextChangedListener(textWatcher);
	}

	public void controllerButtonClick(final View view){
		new Thread(new Runnable(){
			@Override public void run(){
				presentationModel.text.set("$1.00");
				presentationModel.isShowing.set(true);

			}
		}).start();
	}
}
