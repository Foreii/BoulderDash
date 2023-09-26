package model.command;

import model.Direction;
import model.Game;
import model.Model;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    Model game;

    @BeforeEach
    void setUp() {
        game = new Game(1);
        game.start(1);
    }

    @Test
    void execute() {
        MoveCommand command = new MoveCommand(game, Direction.N);
        command.execute();
        Position position = new Position(1, 3);

        assertEquals(game.getPlayer().getPosition(), position);
    }

    @Test
    void unexecute() {
        MoveCommand command = new MoveCommand(game, Direction.N);
        command.execute();
        command.unexecute();
        Position position = new Position(2, 3);

        assertEquals(game.getPlayer().getPosition(), position);
    }
}