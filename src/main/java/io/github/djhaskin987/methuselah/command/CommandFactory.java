package io.github.djhaskin987.methuselah.command;

import java.util.List;
import java.util.Properties;

public class CommandFactory {

    /**
     * Class used by CommandFactory to throw exceptions.
     */
    public class Exception extends java.lang.Exception {

        /**
         * Serial ID given to avoid compiler errors/warnings.
         */
        private static final long serialVersionUID = -1171991815127505551L;

        /**
         * Create an exception and give it a message.
         *
         * @param message
         *                    the message to print when this exception is
         *                    thrown.
         */
        public Exception(final String message) {
            super(message);
        }
    }

    /**
     * Command dependencies to pass along.
     */
    private CommandDeps commandDeps;

    /**
     * Creates an instance of CommandFactory.
     *
     * @return a new instance of CommandFactory.
     *
     */
    public static CommandFactory createDefaultInstance() {
        return new CommandFactory(CommandDeps.createDefaultInstance());
    }

    /**
     * Creates an instance of CommandFactory.
     *
     * @param deps
     *                 command dependencies to pass along to the commands.
     */
    protected CommandFactory(final CommandDeps deps) {
        commandDeps = deps;
    }

    /**
     * Create a Command object, capable of running a sub-program.
     *
     * @param subcommands
     *                        the subcommands given to the program.
     * @param options
     *                        the options given to the program.
     * @return a Command object.
     * @throws Exception
     *                       an exception is thrown if there are too many
     *                       subcommands/arguments.
     */
    public Command createCommandInstance(final List<String> subcommands,
            final Properties options) throws Exception {

        String firstCommand = null;
        if (subcommands.size() == 0) {
            firstCommand = "help";
        } else if (subcommands.size() == 1) {
            firstCommand = subcommands.get(0);
        } else {
            throw new Exception("Too many arguments.");
        }

        if (firstCommand == "capture") {
            return new CaptureCommand(commandDeps, options);
        } else {
            throw new Exception("Unknown command `" + firstCommand + "`.");
        }
    }

}
