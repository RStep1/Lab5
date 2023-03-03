package exceptions;

public class WrongAmountOfArgumentsException extends Exception {
    public WrongAmountOfArgumentsException(String message,
                                           int numberOfUserArguments,
                                           int expectedNumberOfArguments) {
        super(message + String.valueOf(numberOfUserArguments) +
                " arguments" + ", expected: " +
                String.valueOf(expectedNumberOfArguments));
    }
}
