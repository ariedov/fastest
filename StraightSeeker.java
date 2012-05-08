package com.badlogic.androidgames.shortest;

import java.util.List;

import android.util.Log;

public class StraightSeeker extends Seeker {
	public StraightSeeker(int x, int y) {
		super(x, y);
	}

	public Vertice seek(Map map) throws NoDirectionsException {
		if (target == null) {
			throw new NoDirectionsException("No target is set.");
		}
			
		map.buildMap(this, target);
		Vertice seekerPos = map.getSeekerPos();
		Vertice targetPos = map.getTargetPos();
		List<Vertice> available = seekerPos.available;
				
		int len = available.size();
		if (len == 0) {
			throw new NoDirectionsException("No available routes.");
		}
		
		Vertice min = available.get(0);
		for (int i = 0; i < len; i++) {
			Vertice curr = available.get(i);
			Log.d("Available", len + "- " + curr.x + " : " + curr.y);
			if (targetPos.distance(curr) < targetPos.distance(min))
				min = new Vertice(curr);
		}
		return min;
	}
}
