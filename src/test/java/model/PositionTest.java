package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void testNextNorth() {
        Direction dir;
        dir = Direction.N;

        Position p = new Position(3, 1);
        Position result = p.next(dir);
        Position expected = new Position(2, 1);

        assertEquals(expected, result);
    }

    @Test
    public void testNextSouth() {
        Direction dir;
        dir = Direction.S;

        Position p = new Position(0, 0);
        Position result = p.next(dir);
        Position expected = new Position(1, 0);

        assertEquals(expected, result);
    }

    @Test
    public void testNextSouthWest() {
        Direction dir;
        dir = Direction.SW;

        Position p = new Position(1, 1);
        Position result = p.next(dir);
        Position expected = new Position(2, 0);

        assertEquals(expected, result);
    }

    @Test
    public void testNextTwoBehind() {
        Direction dir;
        dir = Direction.S;

        Position p = new Position(6, 1);
        Position result = p.next(dir).next(dir);
        Position expected2 = new Position(8, 1);

        assertEquals(expected2, result);
    }

    @Test
    public void testTwoForward() {
        Direction dir;
        dir = Direction.N;

        Position p = new Position(1, 1);
        Position result = p.next(dir).next(dir);
        Position expected = new Position(-1, 1);

        assertEquals(expected, result);
    }

}