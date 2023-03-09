package exceptions;


public class WrongAmountOfArgumentsException extends Exception {
    public WrongAmountOfArgumentsException(String message, int numberOfUserArguments, int expectedNumberOfArguments) {
        super(String.format("%s %s arguments, expected: %s",
                message, numberOfUserArguments, expectedNumberOfArguments));
    }
}
