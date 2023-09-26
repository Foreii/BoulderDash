package model;

import model.item.Diamond;
import model.item.Player;
import model.item.Rock;
import model.item.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();

        for (int i = 0; i < 22; i++) {
            board.setItem(new Wall(), new Position(i, 0));
            board.setItem(new Wall(), new Position(i, 39));
        }

        for (int j = 0; j < 40; j++) {
            board.setItem(new Wall(), new Position(0, j));
            board.setItem(new Wall(), new Position(21, j));
        }

    }

    @Test
    void moveOnGrass() {
        Game game = new Game(1);
        game.start(1);

        Position playerPosition = game.getPlayer().getPosition();
        game.move(Direction.N);
        Position newPosition = new Position(playerPosition.getRow() - 1, playerPosition.getColumn());


        assertEquals(game.getPlayer().getPosition(), newPosition);
    }

    @Test
    void moveOnDiamond() {
        Player player = new Player();
        Diamond diamond = new Diamond();
        board.setItem(player, new Position(1, 1));
        board.setItem(diamond, new Position(1, 2));

        Game game = new Game(board, player);
        game.move(Direction.E);

        assertEquals(game.getPlayer().getNbDiamonds(), 1);
    }

    @Test
    void moveOnRockAndPushIt() {
        Player player = new Player();
        Rock rock = new Rock();
        Wall wall = new Wall();
        board.setItem(player, new Position(1, 1));
        board.setItem(rock, new Position(1, 2));
        board.setItem(wall, new Position(2, 3));

        Game game = new Game(board, player);
        game.move(Direction.E);

        Position newPosition = new Position(1, 3);

        assertEquals(rock.getPosition(), newPosition);
        assertEquals(player.getPosition(), new Position(1, 2));
    }

    @Test
    void moveOnRockButThereIsAWallBehind() {
        Player player = new Player();
        Rock rock = new Rock();
        Wall wall = new Wall();
        board.setItem(player, new Position(1, 1));
        board.setItem(rock, new Position(1, 2));
        board.setItem(wall, new Position(1, 3));

        Game game = new Game(board, player);
        assertThrows(IllegalArgumentException.class, () -> game.move(Direction.E));
    }

    @Test
    void moveOnWall() {
        Player player = new Player();
        Wall wall = new Wall();
        board.setItem(player, new Position(1, 1));
        board.setItem(wall, new Position(1, 2));

        Game game = new Game(board, player);
        assertThrows(IllegalArgumentException.class, () -> game.move(Direction.E));
    }

    @Test
    void fall() {
        Rock rock = new Rock();
        board.setItem(rock, new Position(1, 1));

        Game game = new Game(board, new Player());
        game.fall();

        Position expectedPosition = new Position(20, 1);

        assertEquals(rock.getPosition(), expectedPosition);

    }

    @Test
    void fallOnWall() {
        Rock rock = new Rock();
        Wall item = new Wall();
        board.setItem(rock, new Position(1, 1));
        board.setItem(item, new Position(2, 1));

        Game game = new Game(board, new Player());
        game.fall();

        Position expectedPosition = new Position(1, 1);

        assertEquals(rock.getPosition(), expectedPosition);

    }

    @Test
    void slipOnDiamond() {
        Rock rock = new Rock();
        Diamond item = new Diamond();
        board.setItem(rock, new Position(1, 1));
        board.setItem(item, new Position(2, 1));

        Game game = new Game(board, new Player());
        game.fall();

        Position expectedPosition = new Position(20, 2);

        assertEquals(rock.getPosition(), expectedPosition);

    }

    @Test
    void slipOnRock() {
        Rock rock = new Rock();
        Rock item = new Rock();
        board.setItem(rock, new Position(1, 1));
        board.setItem(item, new Position(2, 1));

        Game game = new Game(board, new Player());
        game.fall();

        Position expectedPosition = new Position(20, 2);

        assertEquals(rock.getPosition(), expectedPosition);

    }
}