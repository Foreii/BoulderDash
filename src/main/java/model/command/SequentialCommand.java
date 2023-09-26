package model.command;

import java.util.List;
import java.util.ArrayList;

/**
 * The SequentialCommand class represents a command that consists of a sequence of other commands,
 * which will be executed in order when the execute method is called, and undone in reverse order
 * when the unexecute method is called.
 */
public class SequentialCommand implements Command {

    private final List<Command> commands;
    private final List<Command> undoneCommands;

    public SequentialCommand(List<Command> commands) {
        this.commands = commands;
        this.undoneCommands = new ArrayList<>();
    }

    /**
     * Executes all the commands in the sequence, in order.
     */
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
            undoneCommands.add(0, command);
        }
    }

    /**
     * Undoes all the commands in the sequence, in reverse order.
     */
    @Override
    public void unexecute() {
        for (Command command : undoneCommands) {
            command.unexecute();
        }
        undoneCommands.clear();

    }
}
