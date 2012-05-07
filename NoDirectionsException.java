package com.badlogic.androidgames.shortest;

@SuppressWarnings("serial")
public class NoDirectionsException extends Exception {
	public NoDirectionsException() {
		
	}
	
	public NoDirectionsException(String description) {
		super(description);
	}
}
