package au.com.livewirelabs.assignment.CustomizedExceptions;

public class InsufficientUnitsException extends Exception{
    public InsufficientUnitsException(String code) {
        super(code);
    }
}
