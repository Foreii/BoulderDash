package model;

import model.item.Background;
import model.item.Player;
import model.item.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void setBackground() {
        Background background = new Background();
        board.setItem(background, new Position(1, 1));
        assertEquals(board.getItem(new Position(1,1)), background);
    }

    @Test
    void setRock() {
        Rock rock = new Rock();
        board.setItem(rock, new Position(1, 1));
        assertEquals(board.getItem(new Position(1,1)), rock);
    }

    @Test
    void setPlayer() {
        Player player = new Player();
        board.setItem(player, new Position(1, 1));
        assertEquals(board.getItem(new Position(1,1)), player);
    }

    @Test
    void testContainsOnCorner() {
        Position pos = new Position(0, 0);
        assertTrue(board.contains(pos));
    }

    @Test
    public void testContainsMiddleBoard() {
        Position pos = new Position(11, 20);
        assertTrue(board.contains(pos));
    }

    @Test
    public void testContainsRowOutOfRange() {
        Position pos = new Position(-1, 3);
        assertFalse(board.contains(pos));
    }

    @Test
    public void testContainsColOutOfRange() {
        Position pos = new Position(1, 41);
        assertFalse(board.contains(pos));
    }

    @Test
    public void testContainsColAndRowOutOfRange() {
        Position pos = new Position(22, 40);
        assertFalse(board.contains(pos));
    }

}