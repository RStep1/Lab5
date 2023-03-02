package processing;
import commands.Command;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;


public class ScriptParser {
    private Queue<Command> commandStack = new LinkedList<>();
    private File script;

    public ScriptParser(File script) {
        this.script = script;
    }

    public Queue<Command> parseScript() {
        Queue<Command> commandStack = new LinkedList<>();


        return commandStack;
    }
}
