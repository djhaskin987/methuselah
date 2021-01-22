package io.github.djhaskin987.methuselah;

import java.util.Properties;
import java.util.HashMap;
import java.util.List;

import io.github.djhaskin987.gumshoe.Gumshoe;
import io.github.djhaskin987.gumshoe.GumshoeReturn;

import io.github.djhaskin987.methuselah.command.Command;
import io.github.djhaskin987.methuselah.command.CommandFactory;

/**
 * Main entry point class for Methuselah.
 *
 */
public final class Methuselah {
    /**
     * Ensure that Methuselah, as a utility class, cannot be instantiated.
     */
    private Methuselah() {
    }

    /**
     * Main entrypoint of the program.
     *
     * @param args
     *                 The command line arguments.
     */
    public static void main(final String[] args) throws Exception {

        Gumshoe optionsGatherer = Gumshoe.createDefaultInstance();
        GumshoeReturn gatheringResults = optionsGatherer.gatherOptions(
                "methuselah", new HashMap<String, String>(), args);

        Properties options = gatheringResults.getOptionsMap();
        List<String> subcommands = gatheringResults.getUnusedArguments();

        CommandFactory factory = CommandFactory.createDefaultInstance();

        Command command = factory.createCommandInstance(subcommands, options);
        System.exit(command.call());
    }
}
