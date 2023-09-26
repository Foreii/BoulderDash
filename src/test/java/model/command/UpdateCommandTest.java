package model.command;

import model.Game;
import model.Model;
import model.Position;
import model.item.Item;
import model.item.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCommandTest {

    Model game;

    @BeforeEach
    void setUp() {
        game = new Game(2);
        game.start(2);
    }

    @Test
    void execute() {
        UpdateCommand command = new UpdateCommand(game);
        Item rock = game.getItem(new Position(2, 37));
        command.execute();
        Position positon = new Position(3, 37);

        assertEquals(rock.getPosition(), positon);
    }

    @Test
    void unexecute() {
        UpdateCommand command = new UpdateCommand(game);
        Item rock = game.getItem(new Position(2, 37));
        command.execute();
        command.unexecute();
        Position positon = new Position(2, 37);

        assertEquals(rock.getPosition(), positon);
    }
}