package au.com.livewirelabs.assignment.CustomizedExceptions;

public class InvalidCodeException extends Exception{
    public InvalidCodeException(String code) {
        super(code);
    }
}
