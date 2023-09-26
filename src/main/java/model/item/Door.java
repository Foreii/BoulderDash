package model.item;

import model.Board;
import model.Direction;

/**
 * A class representing a door item in a grid-based game.
 * The door item is a type of {@link Item} that cannot be moved and is solid.
 */
public class Door extends Item {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Direction direction, Board board, Player player) {
        player.setHasSucceeded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConcrete() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTraversable(Direction direction, Board board) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ANSI_RED + "O" + ANSI_RESET;
    }
}
