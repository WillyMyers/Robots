package main.java.controller;

import main.java.entity.Location;

/**
 * Maintains each robots location and allows each robot to check if their
 * desired move is legal.
 * 
 * @author willy
 *
 */
public class Controller implements Controllable {

	private final boolean[][] grid;

	public Controller(int xAxis, int yAxis) {
		super();
		this.grid = new boolean[xAxis][yAxis];
	}

	/* (non-Javadoc)
	 * @see bnp.controller.Controllable#register(bnp.entity.Location)
	 */
	@Override
	public synchronized boolean register(final Location location) throws IllegalPositionException {		
		if (!checkPositionIsOnGrid(location)) {
			throw new IllegalPositionException("The requested location is not on the grid");
		}
		if (!checkPositionIsAvailable(location)) {
			throw new IllegalPositionException("This grid position is already occupied by another robot");
		}
		this.grid[location.getX()][location.getY()] = true;
		return true;

	}

	/* (non-Javadoc)
	 * @see bnp.controller.Controllable#move(bnp.entity.Location, bnp.entity.Location)
	 */
	@Override
	public synchronized boolean move(final Location startPosition, final Location endPosition) {
		if (!checkPositionIsOnGrid(endPosition)|| !checkPositionIsAvailable(endPosition)) {
			return false;
		}
		this.grid[startPosition.getX()][startPosition.getY()] = false;
		this.grid[endPosition.getX()][endPosition.getY()] = true;
		return true;
	}

	private boolean checkPositionIsOnGrid(final Location location) {
		if (location.getX() > this.grid.length || location.getY() > this.grid[0].length) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkPositionIsAvailable(final Location location) {
		if (this.grid[location.getX()][location.getY()]) {
			return false;
		}
		return true;
	}
}
