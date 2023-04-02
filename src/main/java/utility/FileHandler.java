package utility;


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

    public FileHandler() {

    }

    public static String filePathSelection(FileType fileType) {
        String filePath = "";
        switch (fileType) {
            case OUTPUT -> filePath = OUTPUT_FILE_ABSOLUTE_PATH;
            case USER_ERRORS -> filePath = USER_ERRORS_FILE_ABSOLUTE_PATH;
            case REFERENCE -> filePath = REFERENCE_FILE_ABSOLUTE_PATH;
            case SYSTEM_ERRORS -> filePath = SYSTEM_ERRORS_FILE_ABSOLUTE_PATH;
            case JSON -> filePath = JSON_FILE_ABSOLUTE_PATH;
//            default ->
        }
        return filePath;
    }

    public static void writeReferenceFile(String info) {
        clearFile(FileType.REFERENCE);
        writeToFile(info, FileType.REFERENCE);
    }

    public static void writeToFile(String information, FileType fileType) {
        String filePath = filePathSelection(fileType);
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(information);
            writer.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
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

    public static String readFile(FileType fileType) {
        String absolutePath = filePathSelection(fileType);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void clearFile(FileType fileType) {
        String filePath = filePathSelection(fileType);
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCurrentCommand(String commandName, FileType fileType) {
        String message = String.format("Command '%s':", commandName);
        writeToFile(message, fileType);
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
        clearFile(FileType.JSON);
        writeToFile(json, FileType.JSON);
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
