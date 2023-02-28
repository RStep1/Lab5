package commands;
import processing.BufferedDataBase;

public class HelpCommand extends AbstractCommand implements Command {
    BufferedDataBase dataBase;

    public HelpCommand(BufferedDataBase dataBase) {
        super("help", "выводит справку по доступным командам");
        this.dataBase = dataBase;
    }

    @Override
    public boolean execute(String argument) {
        return dataBase.help(argument);
    }
}
