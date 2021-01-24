package io.github.djhaskin987.methuselah.command;

import java.util.Queue;
import java.util.Properties;
import java.util.Arrays;

/**
 * The command that houses the root command for Methuselah.
 */
public final class CaptureCommand implements Command {

    /**
     * Create a default instance of the root command.
     *
     * @param deps
     *                 dependency injection class.
     *
     * @return a new RootCommand instance.
     */
    public static Command createInstance(final CommandDeps deps) {
        return new CaptureCommand(deps);
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
    private CaptureCommand(final CommandDeps deps) {
        this.dependencies = deps;
    }

    /**
     * Print help page for the root command.
     */
    public void printHelpPage() {
        dependencies.out().println("TODO: write help page");

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
        if (arguments.size() > 0) {
            throw new CommandException(
                    "Unknown commands: " + String.join(" ", arguments));
        }

        String[] inclusions = options.getProperty("capture.inclusions")
                .split(",");
        Arrays.asList(inclusions);

        // String argument = arguments.poll();
        return 0;
    }
}
