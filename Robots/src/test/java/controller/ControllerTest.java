package test.java.controller;

import static org.junit.Assert.*;
import main.java.controller.Controller;
import main.java.controller.IllegalPositionException;
import main.java.entity.Heading;
import main.java.entity.Location;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	Controller controller;
	Location location;
	
	@Before
	public void setUp(){
		controller = new Controller(10,10);
		location = new Location(5, 5, Heading.N);
	}
	
	@Test
	public void testRegisterReturnsTrue() throws IllegalPositionException {
		assertTrue(controller.register(location));
	}

	@Test(expected=IllegalPositionException.class)
	public void testRegisterDuplicateLocationThrowsException() throws IllegalPositionException {
		controller.register(location);
		controller.register(location);
	}
	
	@Test(expected=IllegalPositionException.class)
	public void testRegisterOffGridThrowsException() throws IllegalPositionException {
		location = new Location(15, 5, Heading.N);
		controller.register(location);
	}
	
	@Test
	public void testValidMoveReturnsTrue() throws IllegalPositionException {
		Location endLocation = new Location(location.getX()+1, location.getY(), location.getHeading());
		assertTrue(controller.move(location, endLocation));
	}
	
	@Test
	public void testMoveOffGridReturnsFalse() throws IllegalPositionException {
		Location endLocation = new Location(11, location.getY(), location.getHeading());
		assertFalse(controller.move(location, endLocation));
	}
	
	@Test
	public void testMoveToOccupiedLocationReturnsFalse() throws IllegalPositionException {
		Location endLocation = new Location(location.getX()+1, location.getY(), location.getHeading());
		controller.register(endLocation);
		assertFalse(controller.move(location, endLocation));
	}

}
