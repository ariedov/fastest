package com.badlogic.androidgames.shortest;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	public int x, y;
	public boolean blocked;
	public boolean seeker;
	public boolean target;
	public int cost = -1;
	public List<Vertice> available = new ArrayList<Vertice>();
	
	public Vertice(Vertice prev) {
		this.x = prev.x;
		this.y = prev.y;
		this.blocked = prev.blocked;
		this.seeker = prev.seeker;
		this.target = prev.target;
		this.available = new ArrayList<Vertice>(prev.available);
		this.cost = prev.cost;
	}
	
	public Vertice(GameObject gameObject) {
		this.x = gameObject.x;
		this.y = gameObject.y;
		this.blocked = false;
		this.seeker = false;
		this.target = false;
	}
	
	public Vertice(int x, int y, boolean blocked, boolean seeker, boolean target) {
		this.x = x;
		this.y = y;
		this.blocked = blocked;
		this.seeker = seeker;
		this.target = target;
	}
	
	public double distance(Vertice target) {
		int xDiff = Math.abs(this.x - target.x);
		int yDiff = Math.abs(this.y - target.y);
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
	
	public Tonnel getCurrentTonnel(List<Tonnel> tonnels) { 
		int len = tonnels.size();
		for (int i = 0; i < len; i++) {
			Tonnel tonnel = tonnels.get(i);
			if (this.x == tonnel.x && this.y == tonnel.y) {
				return tonnel;
			}
		}
		return null;
	}
	
	public void reset(boolean blocked, boolean seeker, boolean target) {
		this.blocked = blocked;
		this.seeker = seeker;
		this.target = target;
		this.cost = -1;
		this.available.clear();
	}
}