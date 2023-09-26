package model;

import model.command.Command;
import model.item.Item;
import model.item.Player;
import util.Observable;

import java.util.List;
import java.util.Map;

public interface Model extends Observable {

    /**
     Starts the game by converting the board to the current level.
     */
    void start(int level);

    /**
     Makes the player move in the given direction, if possible.

     @param direction the direction in which the player should move

     @throws IllegalArgumentException if the player isn't allowed to move this way
     @throws IndexOutOfBoundsException if the position doesn't exist
     */
    void move(Direction direction);

    boolean hasPassed();

    /**
     Updates the game by making movable items fall if possible.
     */
    void fall();

    Map<Item, Position> getFallenItems();

    void setFallenItems(Item fallenItems);

    List<Direction> getPossibleDirections();

    void convertToBoard(int level);

    void executeCommand(Command command);

    void undo();

    void redo();

    /**
     * Creates or changes the level of the board.
     * @param level the level
     */
    void createLevel(int level);

    int getDiamTotal();

    Item getItem(Position position);

    void setItem(Item item, Position position);

    int diamondsRemaining();

    boolean isAlive();

    Item getMovedItem();

    Player getPlayer();

    int objectiveCurrentLevel();

    int getCurrentLevel();

    boolean historyIsEmpty();

    boolean historyUndoIsEmpty();
}
