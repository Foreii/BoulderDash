package model;

import model.item.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class representing a board in a grid-based game.
 * The board consists of a 2D array of {@link Tile} objects.
 */
public class Board {
    private StringBuilder stringBuilder;
    private final Tile[][] tiles;
    private final int height;
    private final int width;

    /**
     * Constructs a new board with the specified number of rows and columns.
     * The board is initialized with {@link Tile} objects containing a default {@link Background} item.
     */
    public Board() {
        this.height = 22;
        this.width = 40;
        this.stringBuilder = new StringBuilder();
        this.tiles = new Tile[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = new Tile();
                this.setItem(new Background(), new Position(i, j));
            }
        }
    }

    /**
     * Returns the string builder of the board.
     * @return the string builder of the board
     */
    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows in the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the number of columns in the board.
     *
     * @return the number of columns in the board
     */
    public int getWidth() {
        return width;
    }



    /**
     * Sets the contents of a file to a string builder.
     *
     * @param file the file to set
     */
    public void setFile(File file) {
        stringBuilder.delete(0, stringBuilder.length());

        try {
            System.out.println(file.getAbsolutePath());
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringBuilder.append(data).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Converts the number of where the file is to a file.
     *
     * @param level the level to convert
     * @return the file corresponding to the level
     * @throws IllegalArgumentException if there is no file corresponding to the level
     */
    public File convert(int level) {
        File file;

        switch (level) {
            case 1 -> file = new File("src/main/resources/level1.txt");
            case 2 -> file = new File("src/main/resources/level2.txt");
            case 3 -> file = new File("src/main/resources/level3.txt");
            case 4 -> file = new File("src/main/resources/level4.txt");
            case 5 -> file = new File("src/main/resources/level5.txt");
            case 6 -> file = new File("src/main/resources/level6.txt");
            case 7 -> file = new File("src/main/resources/level7.txt");

            default -> throw new IllegalArgumentException("There is no other file.");
        }

        return file;
    }

    /**
     * Returns the {@link Item} at the specified {@link Position} on this board.
     *
     * @param position the position of the item to retrieve
     * @return the item at the specified position
     * @throws IndexOutOfBoundsException if the position is not within the bounds of the board
     */
    public Item getItem(Position position) {
        if (!contains(position)) {
            throw new IndexOutOfBoundsException("Position doesn't exist.");
        }
        return this.tiles[position.getRow()][position.getColumn()].getItem();
    }

    /**
     * Sets the specified {@link Item} at the specified {@link Position} on this board.
     *
     * @param item the item to set at the position
     * @param position the position at which to set the item
     * @throws IndexOutOfBoundsException if the position is not within the bounds of the board
     */
    public void setItem(Item item, Position position) {
        if (!contains(position)) {
            throw new IndexOutOfBoundsException("Position doesn't exist.");
        }
        this.tiles[position.getRow()][position.getColumn()].setItem(item);
        item.setPosition(position);
    }

    /**
     * Returns whether the specified {@link Position} is within the bounds of this board.
     *
     * @param position the position to check
     * @return true if the position is within the bounds of the board, false otherwise
     */
    public boolean contains(Position position) {
        return ((position.getRow() >= 0 && position.getRow() < height)
                && (position.getColumn() >= 0 && position.getColumn() < width));
    }

}
