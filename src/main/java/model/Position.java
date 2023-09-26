package model;

public class Position {
    private final int row;
    private final int column;

    /**
     * Constructs a new Position object with the specified row and column.
     *
     * @param row the row of the position
     * @param column the column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row of this position.
     *
     * @return the row of this position
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this position.
     *
     * @return the column of this position
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Returns the next position in the specified direction from this position.
     *
     * @param dir the direction to move in
     * @return the next position in the specified direction
     */
    public Position next(Direction dir) {
        int newRow = this.row;
        int newColumn = this.column;

        newRow = newRow + dir.getDeltaRow();
        newColumn = newColumn + dir.getDeltaColumn();

        return new Position(newRow, newColumn);
    }

    /**
     * Determines if this position is equal to the specified object.
     * Two positions are equal if they have the same row and column.
     *
     * @param o the object to compare to this position
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;

        return row == position.row && column == position.column;
    }

    /**
     * Returns a string representation of this position.
     * The string has the form "(row,column)".
     *
     * @return a string representation of this position
     */
    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}
