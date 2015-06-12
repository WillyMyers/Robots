package test.java.entity;

import static org.junit.Assert.*;
import main.java.entity.Heading;

import org.junit.Before;
import org.junit.Test;

public class HeadingTest {

	Heading heading;
	
	@Before
	public void setUp(){
		
	}
	@Test
	public void testRotateLeftFromNorth() {
		heading = Heading.N;
		assertTrue(heading.rotateLeft() == Heading.W);
	}

	@Test
	public void testRotateRightFromNorth() {
		heading = Heading.N;
		assertTrue(heading.rotateRight() == Heading.E);
	}
	
	@Test
	public void testRotateLeftFromEast() {
		heading = Heading.E;
		assertTrue(heading.rotateLeft() == Heading.N);
	}

	@Test
	public void testRotateRightFromEast() {
		heading = Heading.E;
		assertTrue(heading.rotateRight() == Heading.S);
	}
	
	@Test
	public void testRotateLeftFromSouth() {
		heading = Heading.S;
		assertTrue(heading.rotateLeft() == Heading.E);
	}

	@Test
	public void testRotateRightFromSouth() {
		heading = Heading.S;
		assertTrue(heading.rotateRight() == Heading.W);
	}
	
	@Test
	public void testRotateLeftFromWest() {
		heading = Heading.W;
		assertTrue(heading.rotateLeft() == Heading.S);
	}

	@Test
	public void testRotateRightFromWest() {
		heading = Heading.W;
		assertTrue(heading.rotateRight() == Heading.N);
	}

}
