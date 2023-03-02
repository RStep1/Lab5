package commands;

public abstract class AbstractCommand {
    private String name;
    private String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return name + ": " + description;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode(); //////////
    }

    /*
    @Override
    public boolean equals() {
        //...
    }
    */
}
