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
        dependencies = deps;
    }

    /**
     * Print help page for the root command.
     */
    public void printHelpPage() {
        String[] message = new String[] {"Welcome to Methuselah:",
                "The history manager that remembers eeeeverything.", "",
                "Subcommands:", "", "  - `init`", "  - `capture`" };
        for (String line : message) {
            dependencies.out().println(line);
        }
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
        String argument = arguments.poll();
        if (argument == null || argument.equals("help")) {
            printHelpPage();
            return 1;
            // } else if (argument.equals("init")) {
            // Command captureCommand =
            // InitCommand.createInstance(dependencies);
            // return captureCommand.invoke(options, arguments);
        } else if (argument.equals("capture")) {
            Command captureCommand = CaptureCommand
                    .createInstance(dependencies);
            return captureCommand.invoke(options, arguments);
        } else {
            throw new CommandException("Unknown command: " + argument);
        }
    }
}
