package model.item;

import model.Board;
import model.Direction;

public class Dirt extends Item {
    public static final String ANSI_GREEN = "\033[0;32m";
    public static final String ANSI_RESET = "\u001B[0m";

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
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ANSI_GREEN + "D" + ANSI_RESET;
    }
}
