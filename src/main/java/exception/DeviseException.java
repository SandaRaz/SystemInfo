package exception;

public class DeviseException extends Exception{
    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        if(this.message == "" || this.message == null){
            return " Devise non valide";
        }
        return message;
    }

    public DeviseException() {
    }

    public DeviseException(String message) {
        this.message = message;
    }
}
