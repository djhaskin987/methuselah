package io.github.djhaskin987.methuselah;

import java.util.Properties;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

import io.github.djhaskin987.gumshoe.Gumshoe;
import io.github.djhaskin987.gumshoe.GumshoeReturn;

import io.github.djhaskin987.methuselah.command.Command;
import io.github.djhaskin987.methuselah.command.RootCommand;

/**
 * Main entry point class for Methuselah.
 *
 */
public final class Methuselah {
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
        Queue<String> subcommands = new ArrayDeque<String>();
        subcommands.addAll(gatheringResults.getUnusedArguments());

        Command rootCommand = RootCommand.createDefaultInstance();

        System.exit(rootCommand.invoke(options, subcommands));
    }
}
