package medbot.command;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.Ui;

public abstract class Command {
    /**
     * Returns if the command type is the exitCommand.
     *
     * @return boolean value of whether the command is the exitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Abstract method that executes the command.
     *
     * @param scheduler the scheduler that will be read or modified
     */
    public abstract void execute(Scheduler scheduler, Ui ui) throws MedBotException;
}
