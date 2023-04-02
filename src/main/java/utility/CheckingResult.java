package utility;

public class CheckingResult {
    private boolean status;
    private String message;
    public CheckingResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public boolean getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
