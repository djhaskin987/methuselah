package io.github.djhaskin987.methuselah.command;

import java.util.concurrent.Callable;

/**
 * Interface that describes a Methuselah leaf command.
 */
public interface Command extends Callable<Integer> {
    /**
     * Print the help page for this command.
     */
    void printHelpPage();
}
