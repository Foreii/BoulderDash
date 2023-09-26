package view.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 Class representing the menu that appears when the player press esc in a game.
 */
public class MenuEsc {
    private StackPane stackPane;
    private Rectangle rectangle;
    private Rectangle r;
    private Text quit;
    private Text resume;
    private Text restart;

    /**
     Constructor for the MenuEsc class. Initializes the stackPane and calls the createContent() method.
     */
    public MenuEsc() {
        stackPane = new StackPane();
        createContent();
    }

    /**
     Method that creates the elements of the menu.
     */
    public void createContent() {
        rectangle = new Rectangle(UIConstants.APP_WIDTH / 2.0, UIConstants.APP_HEIGHT / 2.0);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.8);

        quit = new Text("EXIT");
        quit.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        quit.setFill(Color.WHITE);
        quit.setTranslateY(80);

        resume = new Text("RESUME");
        resume.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        resume.setFill(Color.WHITE);

        restart = new Text("RESTART");
        restart.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        restart.setFill(Color.WHITE);
        resume.setTranslateY(-80);

        stackPane.setVisible(false);

        stackPane.getChildren().addAll(rectangle, restart, resume, quit);
        quit.setOnMouseEntered(event -> {
            quit.setFill(Color.DARKGREY);
        });
        quit.setOnMouseExited(event -> {
            quit.setFill(Color.WHITE);
        });

        resume.setOnMouseEntered(event -> {
            resume.setFill(Color.DARKGREY);
        });
        resume.setOnMouseExited(event -> {
            resume.setFill(Color.WHITE);
        });

        restart.setOnMouseEntered(event -> {
            restart.setFill(Color.DARKGREY);
        });
        restart.setOnMouseExited(event -> {
            restart.setFill(Color.WHITE);
        });

        quit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }

    /**
     Returns the Rectangle object that acts as the background for the menu.
     @return The Rectangle object that acts as the background for the menu.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     Returns the Rectangle object.
     @return The Rectangle object.
     */
    public Rectangle getR() {
        return r;
    }

    /**
     Returns the Text object representing the "EXIT" option in the menu.
     @return The Text object representing the "EXIT" option in the menu.
     */
    public Text getQuit() {
        return quit;
    }

    /**
     Returns the StackPane object that holds the menu elements.
     @return The StackPane object that holds the menu elements.
     */
    public StackPane getStackPane() {
        return stackPane;
    }

    /**
     Returns the Text object representing the "RESTART" option in the menu.
     @return The Text object representing the "RESTART" option in the menu.
     */
    public Text getRestart() {
        return restart;
    }

    /**
     Returns the Text object representing the "Resume" option in the menu.
     @return The Text object representing the "Resume" option in the menu.
     */
    public Text getResume() {
        return resume;
    }
}
