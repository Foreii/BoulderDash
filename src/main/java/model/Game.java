package model;

import model.command.Command;
import model.item.*;
import util.Observer;

import java.util.*;

public class Game implements Model {
    private Board board;
    private Player player;
    private int currentLevel;
    private int nbDiamonds;
    private int diamTotal;
    private List<Integer> objectiveDiam;
    private Item movedItem;
    private List<Position> doors;
    private Stack<Command> history;
    private Stack<Command> historyUndo;
    private Map<Item, Position> fallenItems;
    private List<Observer> observers = new ArrayList<>();
    private Door door;

    /**
     Constructs a new game with the given level.
     @param level the level of the game
     */
    public Game(int level) {
        this.board = new Board();
        this.player = new Player();
        this.currentLevel = level;
        this.history = new Stack<>();
        this.historyUndo = new Stack<>();
        this.fallenItems = new HashMap<>();
        this.objectiveDiam = Arrays.asList(9, 7, 18, 4, 5, 5, 4);
        this.doors = Arrays.asList(new Position(16, 38), new Position(19, 18),
                new Position(39, 19),new Position(21, 38),new Position(39, 21),new Position(38, 19),
                new Position(39 , 5));
    }

    /**
     * Constructs a game with the given board.
     * @param board the board of the game
     */
    public Game(Board board, Player player) {
        // it's for junit tests;
        this.board = board;
        this.player = player;
    }

    /**
     Starts the game by converting the board to the current level.
     */
    @Override
    public void start(int level) {
        convertToBoard(level);
        this.currentLevel = level;
    }

    /**
     Makes the player move in the given direction, if possible.

     @param direction the direction in which the player should move

     @throws IllegalArgumentException if the player isn't allowed to move this way
     @throws IndexOutOfBoundsException if the position doesn't exist
     */
    @Override
    public void move(Direction direction) {
        Position newPos = this.player.getPosition().next(direction);

        if (!this.board.contains(newPos)) {
            throw new IndexOutOfBoundsException("This position doesn't exist.");
        }
        if (!getPossibleDirections().contains(direction)) {
            throw new IllegalArgumentException("The player isn't allowed to move this way.");
        }

        Item item = this.board.getItem(newPos);
        item.move(direction, board, player);
        this.movedItem = item;
        this.player.move(direction, this.board, player);

        if (objectiveDiam.get(currentLevel - 1) == this.player.getNbDiamonds() && door == null) {
            Door door = new Door();
            setItem(door, doors.get(currentLevel - 1));
        }
    }

    @Override
    public boolean hasPassed() {
        return this.player.isHasSucceeded();
    }

    /**
     Updates the game by making movable items fall if possible.
     */
    @Override
    public void fall() {
        fallenItems.clear();

        for (int i = 0; i < 40; i++) {
            for (int j = 22 - 1; j >= 0; j--) {
                Item item = this.board.getItem(new Position(j, i));
                Position position = item.getPosition().next(Direction.S);

                if (item.isMovable()) {
                    setFallenItems(item);

                    if (!board.getItem(position).isConcrete() && !board.getItem(position).equals(player)) {
                        falling(item.getPosition());


                    } else if (board.getItem(position).isMovable() &&
                            board.getItem(position.next(Direction.N)).isMovable()) {
                        if ((!board.getItem(position.next(Direction.W)).isConcrete()) ||
                                (!board.getItem(position.next(Direction.E)).isConcrete())) {
                            setFallenItems(board.getItem(position.next(Direction.N)));
                            fallingDiagonally(position.next(Direction.N));
                        }
                    }
                }
            }
        }
    }

    @Override
    public Map<Item, Position> getFallenItems() {
        return new HashMap<>(this.fallenItems);
    }

    @Override
    public void setFallenItems(Item fallenItems) {
        this.fallenItems.put(fallenItems, fallenItems.getPosition());
    }

    @Override
    public List<Direction> getPossibleDirections() {
        return this.player.getPossibleDirections(board);
    }

    /**
     * Converts the contents of a text file to a board of {@link Item}s.
     * The text file is specified by the level number, and the board is populated with the corresponding items.
     *
     * @param level the level number, corresponding to a text file with the same name
     */
    @Override
    public void convertToBoard(int level) {
        this.board.getStringBuilder().delete(0, this.board.getStringBuilder().length());
        this.board = new Board();
        this.board.setFile(this.board.convert(level));
        this.player = new Player();
        this.nbDiamonds = 0;
        this.diamTotal = 0;
        this.door = null;

        int row = 0;
        int col = 0;
        for (int i = 0; i < this.board.getStringBuilder().length(); i++) {
            char charItem = this.board.getStringBuilder().charAt(i);

            switch (charItem) {
                case 'G', 'Q' -> {
                    this.board.setItem(new Dirt(), new Position(row, col));
                }
                case 'W', 'B' -> {
                    this.board.setItem(new Wall(), new Position(row, col));
                }
                case 'R' -> {
                    this.board.setItem(new Rock(), new Position(row, col));
                }
                case 'D' -> {
                    this.board.setItem(new Diamond(), new Position(row, col));
                    this.nbDiamonds++;
                    this.diamTotal++;
                }
                case 'P' -> {
                    this.board.setItem(player, new Position(row, col));
                }
                case 'E' -> {
                    this.board.setItem(new Background(), new Position(row, col));
                }
                default -> col--;
            }

            col++;
            if (col >= this.board.getWidth()) {
                col = 0;
                row++;
            }
        }
        currentLevel = level;
        notifyObservers();
    }

    @Override
    public void executeCommand(Command command) {
        history.push(command);
        command.execute();

        historyUndo.clear();
    }

    @Override
    public void undo() {
        if (history.isEmpty()) {
            throw new IllegalArgumentException("Empty stack.");
        }

        Command command = history.pop();
        command.unexecute();
        historyUndo.push(command);
    }

    @Override
    public void redo() {
        if (historyUndo.isEmpty()) {
            throw new IllegalArgumentException("Empty stack.");
        }

        Command command = historyUndo.pop();
        history.push(command);
        command.execute();
    }

    /**
     * Creates or changes the level of the board.
     * @param level the level
     */
    @Override
    public void createLevel(int level) {
        this.convertToBoard(level);
        this.currentLevel = level;
    }

    @Override
    public Item getMovedItem() {
        return movedItem;
    }

    /**
     * Makes the movable items fall vertically if needed
     * @param position the position of the movable item
     */
    private void falling(Position position) {
        while (board.contains(position.next(Direction.S)) && !board.getItem(position.next(Direction.S)).isConcrete()) {
            Item rock = board.getItem(position);
            Item item = board.getItem(position.next(Direction.S));

            if (board.getItem(position.next(Direction.S)).equals(player)) {
                Item background = new Background();
                board.setItem(background, position.next(Direction.S));
                player.setAlive(false);
            }
            board.setItem(new Background(), rock.getPosition());
            board.setItem(rock, rock.getPosition().next(Direction.S));

            position = position.next(Direction.S);
        }
    }

    /**
     * Returns the number of diamonds collected by the player.
     *
     * @return the number of diamonds collected by the player
     */
    public int getNbDiamonds() {
        return nbDiamonds;
    }

    /**
     * Returns the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the total number of diamonds in the game.
     *
     * @return the total number of diamonds in the game
     */
    @Override
    public int getDiamTotal() {
        return diamTotal;
    }

    /**
     * Makes the movable items fall diagonally if needed
     * @param position the position of the movable item
     */
    private void fallingDiagonally(Position position) {
        Item item = board.getItem(position);

        while ((board.getItem(position.next(Direction.S)).isMovable()) &&
                ((!board.getItem(position.next(Direction.SE)).isConcrete()) &&
                        (!board.getItem(position.next(Direction.SE)).equals(player))) ||
                ((!board.getItem(position.next(Direction.SW)).isConcrete()) &&
                        (!board.getItem(position.next(Direction.SW)).equals(player)))) {

            if (!board.getItem(position.next(Direction.SW)).isConcrete() &&
                    !board.getItem(position.next(Direction.SW)).equals(player)) {
                board.setItem(new Background(), position);
                position = position.next(Direction.SW);
                board.setItem(item, position);
            } else if (!board.getItem(position.next(Direction.SE)).isConcrete() &&
                    !board.getItem(position.next(Direction.SE)).equals(player)) {
                board.setItem(new Background(), position);
                position = position.next(Direction.SE);
                board.setItem(item, position);
            }

            if ((board.getItem(position.next(Direction.S))).equals(player)) {
                Item background = new Background();
                board.setItem(background, position.next(Direction.S));
                player.setAlive(false);
            }

            falling(position);
        }
    }

    /**
     * Returns the Item at the given position on the game board.
     *
     * @param position the position on the game board
     * @return the Item at the given position on the game board
     */
    @Override
    public Item getItem(Position position) {
        return this.board.getItem(position);
    }

    /**
     * Sets the given Item at the given position on the game board.
     *
     * @param item the Item to set at the given position on the game board
     * @param position the position on the game board where the Item should be set
     */
    @Override
    public void setItem(Item item, Position position) {
        Position p = new Position(position.getRow(), position.getColumn());
        this.board.setItem(item, p);
    }

    /**
     * Returns the number of diamonds remaining in the game.
     *
     * @return the number of diamonds remaining in the game
     */
    public int diamondsRemaining() {
        return this.nbDiamonds - this.player.getNbDiamonds();
    }

    /**
     * Returns a boolean value indicating whether the player is alive.
     *
     * @return true if the player is alive, false otherwise
     */
    public boolean isAlive() {
        return this.player.isAlive();
    }

    /**
     * Returns the number of diamonds the player needs to collect in order to complete the current level.
     *
     * @return the number of diamonds the player needs to collect in order to complete the current level
     */
    @Override
    public int objectiveCurrentLevel() {
        return this.objectiveDiam.get(currentLevel - 1);
    }

    /**
     * Returns the current level of the game.
     *
     * @return the current level of the game
     */
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Returns a boolean value indicating whether the history of executed commands is empty.
     *
     * @return true if the history of executed commands is empty, false otherwise
     */
    @Override
    public boolean historyIsEmpty() {
        return this.history.isEmpty();
    }

    /**
     * Returns a boolean value indicating whether the history of undone commands is empty.
     *
     * @return true if the history of undone commands is empty, false otherwise
     */
    @Override
    public boolean historyUndoIsEmpty() {
        return this.historyUndo.isEmpty();
    }

    /**
     * Adds the given Observer to the list of observers.
     *
     * @param observer the Observer to add to the list of observers
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes the given Observer from the list of observers.
     *
     * @param observer the Observer to remove from the list of observers
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all the observers in the list of any changes.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
