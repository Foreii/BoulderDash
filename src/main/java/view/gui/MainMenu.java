package view.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * MainMenu is a class that represents the main menu of the game.
 * It displays the title of the game, "Boulder Dash", and two options: "PLAY" and "EXIT".
 * The player can choose to start a new game or exit the game by clicking on these options.
 */
public class MainMenu {
    private final StackPane stackPane;
    Text play = new Text("PLAY");
    Text exit = new Text("EXIT");

    public MainMenu() {
        stackPane = new StackPane();
        stackPane.setPrefSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
    }

    /**
     * Creates the main menu of the game by adding a background image, the title of the game,
     * and the options "PLAY" and "EXIT" to a StackPane.
     */
    public void createMenu() {
        ImageView background = new ImageView(new Image("/assets/background_2.jpg"));
        background.setFitWidth(stackPane.getPrefWidth());
        background.setFitHeight(stackPane.getPrefHeight());

        stackPane.getChildren().add(background);

        createTitle();
        createSubtitles();
    }

    /**
     * Creates the title of the game, "Boulder Dash", and adds it to the StackPane.
     */
    private void createTitle() {
        Rectangle rectangle = new Rectangle(stackPane.getPrefWidth(), 140);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.6);

        Text boulder = new Text("B O U L D E R");
        Text dash = new Text("D A S H");

        boulder.setFill(Color.WHITE);
        dash.setFill(Color.WHITE);

        boulder.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        dash.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        Line line = new Line();
        line.setEndX(310);
        line.setStroke(Color.DARKGREY);
        line.setOpacity(0.5);

        rectangle.setTranslateY(-130);
        boulder.setTranslateY(-160);
        dash.setTranslateY(-100);
        line.setTranslateY(-128);

        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(rectangle, boulder, dash, line);
    }

    /**
     * Creates the options "PLAY" and "EXIT" and adds them to the StackPane.
     * The player can choose to start a new game or exit the game by clicking on these options.
     */
    private void createSubtitles() {
        Rectangle rectangle = new Rectangle(200, 100);
        rectangle.setOpacity(0.4);

        play.setFill(Color.WHITE);
        exit.setFill(Color.WHITE);

        play.setFont(Font.font("Times New Roman", 30));
        exit.setFont(Font.font("Times New Roman",  30));

        Line line = new Line();
        line.setStroke(Color.DARKGREY);
        line.setEndX(150);
        line.setOpacity(0.5);

        rectangle.setTranslateY(50);
        play.setTranslateY(25);
        exit.setTranslateY(75);
        line.setTranslateY(50);

        stackPane.getChildren().addAll(rectangle, play, exit, line);

        play.setOnMouseEntered(event -> {
            play.setFill(Color.DARKGREY);
        });

        exit.setOnMouseEntered(event -> {
            exit.setFill(Color.DARKGREY);
        });

        play.setOnMouseExited(event -> play.setFill(Color.WHITE));

        exit.setOnMouseExited(event -> exit.setFill(Color.WHITE));

        exit.setOnMouseClicked(event -> System.exit(0));
    }

    /**
     * Returns the "PLAY" text of the main menu.
     *
     * @return the "PLAY" text of the main menu
     */
    public Text getPlay() {
        return play;
    }

    /**
     * Returns the StackPane that contains the main menu of the game.
     *
     * @return the StackPane that contains the main menu of the game
     */
    public StackPane getStackPane() {
        return stackPane;
    }
}
