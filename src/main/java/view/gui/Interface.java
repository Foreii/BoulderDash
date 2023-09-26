package view.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interface extends Application {
    private Scene sceneMenu;
    private Scene sceneLevel;
    private Scene sceneGame;
    MainMenu mainMenu;
    LevelMenu levelMenu;
    GameCenter gameCenter;
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        mainMenu = new MainMenu();
        mainMenu.createMenu();
        sceneMenu = new Scene(mainMenu.getStackPane());

        levelMenu = new LevelMenu();
        sceneLevel = new Scene(levelMenu.getStackPane());

        gameCenter = new GameCenter();
        sceneGame = new Scene(gameCenter.getRoot());

        stage.setScene(sceneMenu);
        stage.setResizable(false);
        stage.show();
        this.stage = stage;

        mainMenu.getPlay().setOnMouseClicked(event -> {
            stage.setScene(sceneLevel);
        });

        switchSceneGame();
        switchSceneQuit();
        switchSceneMainMenu();
    }

    private void switchSceneGame() {
        for (int i = 0; i < 7; i++) {
            int finalI1 = i;
            levelMenu.getTexts().get(i).setOnMouseClicked(event -> {
                gameCenter.createGame(sceneGame, finalI1 + 1);
                stage.setScene(sceneGame);
            });

            int finalI = i;
            levelMenu.getRectangles().get(i).setOnMouseClicked(event -> {
                gameCenter.createGame(sceneGame, finalI + 1);
                stage.setScene(sceneGame);
            });
        }
    }

    private void switchSceneMainMenu() {
            this.levelMenu.getBack().setOnMouseClicked(event -> {
                stage.setScene(this.sceneMenu);
            });
    }

    private void switchSceneQuit() {
        this.gameCenter.getRestart().setOnMouseClicked(event -> {
            stage.setScene(sceneLevel);
        });
        this.gameCenter.getQuit().setOnMouseClicked(event -> {
            this.gameCenter.getPause().getStackPane().setVisible(false);
            stage.setScene(sceneLevel);
        });
    }
}
