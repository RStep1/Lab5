package processing;

import data.Vehicle;


import java.util.ArrayDeque;

public class BufferedDataBase {
    private ArrayDeque<Vehicle> data = new ArrayDeque<>();

    public BufferedDataBase() {
    }

    public boolean help(String[] arguments) {
        if (arguments.length > 1) {
            System.out.println("Wrong amount of arguments");
            System.out.println("Print 'help' and press Enter to " +
                    "see a list of commands");

            //Console.printWarning("Wrong amount of arguments")
            return false;
        }
        /*

        try {
            Console.print(argument)
        }
        catch (){
        }
         */
        System.out.println(arguments[0]);////////////////////
        return true;
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
