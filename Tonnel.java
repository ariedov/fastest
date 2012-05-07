package com.badlogic.androidgames.shortest;

public class Tonnel extends GameObject{
	public boolean top = false;
	public boolean right = false;
	public boolean bottom = false;
	public boolean left = false;
	
	public Tonnel(int x, int y, boolean top, boolean right, boolean bottom, boolean left) {
		super(x, y);
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}	
}
