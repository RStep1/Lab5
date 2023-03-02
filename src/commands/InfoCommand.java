package commands;

import processing.BufferedDataBase;

public class InfoCommand extends AbstractCommand implements Command {
    BufferedDataBase bufferedDataBase;

    public InfoCommand(BufferedDataBase bufferedDataBase) {
        super("info", "выводит информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)");
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments) {
        return bufferedDataBase.info(arguments);
    }
}
