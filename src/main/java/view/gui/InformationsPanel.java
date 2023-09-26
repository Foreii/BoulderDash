package view.gui;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InformationsPanel {
    private StackPane stackPane;
    private Text objectiveDiamond;
    private Text totalDiamonds;
    private Text level;
    Rectangle rectangle;

    public InformationsPanel() {
        stackPane = new StackPane();
        createContent();
    }

    /**
     * Creates and add the content to the stack pane.
     */
    public void createContent() {
        rectangle = new Rectangle(250, 50);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.4);
        rectangle.setTranslateX((rectangle.getWidth() / 2) + 100 - (UIConstants.APP_WIDTH / 2.0));

        Rectangle r =new Rectangle(250, 50);
        r.setFill(Color.BLACK);
        r.setOpacity(0.4);

        totalDiamonds = new Text("Diamonds");
        totalDiamonds.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 30));
        totalDiamonds.setTranslateX((rectangle.getWidth() / 2) + 100 - (UIConstants.APP_WIDTH / 2.0));


        objectiveDiamond = new Text("Objective 10");
        objectiveDiamond.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 30));
        objectiveDiamond.setTranslateX((UIConstants.APP_WIDTH / 2.0) - 250);

        Rectangle re = new Rectangle(250, 50);
        re.setFill(Color.BLACK);
        re.setOpacity(0.4);
        re.setTranslateX((UIConstants.APP_WIDTH / 2.0) - 250);

        level = new Text("Level 1");
        level.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 30));

        stackPane.getChildren().addAll(rectangle, re, r,objectiveDiamond, totalDiamonds, level);
        stackPane.setPadding(new Insets(10, 0, 10, 0)); // set top, right, bottom, and left margins to 10

        stackPane.setStyle("-fx-background-color: darkgrey;");
    }

    /**
     * Updates the texts.
     * @param nbLevel the level
     * @param objective the number of objective of diamond
     * @param currentDiamond the number of current diamond
     * @param total the total of diamond
     */
    public void updateData(int nbLevel, int objective, int currentDiamond, int total) {
        level.setText("Level " + nbLevel);
        objectiveDiamond.setText("Objective " + objective);
        totalDiamonds.setText("Diamonds : " + currentDiamond + "/" + total);
    }

    /**
     * Returns the stack pane.
     * @return The stack pane.
     */
    public StackPane getStackPane() {
        return stackPane;
    }

}
