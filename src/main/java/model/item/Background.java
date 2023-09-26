package model.item;

import model.Board;
import model.Direction;

/**
 * A class representing a background item in a grid-based game.
 * The background item is a type of {@link Item} that cannot be moved and is not solid.
 */
public class Background extends Item {

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
        return false;
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
        return "-";
    }

}
