package controller;

import model.Direction;
import model.Model;
import model.command.Command;
import model.command.MoveCommand;
import model.command.SequentialCommand;
import model.command.UpdateCommand;
import view.console.View;

import java.util.Arrays;
import java.util.List;

/**
 * The Controller class is responsible for controlling the game flow and interacting
 * with the View and Model classes.
 *
 * @author [58646]
 */
public class Controller {
    private final View view;
    private final Model model;
    private boolean isFinished = false;
    private boolean quit = false;

    /**
     * Constructs a new Controller object with the given Model and View.
     *
     * @param model the Model associated with this Controller
     * @param view the View associated with this Controller
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Starts the game and controls the game flow until the user decide to quit.
     */
    public void play() {
        view.displayTitle();
        int start = view.askLevel();

        if (start != -1) {
            model.start(start);

            while (!quit) {
                view.showLevel();
                while (!isFinished) {
                    view.displayBoard();
                    view.diamondsToPickUp();

                    List<Direction> list = model.getPossibleDirections();

                    String line = view.askCommand(list);

                    if (line.equals("undo") && !model.historyIsEmpty()) {
                        this.model.undo();
                    } else if (line.equals("redo") && !model.historyUndoIsEmpty()) {
                        this.model.redo();
                    } else if (line.matches("[zqsd]")) {
                        Direction direction = view.convertDirection(line);
                        List<Command> commands = Arrays.asList(new MoveCommand(model, direction), new UpdateCommand(model));

                        Command command = new SequentialCommand(commands);
                        this.model.executeCommand(command);
                    } else {
                        view.displayError();
                    }

                    if (!model.isAlive() || model.hasPassed()) {
                        isFinished = true;
                    }

                }

                view.displayBoard();
                view.displayWinner();

                int level = view.askLevel();
                if (level == -1) {
                    quit = true;
                } else {
                    model.convertToBoard(level);
                    isFinished = false;
                }

            }

        }

    }


}
