package com.breakout.observer;

import java.util.ArrayList;

public class GameObservable implements Observable {
	
	private ArrayList<Observer> observerList;
	
	public GameObservable(){
		observerList = new ArrayList<Observer>();
	}

	@Override
	public void register(Observer o) {
		// TODO Auto-generated method stub
		observerList.add(o);

	}

	@Override
	public void unregister(Observer o) {
		// TODO Auto-generated method stub
		int i = observerList.indexOf(o);
		if(i >= 0){
			observerList.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < observerList.size(); i++){
			Observer observer = observerList.get(i);
			observer.update();
		}
	}
	
	public void frameChanged(){
		notifyObservers();
	}

}
