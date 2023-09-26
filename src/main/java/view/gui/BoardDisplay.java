package view.gui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import model.Direction;
import model.Model;
import model.Position;
import model.item.*;

/**
 The BoardDisplay class is responsible for displaying the game board and its elements on the screen.

 It uses a Canvas object to draw the board and the various elements, such as grass, rocks, and diamonds.

 The class also includes a ScrollPane to allow the board to be panned around and a TileDisplay object to handle the actual drawing of the elements on the board.

 The BoardDisplay class also has an events method that consumes any mouse pressed events on the ScrollPane to prevent them from being processed.
 */

public class BoardDisplay {
    private ScrollPane scrollPane;
    private Model game;
    private Canvas canvas;
    private GraphicsContext board;
    private TileDisplay tileDisplay;

    /**
     Constructs a new BoardDisplay object.
     Initializes the ScrollPane, Canvas, and TileDisplay objects and sets up the ScrollPane to disable scrolling and panning.
     */
    public BoardDisplay() {
        scrollPane = new ScrollPane();
        canvas = new Canvas(UIConstants.BOARD_WIDTH, UIConstants.BOARD_HEIGHT);
        board = canvas.getGraphicsContext2D();

        scrollPane.setContent(canvas);
        scrollPane.setPrefSize(canvas.getWidth(), canvas.getHeight());
        scrollPane.setMaxSize(UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);

        tileDisplay = new TileDisplay(board);
        events();
    }

    /**
     Returns the GraphicsContext object used to draw on the board.
     @return The GraphicsContext object used to draw on the board.
     */
    public GraphicsContext getBoard() {
        return board;
    }

    /**
     Returns the Canvas object on which the board is drawn.
     @return The Canvas object on which the board is drawn.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     Returns the TileDisplay object responsible for drawing the individual elements on the board.
     @return The TileDisplay object responsible for drawing the individual elements on the board.
     */
    public TileDisplay getTileDisplay() {
        return tileDisplay;
    }

    public void events() {
        this.scrollPane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Consume the event to prevent it from being processed
                event.consume();
            }
        });
    }

    /**
     * Gets the scroll pane of the board.
     * @return the scroll pane of the board
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Starts the game;
     * @param game the game
     */
    public void start(Model game) {
        this.game = game;
    }


    /**
     * Shows the board.
     * @param direction the direction in which the player is going to
     */
    public void showBoard(Direction direction) {
        board.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 40; j++) {
                Item item = game.getItem(new Position(i, j));

                if (item instanceof Dirt) {
                    tileDisplay.drawGrass(i, j);
                } else if (item instanceof Rock) {
                    tileDisplay.drawRock(i, j);
                } else if (item instanceof Wall) {
                    tileDisplay.drawWall(i, j);
                } else if (item instanceof Diamond) {
                    tileDisplay.drawDiamond(i, j);
                } else if (item instanceof Background) {
                    tileDisplay.drawBackground(i, j);
                } else if (item instanceof Player) {
                    tileDisplay.drawPlayer(i, j, direction);
                } else if (item instanceof Door) {
                    tileDisplay.drawDoor(i, j);
                }
            }
        }
    }
}
