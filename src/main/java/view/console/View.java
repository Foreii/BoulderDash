package view.console;

import model.Direction;

import java.util.List;

/**
 * An interface for views in the Boulder Dash game.
 */
public interface View {

    /**
     * Displays the title of the game.
     */
    void displayTitle();

    /**
     * Displays an error message.
     */
    void displayError();

    /**
     * Asks the player which level they want to play and returns the path to the level file.
     *
     * @return the path to the level file
     */
    int askLevel();

    /**
     * Displays an error message for an empty history stack.
     */
    void displayErrorHistoryStack();

    /**
     * Displays an error message for an empty undo history stack.
     */
    void displayErrorHistoryUndoStack();

    /**
     * Displays the keys that the player can use to move.
     */
    void displayKeys();

    /**
     * Displays a message indicating whether the player has won or lost.
     */
    void displayWinner();

    /**
     * Displays the number of diamonds remaining to be picked up.
     */
    void diamondsToPickUp();

    /**
     * Displays the current level of the board.
     */
    void showLevel();

    /**
     * Displays the current state of the board.
     */
    void displayBoard();

    /**
     * Converts a string input by the player into a direction.
     *
     * @param line the string input by the player
     * @return the direction represented by the string input
     */
    Direction convertDirection(String line);

    /**
     * Asks the player for a command and returns the command as a string.
     *
     * @param list the list of valid directions
     * @return the player's command as a string
     */
    String askCommand(List<Direction> list);

}
