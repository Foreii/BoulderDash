package model;

public enum Direction {
    /** The north direction. */
    N(-1, 0),
    /** The south direction. */
    S(1, 0),
    /** The west direction. */
    W(0, -1),
    /** The east direction. */
    E(0, 1),
    /** The south-east direction */
    SE(1, 1),
    /** The south-west direction */
    SW(1, -1);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Constructs a new direction with the specified delta row and column.
     *
     * @param deltaRow    the delta row for this direction
     * @param deltaColumn the delta column for this direction
     */
    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Returns the delta row for this direction.
     *
     * @return the delta row for this direction
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Returns the delta column for this direction.
     *
     * @return the delta column for this direction
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

    /**
     * Returns the opposite direction of this direction.
     *
     * @return the opposite direction of this direction
     * @throws IllegalArgumentException if this direction is not one of the four cardinal directions
     */
    public Direction opposite() {
        switch (this) {
            case S -> {
                return N;
            }
            case N -> {
                return S;
            }
            case E -> {
                return W;
            }
            case W -> {
                return E;
            }
            default -> throw new IllegalArgumentException("There are only 4 directions.");
        }
    }
}
