package io.github.djhaskin987.methuselah.command;

import java.util.Properties;
import java.util.List;
import java.util.Arrays;

/**
 * This class houses the logic around the Capture command.
 */
public class CaptureCommand implements Command {
    /**
     * Houses the glob expresssions specified by the user for capture.
     */
    private List<String> captureExpressions;

    /**
     * Dependency injection dependencies.
     */
    private CommandDeps dependencies;

    /**
     * Create a CaptureCommand.
     *
     * @param commandDeps
     *                        The command dependency injection dependencies.
     * @param options
     *                        Program user options gathered from various
     *                        sources.
     */
    protected CaptureCommand(final CommandDeps commandDeps,
            final Properties options) {
        this.dependencies = commandDeps;
        String rawInclusions = options.getProperty("capture.inclusions");
        if (rawInclusions == null) {
            rawInclusions = "";
        }
        captureExpressions = Arrays.asList(rawInclusions.split(","));
    }

    /**
     * Print the help page.
     */
    public void printHelpPage() {
        String[] lines = new String[] {
                "This subcommand is used to capture individual files and store",
                "them in Methuselah for later compilation into a larger story.",
                "", "Options:",
                "  * `capture.inclusions`: Java glob expressions that describe",
                "    what needs to be captured in the present working",
                "    directory." };
        for (String line : lines) {
            dependencies.out().println(line);
        }
    }

    /**
     * Main function that performs the command logic.
     *
     * @return Exit code for the command.
     */
    @Override
    public Integer call() {
        dependencies.out().println("Hello, World!");
        return 0;
    }
}
