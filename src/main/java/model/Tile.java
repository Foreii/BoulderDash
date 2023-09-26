package model;

import model.item.Item;
import model.item.Background;

public class Tile {
    private Item item;


    /**
     * Constructs a new tile with a default {@link Background} item.
     */
    public Tile() {
        this.item = new Background();
    }

    /**
     * Constructs a new tile with the specified {@link Item}.
     *
     * @param item the item to be placed on the tile
     */
    public Tile(Item item) {
        this.item = item;
    }

    /**
     * Sets the item on the tile.
     *
     * @param item the item to be placed on the tile
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the item on the tile.
     *
     * @return the item on the tile
     */
    public Item getItem() {
        return this.item;
    }
}
