package processing;


import data.Vehicle;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Hashtable;

public class FileHandler {
    private static final String OUTPUT_FILE_PATH = "files/output.txt";
    private static final String USER_ERRORS_FILE_PATH = "files/user_errors.txt";
    private static final String SYSTEM_ERRORS_FILE_PATH = "files/system_errors.txt";
    private static final String JSON_FILE_PATH = "files/data_base.json";
    private static final String REFERENCE_FILE_PATH = "files/reference.txt";
    private static final String OUTPUT_FILE_ABSOLUTE_PATH =
            new File(OUTPUT_FILE_PATH).getAbsolutePath();
    private static final String USER_ERRORS_FILE_ABSOLUTE_PATH =
            new File(USER_ERRORS_FILE_PATH).getAbsolutePath();
    private static final String SYSTEM_ERRORS_FILE_ABSOLUTE_PATH =
            new File(SYSTEM_ERRORS_FILE_PATH).getAbsolutePath();
    private static final String JSON_FILE_ABSOLUTE_PATH =
            new File(JSON_FILE_PATH).getAbsolutePath();
    private static final String REFERENCE_FILE_ABSOLUTE_PATH =
            new File(REFERENCE_FILE_PATH).getAbsolutePath();

    //сделать проверку на существование файлов
    //дать потокам числовую характеристику

    public FileHandler() {

    }
    public static void writeReferenceFile(String info) {
        clearReferenceFile();
        writeToFile(info, REFERENCE_FILE_ABSOLUTE_PATH);
    }

    public static void writeJsonFile(String json) {
        writeToFile(json, JSON_FILE_ABSOLUTE_PATH);
    }

    public static void writeOutputInfo(String out) {
        writeToFile(out, OUTPUT_FILE_ABSOLUTE_PATH);
    }

    public static void writeUserErrors(String errors) {
        writeToFile(errors, USER_ERRORS_FILE_ABSOLUTE_PATH);
    }

    private static void writeToFile(String information, String filePath) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(information);
            writer.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFile() {
        return readFile(JSON_FILE_ABSOLUTE_PATH);
    }
    public static String readOutFile() {
        return readFile(OUTPUT_FILE_ABSOLUTE_PATH);
    }

    public static String readUserErrFile() {
        return readFile(USER_ERRORS_FILE_ABSOLUTE_PATH);
    }

    public static String readReferenceFile() {
        return readFile(REFERENCE_FILE_ABSOLUTE_PATH);
    }

    private static String readFile(String absolutePath) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void clearReferenceFile() {
        clearFile(REFERENCE_FILE_ABSOLUTE_PATH);
    }

    public static void clearJsonFile() {
        clearFile(JSON_FILE_ABSOLUTE_PATH);
    }

    public static void clearOutFile() {
        clearFile(OUTPUT_FILE_ABSOLUTE_PATH);
    }

    public static void clearUserErrFile() {
        clearFile(USER_ERRORS_FILE_ABSOLUTE_PATH);
    }

    private static void clearFile(String filePath) {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCurrentCommand(String commandName) {
        writeOutputInfo("Command " + commandName + ":");
    }

    public static Hashtable<Long, Vehicle> loadDataBase() {
        String json = readJsonFile();
        System.out.println(json);////////////
        JsonReader jsonReader = new JsonReader(json);
        Hashtable<Long, Vehicle> vehicleHashtable = jsonReader.readDataBase();
        if (vehicleHashtable == null) {
            return new Hashtable<>();
        }
        return vehicleHashtable;
    }

    public static boolean saveDataBase(Hashtable<Long, Vehicle> dataBase) {
        JsonWriter jsonWriter = new JsonWriter(dataBase);
        String json = jsonWriter.writeDataBase();
        System.out.println(json);/////////
        clearJsonFile();
        writeJsonFile(json);
        return true;
    }
}
