package exception;

public class CharLimiteException extends Exception{

    @Override
    public String getMessage() {
        return "Nombre de caractere maximum atteinte";
    }

}
