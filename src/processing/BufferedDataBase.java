package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.util.Hashtable;

public class BufferedDataBase {
    private Hashtable<Integer, Vehicle> data = new Hashtable<Integer, Vehicle>();

    public BufferedDataBase() {
    }

    private boolean checkNumberOfArguments(String[] arguments,
                                  int expectedNumberOfArguments) {
        try {
            if (arguments.length != expectedNumberOfArguments + 1)
                throw new WrongAmountOfArgumentsException("Wrong amount of arguments: ",
                        arguments.length - 1, expectedNumberOfArguments);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            Console.printHelpMessage();
        }
        return false;
    }

    public boolean help(String[] arguments) {
        if (checkNumberOfArguments(arguments, 0)) {
            Console.println(arguments[0]);
            return true;
        }
        return false;
    }

    public boolean info(String[] arguments) {

        return true;
    }

    /*
    public boolean show(String argument) {

    }

    public boolean insert(String argument) {

    }

    public boolean update(String argument) {

    }

    public boolean removeKey(String argument) {

    }
    */

    public boolean clear(String[] arguments) {
        if (checkNumberOfArguments(arguments, 0)) {
            data.clear();
            return true;
        }
        return false;
    }
    /*
    public boolean save(String argument) {

    }

    public boolean executeScript(String argument) {

    }
    */

    public boolean exit(String[] arguments) {
        if (checkNumberOfArguments(arguments, 0)) {
            System.exit(0);
            return true;
        }
        return false;
    }

    /*
    public boolean removeGreater(String argument) {

    }

    public boolean removeLower(String argument) {

    }

    public boolean removeGreaterKey(String argument) {

    }

    public boolean removeAllByRnginePower(String argument) {

    }

    public boolean countByFuelType(String argument) {

    }

    public boolean filterLessThanFuelType(String argument) {

    }

     */

}
