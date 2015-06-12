package test.java.entity;

import static org.junit.Assert.*;
import main.java.entity.Heading;
import main.java.entity.Location;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	Location location;
	
	@Before
	public void setUp(){
		location = new Location(5, 5, Heading.N);
	}
	@Test
	public void testRotateLeft() {
		location.rotateLeft();
		assertTrue(location.getHeading() == Heading.W);
	}

	@Test
	public void testRotateRight() {
		location.rotateRight();
		assertTrue(location.getHeading() == Heading.E);
	}

	@Test
	public void testMoveNorth() {
		location.move();
		assertTrue(location.getHeading() == Heading.N);
		assertTrue(location.getX() == 5);
		assertTrue(location.getY() == 6);
	}

}
