package main.java.entity;

/**
 * Class to hold the robots location on the grid.
 *
 */
public final class Location {

	private int x;
	private int y;
	private Heading heading;

	public Location(int x, int y, Heading heading) {
		super();
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Heading getHeading() {
		return heading;
	}
	
	public void rotateLeft(){
		this.heading = this.heading.rotateLeft();
	}

	public void rotateRight(){
		this.heading =  this.heading.rotateRight();
	}

	public void move(){
		switch (heading) {
		case N:
			this.y = this.y + 1;
			break;
		case S:
			this.y = this.y - 1;
			break;
		case E:
			this.x = this.x + 1;
			break;
		case W:
			this.x = this.x - 1;
			break;
		}
	}
	
	public Location copy(){
		return new Location(this.x, this.y, this.heading);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heading == null) ? 0 : heading.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (heading != other.heading)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [x=" + x + ", y=" + y + ", heading=" + heading + "]";
	}

	
}
