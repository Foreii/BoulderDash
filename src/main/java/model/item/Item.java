package model.item;

import model.Board;
import model.Direction;
import model.Position;

public abstract class Item {
    private Position position;

    /**
     * Moves the object in the specified direction on the given board.
     *
     * @param direction the direction to move the object
     * @param board the board on which the object is located
     */
    public abstract void move(Direction direction, Board board, Player player);

    /**
     * Returns true if this item can be moved.
     *
     * @return true if this item can be moved, false otherwise
     */
    public abstract boolean isMovable();

    /**
     * Returns true if this item is a concrete object.
     *
     * @return true if this item is a concrete object, false otherwise
     */
    public abstract boolean isConcrete();

    /**
     * Returns true if this item is traversable, false otherwise.
     * In most items we don't need the parameters, but for the rock µ
     * it is needed to see if a concrete object is behind it.
     *
     * @param direction the direction in which the player is going
     * @param board the board
     * @return true if this item is traversable, false otherwise
     */
    public abstract boolean isTraversable(Direction direction, Board board);

    /**
     * Returns the position of this item.
     *
     * @return the position of this item
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of this item.
     *
     * @param position the new position of this item
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
