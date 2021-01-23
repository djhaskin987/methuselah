package io.github.djhaskin987.methuselah.command;

import java.util.Properties;
import java.util.Queue;

/**
 * Interface that describes a Methuselah leaf command.
 */
public interface Command {

    /**
     * Invoke the command given the options and remaining subcommands.
     *
     * @param options
     *                      the options given by the user.
     * @param arguments
     *                      the other arguments given by the user.
     * @return the exit code of the command.
     */
    int invoke(Properties options, Queue<String> arguments)
            throws CommandException;
}
