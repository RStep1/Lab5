package utility;


import data.Vehicle;
import mods.FileType;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * Operates on all project files.
 */
public class FileHandler {
    private static final String ENV_VARIABLE = "SAVE_PATH";
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

    public FileHandler() {}

    /**
     * Checks if the environment variable is valid.
     */
    public static boolean checkEnvVariable() {
        if (System.getenv().get(ENV_VARIABLE) == null) {
            FileHandler.writeToFile(String.format("System variable with file to load and save is not set\n" +
                    "Please set the '%s' environment variable", ENV_VARIABLE), FileType.USER_ERRORS);
            return false;
        }
        try {
            FileReader fileReader = new FileReader(System.getenv().get(ENV_VARIABLE));
            fileReader.close();
        } catch (IOException e) {
            FileHandler.writeToFile(String.format(
                    "Invalid path to environment variable '%s': %s", ENV_VARIABLE, System.getenv().get(ENV_VARIABLE)),
                    FileType.USER_ERRORS);
            return false;
        }
        return true;
    }

    /**
     * Selects path to the file by file type.
     * @param fileType Takes an enumeration of file types.
     * @return returns path to the file.
     */
    private static String filePathSelection(FileType fileType) {
        String filePath = "";
        switch (fileType) {
            case OUTPUT -> filePath = OUTPUT_FILE_ABSOLUTE_PATH;
            case USER_ERRORS -> filePath = USER_ERRORS_FILE_ABSOLUTE_PATH;
            case REFERENCE -> filePath = REFERENCE_FILE_ABSOLUTE_PATH;
            case SYSTEM_ERRORS -> filePath = SYSTEM_ERRORS_FILE_ABSOLUTE_PATH;
            case JSON -> filePath = System.getenv().get(ENV_VARIABLE);
//            default ->
        }
        return filePath;
    }

    public static void writeReferenceFile(String info) {
        clearFile(FileType.REFERENCE);
        writeToFile(info, FileType.REFERENCE);
    }

    /**
     * Writes some information to the file.
     * @param information Takes some text to write.
     * @param fileType Determines which file to write to.
     */
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

    /**
     * Reads script file line by line.
     * @param script Takes user script file.
     * @return All lines from the script file.
     */
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

    /**
     * Reads whole file.
     * @return Contents of the file in string format.
     */
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

    /**
     * Writes the name of the executable program command to the desired file.
     * @param commandName
     * @param fileType
     */
    public static void writeCurrentCommand(String commandName, FileType fileType) {
        String message = String.format("Command '%s':", commandName);
        writeToFile(message, fileType);
    }

    /**
     * Reads Json file and converts it to Hashtable.
     * @return Returns null, if file was empty, otherwise returns Hashtable with data.
     */
    public static Hashtable<Long, Vehicle> loadDataBase() {
        String json = readFile(FileType.JSON);
        JsonReader jsonReader = new JsonReader(json);
        Hashtable<Long, Vehicle> vehicleHashtable = jsonReader.readDataBase();
        if (vehicleHashtable == null) {
            return new Hashtable<>();
        }
        return vehicleHashtable;
    }

    /**
     * Converts Hashtable data to Json format and writes it to Json file.
     * @param dataBase User modified database.
     */
    public static void saveDataBase(Hashtable<Long, Vehicle> dataBase) {
        JsonWriter jsonWriter = new JsonWriter(dataBase);
        String json = jsonWriter.writeDataBase();
        clearFile(FileType.JSON);
        writeToFile(json, FileType.JSON);
    }

    /**
     * Recursively iterates over the entire contents of the given directory.
     * @param dir Directory with script files.
     * @param name Name of the script file.
     * @return Returns null, if file is not found, otherwise returns script file.
     */
    public static File findFile(File dir, String name) {
        File result = null; // no need to store result as String, you're returning File anyway
        File[] dirList  = dir.listFiles();
        if (dirList == null)
            return null;
        for (File file : dirList) {
            if (file.isDirectory()) {
                result = findFile(file, name);
                if (result != null) break; // recursive call found the file; terminate the loop
            } else if (file.getName().matches(name)) {
                return file; // found the file; return it
            }
        }
        return result; // will return null if we didn't find anything
    }
}
