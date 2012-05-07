package com.badlogic.androidgames.shortest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** He seeks the shortest way.
 * 
 */
public class WaveSeeker extends Seeker  {
		
	public WaveSeeker(int x, int y) {
		super(x, y);
	}
	
	public Vertice seek(Map map) throws NoDirectionsException {
		if (target == null) {
			throw new NoDirectionsException("There is no target set.");
		}
		
		map.buildMap(this, target);
		
		Vertice seekerPos = map.getSeekerPos();
		Vertice targetPos = map.getTargetPos();
		
		List<Vertice> oldFront = new ArrayList<Vertice>();
		List<Vertice> newFront = new ArrayList<Vertice>();
		int T = 0;
		oldFront.add(seekerPos);
		seekerPos.cost = 0;
		while (!isIn(oldFront, targetPos)) {
			for (Vertice v: oldFront) {
				for (Vertice a: v.available) { 
					if (a.cost == -1) {
						a.cost = T+1;
						newFront.add(a);
					}
				}
			}
			if (newFront.size() == 0) {
				throw new NoDirectionsException("No shortest path found.");
			}
			oldFront = new ArrayList<Vertice>(newFront);
			newFront.clear();
			T++;
		}
		
		return Trassing(seekerPos, targetPos);
	}
	
	public Vertice Trassing(Vertice s, Vertice t) {
		Vertice curr = new Vertice(t);
		Stack<Vertice> stack = new Stack<Vertice>();
		do {
			stack.push(curr);
			curr.available.add(s);
			for (Vertice a: curr.available) {
				if (a.cost == curr.cost - 1) {
					curr = new Vertice(a);
					break;
				}
			}

			if (curr.cost == -1)
				break;
		} while(curr.x != s.x || curr.y != s.y);
		return stack.pop();
	}

	protected boolean isIn(List<Vertice> vertices, Vertice vertice) {
		for (Vertice v: vertices) {
			if (v.x == vertice.x && v.y == vertice.y)
				return true;
		}
		return false;
	}
}