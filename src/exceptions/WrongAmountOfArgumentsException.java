package exceptions;


public class WrongAmountOfArgumentsException extends Exception {
    public WrongAmountOfArgumentsException(String message,
                                           String command,
                                           int numberOfUserArguments,
                                           int expectedNumberOfArguments) {
        /*
                super("Command " + command + ": " +
                message + String.valueOf(numberOfUserArguments) +
                " arguments, expected: " +
                String.valueOf(expectedNumberOfArguments));
         */
        super(String.format("Command %s: %s %s arguments, expected: %s", command,
                message, String.valueOf(numberOfUserArguments),
                String.valueOf(expectedNumberOfArguments)));
    }
}
