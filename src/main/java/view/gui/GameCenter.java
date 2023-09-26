package view.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Direction;
import model.Game;
import model.Model;
import model.command.Command;
import model.command.MoveCommand;
import model.command.SequentialCommand;
import model.command.UpdateCommand;
import util.Observer;

import java.util.Arrays;
import java.util.List;

/**
 The GameCenter class is responsible for managing and displaying the game.
 It includes a BoardDisplay object to display the game board and its elements, an InformationsPanel object to display information about the game, and a MenuEsc and MenuDead object to display pause and game over menus.
 The class also includes a StackPane object to hold the various UI elements and a VBox object to hold the InformationsPanel and BoardDisplay objects.
 The GameCenter class has a createGame method that sets up and starts a new game, and an update method that handles updates to the game state and display.
 It also has events method that sets up event handlers for key and mouse events to control the game.
 */
public class GameCenter implements Observer {
    private Model game;
    private Scene scene;
    private VBox vBox;
    private StackPane root;
    private InformationsPanel info;
    private MenuEsc pause;
    private MenuDead dead;
    private BoardDisplay display;
    Timeline timeline = new Timeline();
    private Direction direction;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean isPaused = false;
    private boolean isMad = false;
    private int cpt = 0;
    private int compteur = 0;
    private int fragement = 0;

    /**
     Constructs a new GameCenter object.
     */
    public GameCenter() {
        display = new BoardDisplay();
        info = new InformationsPanel();
        pause = new MenuEsc();
        dead = new MenuDead();
        vBox = new VBox();
        root = new StackPane();
        add();
    }

    /**
     * Adds to the stack pane and the vbox the elements.
     */
    public void add() {
        vBox.getChildren().addAll(this.info.getStackPane(), this.display.getScrollPane());
        root.getChildren().addAll(vBox, pause.getStackPane(), dead.getStackPane());
    }

    /**
     * Returns the stack pane.
     * @return returns the stack pane
     */
    public StackPane getRoot() {
        return this.root;
    }

    /**
     * Creates the game.
     * @param scene the scene in which our game is.
     * @param level the level.
     */
    public void createGame(Scene scene, int level) {
        this.scene = scene;
        game = new Game(level);
        game.start(level);
        game.addObserver(this);
        display.start(game);
        focusPlayer();
        isPaused = false;
        dead.getStackPane().setVisible(false);
        info.updateData(game.getCurrentLevel(), game.objectiveCurrentLevel(),
                this.game.getDiamTotal() - this.game.diamondsRemaining(), game.getDiamTotal());
        display.showBoard(Direction.N);
        events();

        timeline = new Timeline(new KeyFrame(Duration.millis(60), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updatePlayer();
            }
        }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();
    }

    /**
     * Updates the look of the player when he is moving.
     */
    public void updatePlayer() {
        int col = game.getPlayer().getPosition().getColumn();
        int row = game.getPlayer().getPosition().getRow();

        if (upPressed) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);

            if (cpt == 0) {
                this.display.getTileDisplay().drawUP1(row, col);
                this.cpt++;
            } else if (cpt == 1) {
                this.display.getTileDisplay().drawUP2(row, col);
                cpt = 0;
            }
        } else if (direction == Direction.N) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);
            this.display.getTileDisplay().drawPlayer(row, col, direction);
        }

        if (leftPressed) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);

            if (cpt == 0) {
                this.display.getTileDisplay().drawLEFT1(row, col);
                cpt++;
            } else if (cpt == 1) {
                this.display.getTileDisplay().drawLEFT2(row, col);
                cpt = 0;
            }
        } else if (direction == Direction.W) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);
            this.display.getTileDisplay().drawPlayer(row, col, direction);
        }

        if (rightPressed) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);

            if (cpt == 0) {
                this.display.getTileDisplay().drawRIGHT1(row, col);
                cpt++;
            } else if (cpt == 1) {
                this.display.getTileDisplay().drawRIGHT2(row, col);
                cpt = 0;
            }
        } else if (direction == Direction.E) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);
            this.display.getTileDisplay().drawPlayer(row, col, direction);
        }
        if (downPressed) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);

            if (cpt == 0) {
                this.display.getTileDisplay().drawDOWN1(row, col);
                cpt++;
            } else if (cpt == 1) {
                this.display.getTileDisplay().drawDOWN2(row, col);
                cpt = 0;
            }
        } else if (direction == Direction.S) {
            this.display.getBoard().clearRect((col * UIConstants.TILES_SIZE) + 1, (row * UIConstants.TILES_SIZE) + 1, UIConstants.TILES_SIZE - 1, UIConstants.TILES_SIZE - 1);
            this.display.getTileDisplay().drawTileGrass(row, col);
            this.display.getTileDisplay().drawPlayer(row, col, direction);
        }

        if (isMad) {
            fragement++;

            if (compteur == 0) {
                this.display.getTileDisplay().drawMAD1(row, col);
                if (fragement > 2) {
                    compteur = 1;
                    fragement = 0;
                }
            } else if (compteur == 1) {
                this.display.getTileDisplay().drawMAD2(row + 30, col);
                compteur++;

                if (fragement > 2) {
                    compteur = 2;
                    fragement = 0;
                }
            } else if (compteur == 2) {
                this.display.getTileDisplay().drawMAD3(row, col);
                compteur++;

                if (fragement > 2) {
                    compteur = 3;
                    fragement = 0;
                }
            } else {
                this.display.getTileDisplay().drawMAD4(row, col);
                compteur++;

                if (fragement > 4) {
                    compteur = 0;
                    fragement = 0;
                }
            }

        }

    }

    /**
     * Regroups all the events.
     */
    private void events() {
        scene.setOnKeyPressed(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    isMad = false;

                    if (isPaused) {
                        if (event.getCode() == KeyCode.ESCAPE) {
                            isPaused = false;
                            pause.getStackPane().setVisible(false);
                        }
                    } else if (game.isAlive() && (keyName.equals("UP") || keyName.equals("A") || keyName.equals("E") ||
                            keyName.equals("DOWN") || keyName.equals("LEFT") || keyName.equals("RIGHT"))) {

                        if (keyName.equals("UP")) {
                            direction = Direction.N;
                            upPressed = true;
                        } else if (keyName.contains("LEFT")) {
                            direction = Direction.W;
                            leftPressed = true;
                        } else if (keyName.contains("RIGHT")) {
                            direction = Direction.E;
                            rightPressed = true;
                        } else if (keyName.contains("DOWN")) {
                            direction = Direction.S;
                            downPressed = true;
                        } else if (keyName.equals("A")) {
                            if (!game.historyIsEmpty()) {
                                game.undo();
                            }
                        } else {
                            if (!game.historyUndoIsEmpty()) {
                                game.redo();
                            }
                        }

                        List<Direction> list = game.getPossibleDirections();
                        if (list.contains(direction) && (!keyName.equals("A") && !keyName.equals("E"))) {
                            List<Command> commands = Arrays.asList(new MoveCommand(game, direction), new UpdateCommand(game));
                            Command command = new SequentialCommand(commands);
                            game.executeCommand(command);
                        } else {
                            isMad = true;
                        }

                        if (game.getPlayer().getPosition().getColumn() == 19 && direction == Direction.E) {
                            display.getScrollPane().setHvalue(1.0);
                        } else if (game.getPlayer().getPosition().getColumn() == 18 && direction == Direction.W) {
                            display.getScrollPane().setHvalue(-1.0);
                        } else if (game.getPlayer().getPosition().getRow() == 8 && direction == Direction.S) {
                            display.getScrollPane().setVvalue(0.5);
                        } else if (game.getPlayer().getPosition().getRow() == 7 && direction == Direction.N) {
                            display.getScrollPane().setVvalue(-0.5);
                        } else if (game.getPlayer().getPosition().getRow() == 14 && direction == Direction.S) {
                            display.getScrollPane().setVvalue(1);
                        } else if (game.getPlayer().getPosition().getRow() == 13 && direction == Direction.N) {
                            display.getScrollPane().setVvalue(-1);
                        }

                    } else if (event.getCode() == KeyCode.ESCAPE) {
                        isPaused = true;
                        this.pause.getStackPane().setVisible(true);
                    }
                }


        );

        scene.setOnKeyReleased(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();

                    if (keyName.contains("UP")) {
                        upPressed = false;
                    }

                    if (keyName.contains("LEFT")) {
                        leftPressed = false;
                    }

                    if (keyName.contains("RIGHT")) {
                        rightPressed = false;
                    }

                    if (keyName.contains("DOWN")) {
                        downPressed = false;
                    }
                }
        );

        this.pause.getRestart().setOnMouseClicked(event -> {
            pause.getStackPane().setVisible(false);
            game.start(game.getCurrentLevel());
            timeline.play();
            display.showBoard(Direction.N);
            info.updateData(game.getCurrentLevel(), game.objectiveCurrentLevel(),
                    this.game.getDiamTotal() - this.game.diamondsRemaining(), game.getDiamTotal());
            focusPlayer();
            isPaused = false;
        });

        this.dead.getRestart().setOnMouseClicked(event -> {
            dead.getStackPane().setVisible(false);
            game.start(game.getCurrentLevel());
            timeline.play();
            display.showBoard(Direction.N);
            info.updateData(game.getCurrentLevel(), game.objectiveCurrentLevel(),
                    this.game.getDiamTotal() - this.game.diamondsRemaining(), game.getDiamTotal());
            focusPlayer();
            isPaused = false;
        });


        this.pause.getResume().setOnMouseClicked(event -> {
            isPaused = false;
            pause.getStackPane().setVisible(false);
        });

    }

    /**
     * Gets the main stack pane from the MenuEsc class.
     * @return the stack pane from the MenuEsc class
     */
    public MenuEsc getPause() {
        return pause;
    }

    /**
     * Gets the restart text from the MenuDead class.
     * @return the restart text from the MenuDead class
     */
    public Text getRestart() {
        return this.dead.getChange();
    }

    /**
     * Puts the scroll pane on the player.
     */
    private void focusPlayer() {
        if (game.getPlayer().getPosition().getColumn() < 18) {
            display.getScrollPane().setHvalue(0);
        } else if (game.getPlayer().getPosition().getColumn() >= 18 && game.getPlayer().getPosition().getColumn() < 22) {
            display.getScrollPane().setHvalue(0.5);
        } else if (game.getPlayer().getPosition().getColumn() > 22) {
            display.getScrollPane().setHvalue(1);
        }

        if (game.getPlayer().getPosition().getRow() < 8) {
            display.getScrollPane().setVvalue(0);
        } else if (game.getPlayer().getPosition().getRow() > 8 && game.getPlayer().getPosition().getRow() <= 11) {
            display.getScrollPane().setVvalue(0.5);
        } else if (game.getPlayer().getPosition().getRow() > 11) {
            display.getScrollPane().setVvalue(1);
        }

    }

    /**
     * Returns the quit button
     * @return the quit button
     */
    public Text getQuit() {
        return this.pause.getQuit();
    }

    @Override
    public void update() {
        this.display.showBoard(direction);

        if (!game.isAlive() || game.hasPassed()) {
            this.dead.getStackPane().setVisible(true);
            isPaused = true;
            timeline.stop();
        }

        info.updateData(game.getCurrentLevel(), game.objectiveCurrentLevel(), game.getDiamTotal() - game.diamondsRemaining(), game.getDiamTotal());
    }
}
