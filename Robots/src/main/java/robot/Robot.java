package main.java.robot;

import java.util.concurrent.Callable;

import main.java.controller.Controllable;
import main.java.controller.IllegalPositionException;
import main.java.entity.Location;
import main.java.entity.Move;

public class Robot implements Callable<Boolean> {

	private Move[] moves;
	private Controllable controller;
	private Location location;
	private int retry;
	private int name;

	public Robot(Move[] moves, Controllable controller, Location location, int retry, int name) {
		super();
		this.moves = moves;
		this.controller = controller;
		this.location = location;
		this.retry = retry;
		this.name = name;
	}

	private boolean start() {
		boolean result = true;
		Location endLocation = this.location.copy();
		for (Move move : moves) {
			if (move == Move.M) {
				endLocation.move();
				result = tryMove(endLocation);
				if(!result){
					break;
				}
				this.location = endLocation;
			}
			if (move == Move.L) {
				endLocation.rotateLeft();
			}
			if (move == Move.R) {
				endLocation.rotateRight();
			}
		}
		return result;
	}

	/**
	 * Try to move but give up after 3 attempts if not successful
	 * 
	 * @param endLocation
	 */
	private boolean tryMove(Location endLocation) {
		for (int i = 0; i < retry; i++) {
			if (controller.move(location, endLocation)) {
				System.out.println("Moving robot " + name + " from " + location.toString() + " to " + endLocation.toString());
				return true;
			}
			System.out.println("Failed to move robot " + name + " from " + location.toString() + " to " + endLocation.toString());
		}
		return false;
	}

	@Override
	public Boolean call() throws Exception {
		boolean result = true;;
		// register this robot with the controller and start it
		try {
			controller.register(location);
			result =  this.start();
		} catch (IllegalPositionException e) {
			System.out.println("Failed to register robot with the controller: " + e);
		}
		return result;
	}

}
