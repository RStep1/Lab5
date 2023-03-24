package mods;

public enum RemoveMode {
    REMOVE_GREATER(">"),
    REMOVE_LOWER("<");
    private final String symbol;
    RemoveMode(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
}
