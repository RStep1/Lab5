package processing;


import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class FileHandler {
    private static final String OUTPUT_FILE_PATH = "src/files/output.txt";
    private static final String USER_ERRORS_FILE_PATH = "src/files/user_errors.txt";
    private static final String SYSTEM_ERRORS_FILE_PATH = "src/files/system_errors.txt";
    private static final String OUTPUT_FILE_ABSOLUTE_PATH =
            new File(OUTPUT_FILE_PATH).getAbsolutePath();
    private static final String USER_ERRORS_FILE_ABSOLUTE_PATH =
            new File(USER_ERRORS_FILE_PATH).getAbsolutePath();
    private static final String SYSTEM_ERRORS_FILE_ABSOLUTE_PATH =
            new File(SYSTEM_ERRORS_FILE_PATH).getAbsolutePath();
    //сделать проверку на существование файлов
    //дать потокам числовую характеристику

    public FileHandler() {

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

    public static String readOutFile() {
        return readFile(OUTPUT_FILE_ABSOLUTE_PATH);
    }

    public static String readUserErrFile() {
        return readFile(USER_ERRORS_FILE_ABSOLUTE_PATH);
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
}
