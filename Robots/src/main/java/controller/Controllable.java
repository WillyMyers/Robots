package main.java.controller;

import main.java.entity.Location;

public interface Controllable {

	/**
	 * Registers the robots location as occupied. Will throw an
	 * IllegalPositionException if the location is not on the grid or the
	 * position is already occupied.
	 * 
	 * @param location
	 * @param moves
	 * @return
	 */
	public boolean register(Location location) throws IllegalPositionException;

	/**
	 * Move from the start position to the end position. Will return false if
	 * the end position is already occupied or the end position is outside the
	 * grid.
	 * 
	 * @param startPosition
	 * @param endPosition
	 * @return
	 * @throws IllegalPositionException
	 */
	public boolean move(Location startPosition, Location endPosition);

}