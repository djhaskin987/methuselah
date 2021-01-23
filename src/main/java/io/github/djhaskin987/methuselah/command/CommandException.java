package io.github.djhaskin987.methuselah.command;

public class CommandException extends Exception {
    /**
     * Included to avoid compiler/checkstyle errors.
     */
    private static final long serialVersionUID = -1171991815127505551L;

    /**
     * Create an exception and give it a message.
     *
     * @param message
     *                    the message to print when this exception is thrown.
     */
    public CommandException(final String message) {
        super(message);
    }
}
