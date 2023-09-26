package model.command;

import model.Model;
import model.item.Item;
import model.item.Rock;
import model.Direction;
import model.item.Diamond;
import model.item.Background;

/**
 * The MoveCommand class represents a command that moves the player or an item in the game,
 * and can be undone to revert this move.
 */
public class MoveCommand implements Command {
    private final Model game;
    private final Direction direction;
    private Item movedItem;

    /**
     * Constructs a new MoveCommand object with the given Model and Direction objects.
     *
     * @param game the Model object associated with this MoveCommand
     * @param direction the direction in which the player or item should be moved
     */
    public MoveCommand(Model game, Direction direction) {
        this.game = game;
        this.direction = direction;
    }

    /**
     * Moves the player and an item in the game.
     */
    @Override
    public void execute() {
        this.game.move(direction);
        this.movedItem = game.getMovedItem();
        game.notifyObservers();
    }

    /**
     * Reverts the changes made by the execute method, by moving the player or item back to its
     * previous position and updating the game state accordingly.
     */
    @Override
    public void unexecute() {
        this.game.move(direction.opposite());
        this.game.setItem(movedItem, game.getPlayer().getPosition().next(direction));

        if (movedItem instanceof Rock) {
            this.game.setItem(new Background(), game.getPlayer().getPosition().next(direction).next(direction));
        } else if (movedItem instanceof Diamond) {
            game.getPlayer().removeDiamond();
        }
        game.notifyObservers();
    }
}
