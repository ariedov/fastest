package com.badlogic.androidgames.shortest;

import java.util.ArrayList;
import java.util.List;


public class Map {
	protected List<Vertice> vertices = new ArrayList<Vertice>();
	protected List<Barrier> barriers = new ArrayList<Barrier>();
	protected List<Tonnel> tonnels = new ArrayList<Tonnel>();
	Seeker ant;
	public int width, height;
	
	public Map(int width, int height) {
		ant = new StupidSeeker(0, 0);
		this.width = width;
		this.height = height;
	}
	
	public Map(Map map) {
		this.vertices = new ArrayList<Vertice>(map.vertices);
		this.barriers = new ArrayList<Barrier>(map.barriers);
		this.tonnels = new ArrayList<Tonnel>(map.tonnels);
		this.width = map.width;
		this.height = map.height;
		ant = new StupidSeeker(0, 0);
		
	}
	
	public void addBarrier(Barrier barrier) {
		barriers.add(barrier);
	}
	public void addTonnel(Tonnel tonnel) {
		tonnels.add(tonnel);
	}
	
	public List<Vertice> getMap() {
		return vertices;
	}
	
	public List<Vertice> buildMap(Seeker seeker, Target target) {
		vertices.clear();
		//let's imagine, that all of this values, is not too big
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				boolean blocked = true;
				boolean isSeeker = false;
				boolean isTarget = false;
				ant.move(i, j);
				if (isSafe())
					blocked = false;
				if (seeker.x == ant.x && seeker.y == ant.y)
					isSeeker = true;
				if (target.x == ant.x && target.y == ant.y)
					isTarget = true;
				vertices.add(new Vertice(i, j, blocked, isSeeker, isTarget));
			}
		}
		
		int len = vertices.size();
		for (int i = 0; i < len; i++) {
			Vertice current = vertices.get(i);
			Tonnel tonnel = current.getCurrentTonnel(tonnels);
			if (tonnel == null)
				tonnel = new Tonnel(current.x, current.y, false, false, false, false);
			
			for (int j = 0; j < len; j++) {
				Vertice check = vertices.get(j);
				if (!check.blocked) {
					if (check.x == current.x + 1 && check.y == current.y && !tonnel.right ||
						check.x == current.x - 1 && check.y == current.y && !tonnel.left ||
						check.x == current.x && check.y == current.y + 1 && !tonnel.bottom ||
						check.x == current.x && check.y == current.y - 1 && !tonnel.top ||
						current.x == 0 && check.x == width - 1 && check.y == current.y && !tonnel.left ||
						current.y == 0 && check.y == height - 1 && check.x == current.x && !tonnel.top || 
						current.y == height - 1 && check.y == 0 && check.x == current.x && !tonnel.bottom ||
						current.x == width - 1 && check.x == 0 && check.y == current.y && !tonnel.right)
								vertices.get(i).available.add(check);
					
				}
			}
		}
		
		barriers.clear();
		return vertices;
	}
	
	protected boolean isSafe() {
		int len = barriers.size();
		for (int i = 0; i < len; i++) {
			Barrier barrier = barriers.get(i);
			if (ant.isColliding(barrier)) {
				return false;
			}
		}
		return true;
	}
	
	public Vertice getSeekerPos() {
		int len = vertices.size();
		Vertice seeker = null;
		for (int i = 0; i < len; i++) {
			Vertice current = vertices.get(i);
			if (current.seeker) {
				seeker = current;
				break;
			}
		}
		return seeker;
	}
	
	public Vertice getTargetPos() {
		int len = vertices.size();
		Vertice target = null;
		for (int i = 0; i < len; i++) {
			Vertice current = vertices.get(i);
			if (current.target) {
				target = current;
				break;
			}
		}
		return target;
	}
}
