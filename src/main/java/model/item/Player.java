package model.item;

import model.Board;
import model.Direction;
import model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a player in a grid-based game.
 * The player is a type of {@link Item} and can move on the game board.
 */
public class Player extends Item {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int nbDiamonds;
    private boolean isAlive;
    private boolean hasSucceeded;

    /**
     * Constructs a new player.
     * The player is initially alive.
     */
    public Player() {
        this.isAlive = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Direction direction, Board board, Player player) {
        Position newPos = this.getPosition().next(direction);

        if (board.getItem(newPos) instanceof Door) {
            hasSucceeded = true;
        }

        board.setItem(new Background(), this.getPosition());
        board.setItem(this, newPos);
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
        return false;
    }

    /**
     * Returns whether the player is alive.
     *
     * @return true if the player is alive, false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets the player's alive status.
     *
     * @param alive the new alive status of the player
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Sets the player's success status to true.
     */
    public void setHasSucceeded() {
        this.hasSucceeded = true;
    }

    /**
     * Returns a list of directions that the player can move in,
     * based on the player's current position and the state of the board.
     *
     * @param board the board on which the player is moving
     * @return a list of directions that the player can move in
     */

    public List<Direction> getPossibleDirections(Board board) {
        return validMove(playerMoves(this.getPosition()), board);
    }

    /**
     * Returns a list of directions that are valid for the player to move in,
     * based on the given map of positions and directions and the state of the board.
     *
     * @param playerMoves a map of positions and directions to check for validity
     * @param board the board on which the player is moving
     * @return a list of directions that are valid for the player to move in
     */
    private List<Direction> validMove(Map<Position, model.Direction> playerMoves, Board board) {
        java.util.List<model.Direction> valid = new ArrayList<>();

        for (var playerMove : playerMoves.entrySet()) {
            if (board.contains(playerMove.getKey())) {
                if (possibleItems(playerMove.getKey(), playerMove.getValue(), board)) {
                    valid.add(playerMove.getValue());
                }
            }
        }

        return valid;
    }

    /**
     * Returns the number of diamonds collected by the player.
     *
     * @return the number of diamonds collected by the player
     */
    public int getNbDiamonds() {
        return nbDiamonds;
    }

    /**
     * Increments the number of diamonds collected by the player by 1.
     */
    public void addDiamonds() {
        nbDiamonds++;
    }

    /**
     * Decrements the number of diamonds collected by the player by 1.
     */
    public void removeDiamond() {
        nbDiamonds--;
    }

    /**
     * Determines whether the player can traverse the given position in the given direction on the given board.
     *
     * @param position the position to check
     * @param direction the direction in which the player is moving
     * @param board the board on which the player is moving
     * @return true if the player can traverse the given position in the given direction on the given board, false otherwise
     */
    private boolean possibleItems(Position position, Direction direction, Board board) {
        Item item = board.getItem(position);

        return (item.isTraversable(direction, board));
    }

    /**
     * Returns a map of positions and directions that the player can move to from the given position.
     *
     * @param position the position from which the player can move
     * @return a map of positions and directions that the player can move to from the given position
     */
    private Map<Position, Direction> playerMoves(Position position) {
        Map<Position, Direction> moves = new HashMap<>();

        for (Direction d : playerDirections()) {
            moves.put(position.next(d), d);
        }

        return moves;
    }

    /**
     * Returns a list of directions in which the player theoretically can move.
     *
     * @return a list of directions in which the player theoretically can move
     */
    private List<Direction> playerDirections() {
        List<Direction> directions = new ArrayList<>();

        Direction dirUp = Direction.N;
        Direction dirDown = Direction.S;
        Direction dirLeft = Direction.W;
        Direction dirRight = Direction.E;

        directions.add(dirUp);
        directions.add(dirDown);
        directions.add(dirLeft);
        directions.add(dirRight);

        return directions;
    }

    /**
     * Returns a boolean value indicating whether the game has succeeded.
     *
     * @return true if the game has succeeded, false otherwise
     */
    public boolean isHasSucceeded() {
        return hasSucceeded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ANSI_RED + "P" + ANSI_RESET;
    }

}
