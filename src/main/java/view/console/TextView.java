package view.console;

import model.Direction;
import model.Model;
import model.Position;
import model.item.Diamond;

import java.util.List;
import java.util.Scanner;

/**
 * A text-based view for the Boulder Dash game.
 */
public class TextView implements View {
    private final Model model;
    private final int width;
    private static int side = 40;


    /**
     * Creates a new TextView with the given model.
     *
     * @param model the model to use for this view
     */
    public TextView(Model model) {
        this.model = model;
        this.width = 40 * 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayTitle() {
        displaySide();
        displayLeft(15);
        System.out.println("Welcome to the Boulder Dash!");
        displaySide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError() {
        displaySide();
        System.out.println("You can't move there!");
        displaySide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int askLevel() {
        Scanner kbd = new Scanner(System.in);
        int number;
        boolean verify = false;

        displaySide();
        displayLeft(15);
        System.out.println("What level do you want to play ?");
        displayLeft(14);
        System.out.println("Enter -1 if you want to quit");
        displaySide();

        do {
            number = kbd.nextInt();

            if (number > 0 && number < 8) {
                return number;
            } else if (number == -1) {
                return  number;
            }

        } while(true);

    }

    /**
     * {@inheritDoc}
     */
    public void displayKeys() {
        displaySide();
        System.out.println("You must put one of these keys : ");
        System.out.println("z : up");
        System.out.println("q : left");
        System.out.println("d : right");
        System.out.println("s : down");
        displaySide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayWinner() {
        if (model.isAlive()) {
            displaySide();
            displayLeft(9);
            System.out.println("You won ! Bravo !");
            displaySide();
        } else {
            displaySide();
            displayLeft(7);
            System.out.println("You lost... :(");
            displaySide();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void diamondsToPickUp() {
        System.out.println(new Diamond() + " : " + (this.model.getDiamTotal() - this.model.diamondsRemaining()) +
                "/" + this.model.getDiamTotal() + " | Objectif : " + this.model.objectiveCurrentLevel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLevel() {
        displaySide();
        displayLeft(4);
        System.out.println("Level " + this.model.getCurrentLevel());
        displaySide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayErrorHistoryStack() {
        displaySide();
        displayLeft(17);
        System.out.println("You have to enter a command first!");
        displaySide();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayErrorHistoryUndoStack() {
        displaySide();
        displayLeft(8);
        System.out.println("You can't redo now !");
        displaySide();
    }

    @Override
    public void displayBoard() {
        displaySide();
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(model.getItem(new Position(i, j)) + " ");
            }
            System.out.println();
        }
        displaySide();
    }

    @Override
    public String askCommand(List<Direction> list) {

        Scanner kbd = new Scanner(System.in);
        String line;

        do {
            line = kbd.nextLine();

            if (line.equals("undo")) {
                return line;
            } else if (line.equals("redo")) {
                return line;
            } else if (line.matches("[zqsd]") ) {
                if (list.contains(convertDirection(line))) {
                    return line;
                } else {
                    displayError();
                }
            } else {
                displayError();
            }

        } while (true);
    }

    /**
     * {@inheritDoc}
     */
    public Direction convertDirection(String line) {
        switch (line.charAt(0)) {
            case 'z' -> {
                return Direction.N;
            }
            case 'q' -> {
                return Direction.W;
            }
            case 's' -> {
                return Direction.S;
            }
            case 'd' -> {
                return Direction.E;
            }
            default -> throw new IllegalArgumentException("Not an allowed key.");
        }
    }

    /**
     * {@inheritDoc}
     */
    private void displaySide() {
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    /**
     * {@inheritDoc}
     */
    private void displayLeft(int middle) {
        for (int i = 0; i < (side - middle); i++) {
            System.out.print(" ");
        }
    }
}
