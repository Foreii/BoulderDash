package model.item;

import model.Board;
import model.Direction;

/**
 * A class representing a diamond item in a grid-based game.
 * The diamond item is a type of {@link Item} that can be moved and is solid.
 */
public class Diamond extends Item {
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\033[0m";

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Direction direction, Board board, Player player) {
        player.addDiamonds();
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

    @Override
    public boolean isTraversable(Direction direction, Board board) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return CYAN + "D" + RESET;
    }
}
