package view.gui;

import javafx.scene.canvas.GraphicsContext;
import model.Direction;

/**
 Class that handles the display of tiles on a game board.
 */
public class TileDisplay {
    private GraphicsContext board;

    /**
     Constructor for the TileDisplay class.
     @param board GraphicsContext object representing the game board.
     */
    public TileDisplay(GraphicsContext board) {
        this.board = board;
    }

    /**
     Method that draws a wall tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawWall(int i, int j) {
        drawGrass(i, j);
        board.drawImage(ImageLoader.rock, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a grass tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawGrass(int i, int j) {
        board.drawImage(ImageLoader.grass, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a rock tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawRock(int i, int j) {
        board.drawImage(ImageLoader.box, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a background tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawBackground(int i, int j) {
        board.drawImage(ImageLoader.tileGrass, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a diamond tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawDiamond(int i, int j) {
        board.drawImage(ImageLoader.grass_2, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
        board.drawImage(ImageLoader.diamond, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a player tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawPlayer(int i, int j, Direction direction) {
        drawTileGrass(i, j);

        if (direction == Direction.N) {
            board.drawImage(ImageLoader.up, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
        } else if (direction == Direction.S) {
            board.drawImage(ImageLoader.down, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
        } else if (direction == Direction.E) {
            board.drawImage(ImageLoader.right, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
        } else if (direction == Direction.W) {
            board.drawImage(ImageLoader.left, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
        }

    }

    /**
     Method that draws a grass tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawTileGrass(int i, int j) {
        board.drawImage(ImageLoader.tileGrass, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking forward on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawUP2(int i, int j) {
        board.drawImage(ImageLoader.up2, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking forward on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawUP1(int i, int j) {
        board.drawImage(ImageLoader.up1, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking down on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawDOWN1(int i, int j) {
        board.drawImage(ImageLoader.down1, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking down on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawDOWN2(int i, int j) {
        board.drawImage(ImageLoader.down2, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking left on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawLEFT1(int i, int j) {
        board.drawImage(ImageLoader.left1, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking left on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawLEFT2(int i, int j) {
        board.drawImage(ImageLoader.left2, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking right on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawRIGHT1(int i, int j) {
        board.drawImage(ImageLoader.right1, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws the player looking right on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawRIGHT2(int i, int j) {
        board.drawImage(ImageLoader.right2, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }

    /**
     Method that draws a mad emote on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawMAD1(int i, int j) {
        board.drawImage(ImageLoader.mad_1, (j * UIConstants.TILES_SIZE + 30), i * UIConstants.TILES_SIZE, 20, 20);
    }

    /**
     Method that draws a mad emote on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawMAD2(int i, int j) {
        board.drawImage(ImageLoader.mad_2, (j * UIConstants.TILES_SIZE + 30), i * UIConstants.TILES_SIZE, 20, 20);
    }

    /**
     Method that draws a mad emote on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawMAD3(int i, int j) {
        board.drawImage(ImageLoader.mad_3, (j * UIConstants.TILES_SIZE + 30), i * UIConstants.TILES_SIZE, 20, 20);
    }

    /**
     Method that draws a mad emote on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawMAD4(int i, int j) {
        board.drawImage(ImageLoader.mad_4, (j * UIConstants.TILES_SIZE + 30), i * UIConstants.TILES_SIZE, 20, 20);
    }

    /**
     Method that draws a door tile on the game board.
     @param i The row number of the tile.
     @param j The column number of the tile.
     */
    public void drawDoor(int i, int j) {
        drawTileGrass(i, j);
        board.drawImage(ImageLoader.door, j * UIConstants.TILES_SIZE, i * UIConstants.TILES_SIZE, UIConstants.TILES_SIZE, UIConstants.TILES_SIZE);
    }
}
