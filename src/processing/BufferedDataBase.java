package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;


import java.util.ArrayDeque;

public class BufferedDataBase {
    private ArrayDeque<Vehicle> data = new ArrayDeque<>();

    public BufferedDataBase() {
    }

    public boolean help(String[] arguments) {
        try {
            if (arguments.length > 1)
                throw new WrongAmountOfArgumentsException("Wrong amount of arguments");
            Console.println(arguments[0]);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            Console.printHelpMessage();
        }
        return false;
    }

    public boolean info(String[] arguments) {

        /*
        try {
            Console.print()
        }
         */


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

    public boolean clear(String argument) {

    }

    public boolean save(String argument) {

    }

    public boolean executeScript(String argument) {

    }
    */

    public boolean exit(String[] arguments) {
        System.exit(0);
        return true;
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
