package com.badlogic.androidgames.shortest;

/** He seeks the shortest way.
 * 
 */
public abstract class Seeker extends GameObject {
	Target target = null;
	
	public Seeker(int x, int y) {
		super(x, y);
	}
	
	public abstract Vertice seek(Map map) throws NoDirectionsException;
	
	public void setTarget(Target target) {
		this.target = target;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Target getTarget() {
		return this.target;
	}
	
	public boolean isColliding(Barrier barrier) {
		if (barrier.x == this.x && barrier.y == this.y)
			return true;
		else 
			return false;
	}
}
