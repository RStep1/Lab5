package processing;

public enum AddMode {
    INSERT_MODE("Key", "inserted"),
    UPDATE_MODE("Id", "updated");
    private final String valueName;
    private final String resultMessage;

    AddMode(String valueName, String resultMessage) {
        this.valueName = valueName;
        this.resultMessage = resultMessage;
    }

    public String getValueName() {
        return valueName;
    }
    public String getResultMessage() {
        return resultMessage;
    }
}
