package model.item;

import model.Board;
import model.Direction;

/**
 * A class representing a wall item in a grid-based game.
 * The wall item is a type of {@link Item} that cannot be moved and is solid.
 */
public class Wall extends Item {

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Direction direction, Board board, Player player) {

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
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "W";
    }
}
