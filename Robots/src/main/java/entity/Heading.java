package main.java.entity;

public enum Heading {
	N, E, S, W;

	public Heading rotateLeft() {
        int left = ordinal() -1;
        if (left < 0) left += 4;
        return values()[left];
    }

	public Heading rotateRight() {
        int right = ordinal() +1;
        if (right > 3) right -= 4;
        return values()[right];
    }
}
