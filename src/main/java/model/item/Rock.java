package model.item;

import model.Board;
import model.Direction;
import model.Position;

/**
 * A class representing a rock item in a grid-based game.
 * The rock item is a type of {@link Item} that can be moved and is solid.
 */
public class Rock extends Item {
    public static final String ANSI_PURPLE = "\033[0;35m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Direction direction, Board board, Player player) {
        Position newPos = this.getPosition().next(direction);

        board.setItem(this, newPos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovable() {
        return true;
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
        if (direction == Direction.N) {
            return false;
        }

        return !board.getItem(this.getPosition().next(direction)).isConcrete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ANSI_PURPLE + "R" + ANSI_RESET ;
    }
}
