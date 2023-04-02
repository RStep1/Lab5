/*package processing;


import data.Vehicle;
import mods.FileType;

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

    public FileHandler() {

    }


    private static String selectFilePath(FileType fileType) {
        String filePath = "";
        switch (fileType) {
            case REFERENCE -> filePath = REFERENCE_FILE_ABSOLUTE_PATH;
            case JSON -> filePath = JSON_FILE_ABSOLUTE_PATH;
            case OUTPUT -> filePath = OUTPUT_FILE_ABSOLUTE_PATH;
            case USER_ERRORS -> filePath = USER_ERRORS_FILE_ABSOLUTE_PATH;
            case SYSTEM_ERRORS -> filePath = SYSTEM_ERRORS_FILE_ABSOLUTE_PATH;
        }
        return filePath;
    }

//    public static void writeJsonFile(String json) {
//        writeToFile(json, JSON_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void writeOutputInfo(String out) {
//        writeToFile(out, OUTPUT_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void writeUserErrors(String errors) {
//        writeToFile(errors, USER_ERRORS_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void writeSystemErrors(String errors) {
//        writeToFile(errors, SYSTEM_ERRORS_FILE_ABSOLUTE_PATH);
//    }
    private static void writeToFile(String information, FileType fileType) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(selectFilePath(fileType), true))) {
            writer.write(information);
            writer.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

//    public static String readJsonFile() {
//        return readFile(JSON_FILE_ABSOLUTE_PATH);
//    }
//    public static String readOutFile() {
//        return readFile(OUTPUT_FILE_ABSOLUTE_PATH);
//    }
//
//    public static String readUserErrFile() {
//        return readFile(USER_ERRORS_FILE_ABSOLUTE_PATH);
//    }
//
//    public static String readReferenceFile() {
//        return readFile(REFERENCE_FILE_ABSOLUTE_PATH);
//    }

    private static String readFile(FileType fileType) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(selectFilePath(fileType)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    private static void clearReferenceFile() {
//        clearFile(REFERENCE_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void clearJsonFile() {
//        clearFile(JSON_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void clearOutFile() {
//        clearFile(OUTPUT_FILE_ABSOLUTE_PATH);
//    }
//
//    public static void clearUserErrFile() {
//        clearFile(USER_ERRORS_FILE_ABSOLUTE_PATH);
//    }

    private static void clearFile(FileType fileType) {
        try {
            new FileWriter(selectFilePath(fileType), false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCurrentCommand(String commandName, FileType fileType) {
        writeToFile("Command " + commandName + ":", fileType);
    }

    public static Hashtable<Long, Vehicle> loadDataBase() {
        String json = readFile(FileType.JSON);
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
//        clearJsonFile();
        clearFile(FileType.JSON);
//        writeJsonFile(json);
        writeToFile(json, FileType.JSON);
        return true;
    }
}*/
package processing;


import data.Vehicle;
import mods.FileType;

import java.io.*;
import java.util.ArrayList;
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

    public static void writeSystemErrors(String errors) {
        writeToFile(errors, SYSTEM_ERRORS_FILE_ABSOLUTE_PATH);
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

    public static ArrayList<String> readScriptFile(File script) {
        ArrayList<String> scriptLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(script.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scriptLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scriptLines;
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

    public static void writeCurrentCommand(String commandName, FileType fileType) {
        String message = String.format("Command '%s':", commandName);
        if (fileType == FileType.OUTPUT)
            writeOutputInfo(message);
        if (fileType == FileType.USER_ERRORS)
            writeUserErrors(message);
    }

    public static Hashtable<Long, Vehicle> loadDataBase() {
        String json = readJsonFile();
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
        clearJsonFile();
        writeJsonFile(json);
        return true;
    }

    public static File findFile(File dir, String name) {
        File result = null; // no need to store result as String, you're returning File anyway
        File[] dirlist  = dir.listFiles();

        for(int i = 0; i < dirlist.length; i++) {
            if(dirlist[i].isDirectory()) {
                result = findFile(dirlist[i], name);
                if (result != null) break; // recursive call found the file; terminate the loop
            } else if(dirlist[i].getName().matches(name)) {
                return dirlist[i]; // found the file; return it
            }
        }
        return result; // will return null if we didn't find anything
    }
}
