package io.github.djhaskin987.methuselah.command;

import java.util.Properties;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * This is a dependency injection class passed around classes in the command
 * package so that we don't have to duplicate the work everywhere.
 */
public class CommandDeps {
    /**
     * Create a default instance of CommandDeps, that uses the "real" things for
     * the dependency injection objects.
     *
     * @return a new instance of CommandDeps which uses the "real" thing (i.e.
     *         <code>java.lang.System</code>).
     */
    public static CommandDeps createDefaultInstance() {
        return new CommandDeps(System.out, System.err, System.getProperties());
    }

    /**
     * Represents system properties.
     */
    private Properties systemProperties;

    /**
     * Represents System.out .
     */
    private PrintStream out;

    /**
     * Represents System.err .
     */
    private PrintStream err;

    /**
     * Create a new instance of CommandDeps.
     *
     * @param outStream
     *                        a PrintStream representing standard output (e.g.
     *                        <code>System.out</code> ).
     * @param errStream
     *                        a PrintStream representing standard output (e.g.
     *                        <code>System.err</code> ).
     * @param systemProps
     *                        a Properties object representing System properties
     *                        (e.g. <code>System.getProperties()</code> ).
     */
    public CommandDeps(final PrintStream outStream, final PrintStream errStream,
            final Properties systemProps) {
        this.out = outStream;
        this.err = errStream;
        this.systemProperties = systemProps;
    }

    /**
     * Provide access to standard output.
     *
     * @return the standard output stream.
     */
    public PrintStream out() {
        return this.out;
    }

    /**
     * Provide access to standard output.
     *
     * @return the standard output stream.
     */
    public PrintStream err() {
        return this.err;
    }

    /**
     * Provide access to system properties.
     *
     * @return system properties.
     */
    public Properties systemProperties() {
        return this.systemProperties;
    }

    /**
     * Provide the PWD.
     *
     * @return the present working directory.
     */
    public Path presentWorkingDirectory() {
        String dir = this.systemProperties.getProperty("user.dir");
        if (dir == null) {
            return null;
        }
        return Path.of(dir);
    }
}
