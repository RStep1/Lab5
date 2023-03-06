package processing;


import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class FileHandler {

    //private static final String DIRECTORY = System.getProperty("user.home");
    private static final String ERR_FILE_NAME = "src/files/errors.txt";
    private static final String OUT_FILE_NAME = "src/files/output.txt";
    private static final String ERR_FILE_ABSOLUTE_PATH =
            new File(ERR_FILE_NAME).getAbsolutePath();
    private static final String OUT_FILE_ABSOLUTE_PATH =
            new File(OUT_FILE_NAME).getAbsolutePath();
    //сделать проверку на существование файлов


    public FileHandler() {

    }

    public static void writeOutputInfo(String out) {
        writeToFile(out, OUT_FILE_ABSOLUTE_PATH);
    }

    public static void writeErrors(String errors) {
        writeToFile(errors, ERR_FILE_ABSOLUTE_PATH);
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

    public static String readOutFile() {
        return readFile(OUT_FILE_ABSOLUTE_PATH);
    }

    public static String readErrFile() {
        return readFile(ERR_FILE_ABSOLUTE_PATH);
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

    public static void clearOutFile() {
        clearFile(OUT_FILE_ABSOLUTE_PATH);
    }

    public static void clearErrFile() {
        clearFile(ERR_FILE_ABSOLUTE_PATH);
    }

    private static void clearFile(String filePath) {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
