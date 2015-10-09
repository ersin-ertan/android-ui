package com.nullcognition.mosby.repos;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class MvpLceRxPresenter<V extends MvpLceView<M>, M>
		extends MvpBasePresenter<V>
		implements MvpPresenter<V>{

	protected Subscriber<M> subscriber;

	/**
	 Unsubscribes the subscriber and set it to null
	 */
	protected void unsubscribe(){
		if(subscriber != null && !subscriber.isUnsubscribed()){
			subscriber.unsubscribe();
		}

		subscriber = null;
	}

	/**
	 Subscribes the presenter himself as subscriber on the observable

	 @param observable    The observable to subscribe
	 @param pullToRefresh Pull to refresh?
	 */
	public void subscribe(Observable<M> observable, final boolean pullToRefresh){

		if(isViewAttached()){
			if(getView() != null){ getView().showLoading(pullToRefresh); }
		}

		unsubscribe();

		subscriber = new Subscriber<M>(){
			private boolean ptr = pullToRefresh;

			@Override public void onCompleted(){
				MvpLceRxPresenter.this.onCompleted();
			}

			@Override public void onError(Throwable e){
				MvpLceRxPresenter.this.onError(e, ptr);
			}

			@Override public void onNext(M m){
				MvpLceRxPresenter.this.onNext(m);
			}
		};

		observable.subscribeOn(Schedulers.io())
		          .observeOn(AndroidSchedulers.mainThread())
		          .subscribe(subscriber);
	}

	protected void onCompleted(){
		if(isViewAttached()){
			getView().showContent();
		}
		unsubscribe();
	}

	protected void onError(Throwable e, boolean pullToRefresh){
		if(isViewAttached()){
			getView().showError(e, pullToRefresh);
		}
		unsubscribe();
	}

	protected void onNext(M data){
		if(isViewAttached()){
			getView().setData(data);
		}
	}

	@Override public void detachView(boolean retainInstance){
		super.detachView(retainInstance);
		if(!retainInstance){
			unsubscribe();
		}
	}
}
