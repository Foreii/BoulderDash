package view.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 Class representing the level menu of the game, where the player can choose which level to play.
 */
public class LevelMenu {
    private StackPane stackPane;
    private List<Text> texts;
    private List<Rectangle> rectangles;
    private Text back;

    /**
     Constructs a new LevelMenu.
     */
    public LevelMenu() {
        stackPane = new StackPane();
        stackPane.setPrefSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);

        texts = new ArrayList<>();
        rectangles = new ArrayList<>();
        createContent();
    }

    /**
     Creates the content of the level menu by adding a background image, the title "Choose your level",
     and the level options to a StackPane.
     */
    public void createContent() {
        ImageView background = new ImageView(new Image("/assets/background_2.jpg"));
        background.setFitWidth(stackPane.getPrefWidth());
        background.setFitHeight(stackPane.getPrefHeight());
        stackPane.getChildren().add(background);
        stackPane.setAlignment(Pos.CENTER);

        createTitle();
        createGoBack();

        int j = -450;
        for (int i = 1; i < 8; i++) {
            createRectangles(j, i);
            j += 150;
        }

    }

    private void createGoBack() {
        back = new Text();
        back.setText("Q");
        back.setFill(Color.BLACK);
        back.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
        back.setTranslateX(- (UIConstants.APP_WIDTH / 2.0) + 50);
        back.setTranslateY((UIConstants.APP_HEIGHT / 2.0) - 50);
        stackPane.getChildren().add(back);

        back.setOnMouseEntered(event -> {
            back.setFill(Color.DARKGREY);
        });

        back.setOnMouseExited(event -> {
            back.setFill(Color.BLACK);
        });
    }

    /**
     Creates the title of the level menu, "Choose your level", and adds it to the StackPane.
     */
    public void createTitle() {
        Rectangle rectangle = new Rectangle(500, 140);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.6);

        Text level = new Text("Choose your level");

        level.setFill(Color.WHITE);
        level.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        rectangle.setTranslateY(-130);
        level.setTranslateY(-135);

        stackPane.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(rectangle, level);
    }

    public void createRectangles(int translate, int nbLevel) {
        Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.4);

        Text text = new Text("" + nbLevel);
        text.setTranslateX(translate);
        text.setTranslateY(50);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        rectangle.setTranslateX(translate);
        rectangle.setTranslateY(50);

        rectangle.setOnMouseEntered(event -> {
            rectangle.setFill(Color.DARKGREY);
        });
        rectangle.setOnMouseExited(event -> {
            rectangle.setFill(Color.BLACK);
        });
        text.setOnMouseEntered(event -> {
            rectangle.setFill(Color.DARKGREY);
        });

        texts.add(text);
        rectangles.add(rectangle);

        stackPane.getChildren().addAll(rectangle, text);
    }

    public List<Text> getTexts() {
        return texts;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public Text getBack() {
        return back;
    }
}
