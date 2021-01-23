package io.github.djhaskin987.methuselah.command;

import java.util.Queue;
import java.util.Properties;

/**
 * The command that houses the root command for Methuselah.
 */
public final class RootCommand implements Command {

    /**
     * Create a default instance of the root command.
     *
     * @return a new RootCommand instance.
     */
    public static Command createDefaultInstance() {
        return new RootCommand(CommandDeps.createDefaultInstance());
    }

    /**
     * Dependency injection dependencies.
     */
    private CommandDeps dependencies;

    /**
     * Create a RootCommand.
     *
     * @param deps
     *                 a command dependencies instance for dependency injection
     *                 purposes.
     */
    private RootCommand(final CommandDeps deps) {
        this.dependencies = deps;
    }

    /**
     * Print help page for the root command.
     */
    public void printHelpPage() {

    }

    /**
     * Invoke the root command of the program.
     *
     * @param options
     *                      the options given by the user.
     * @param arguments
     *                      the arguments given by the user.
     * @return exit code.
     */
    public int invoke(final Properties options, final Queue<String> arguments)
            throws CommandException {
        if (arguments.peek() == null || arguments.peek() == "help") {
            printHelpPage();
            return 1;
        }
        String argument = arguments.poll();
        if (argument == "capture") {
            Command captureCommand = CaptureCommand
                    .createInstance(dependencies);
            return captureCommand.invoke(options, arguments);
        } else {
            throw new CommandException("Unknown command: " + argument);
        }
    }
}
