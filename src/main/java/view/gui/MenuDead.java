package view.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 Class representing the menu that appears when the player dies in a game.
 */
public class MenuDead {
    private StackPane stackPane;
    private Rectangle rectangle;
    private Text quit;
    private Text change;
    private Text restart;

    /**
     Constructor for the MenuDead class. Initializes the stackPane and calls the createContent() method.
     */
    public MenuDead() {
        stackPane = new StackPane();
        createContent();
    }

    /**
     Method that creates the elements of the menu.
     */
    private void createContent() {
        rectangle = new Rectangle(UIConstants.APP_WIDTH / 2.0, UIConstants.APP_HEIGHT / 2.0);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.8);

        quit = new Text("EXIT");
        quit.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        quit.setFill(Color.WHITE);
        quit.setTranslateY(80);

        restart = new Text("RESTART");
        restart.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        restart.setFill(Color.WHITE);

        change = new Text("Change level");
        change.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        change.setFill(Color.WHITE);
        change.setTranslateY(-80);

        stackPane.getChildren().addAll(rectangle, quit, change, restart);

        quit.setOnMouseEntered(event -> {
            quit.setFill(Color.DARKGREY);
        });

        quit.setOnMouseExited(event -> {
            quit.setFill(Color.WHITE);
        });

        change.setOnMouseEntered(event -> {
            change.setFill(Color.DARKGREY);
        });

        change.setOnMouseExited(event -> {
            change.setFill(Color.WHITE);
        });

        restart.setOnMouseExited(event -> {
            restart.setFill(Color.WHITE);
        });

        restart.setOnMouseEntered(event -> {
            restart.setFill(Color.DARKGREY);
        });

    }

    /**
     Returns the Text object representing the "RESTART" option in the menu.
     @return The Text object representing the "RESTART" option in the menu.
     */
    public Text getRestart() {
        return restart;
    }

    /**
     Returns the Text object representing the "Change level" option in the menu.
     @return The Text object representing the "Change level" option in the menu.
     */
    public Text getChange() {
        return change;
    }

    /**
     Returns the StackPane object that holds the menu elements.
     @return The StackPane object that holds the menu elements.
     */
    public StackPane getStackPane() {
        return stackPane;
    }

    /**
     Returns the Text object representing the "EXIT" option in the menu.
     @return The Text object representing the "EXIT" option in the menu.
     */
    public Text getQuit() {
        return quit;
    }
}
