package commands;
import processing.BufferedDataBase;

public class ExitCommand extends AbstractCommand implements Command {
    BufferedDataBase dataBase;

    public ExitCommand(BufferedDataBase dataBase) {
        super("exit", "завершает программу без сохранения в файл");
        this.dataBase = dataBase;
    }

    @Override
    public boolean execute(String[] arguments) {
        return dataBase.exit(arguments);
    }
}
