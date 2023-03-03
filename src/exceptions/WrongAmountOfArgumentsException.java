package exceptions;

public class WrongAmountOfArgumentsException extends Exception {
    public WrongAmountOfArgumentsException(String message) {
        super(message);
    }
    public WrongAmountOfArgumentsException() {

    }
}
