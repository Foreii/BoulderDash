package model.command;

import model.Model;
import model.Position;
import model.item.Background;
import model.item.Item;

import java.util.Map;

/**
 * The UpdateCommand class represents a command that updates the state of the game by making
 * items fall, and can be undone to revert these changes.
 */
public class UpdateCommand implements Command {

    private final Model game;
    private Map<Item, Position> items;

    public UpdateCommand(Model game) {
        this.game = game;
    }

    /**
     * Makes items fall.
     */
    @Override
    public void execute() {
        this.game.fall();
        items = game.getFallenItems();
        game.notifyObservers();
    }

    /**
     * Reverts the changes made by the execute method, by moving the fallen items back to their
     * previous positions and the player back to its previous position.
     */
    @Override
    public void unexecute() {
        for (var playerMove : items.entrySet()) {
            this.game.setItem(new Background(), playerMove.getKey().getPosition());
            this.game.setItem(playerMove.getKey(), playerMove.getValue());
        }
        game.notifyObservers();
    }
}
