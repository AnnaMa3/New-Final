package exceptions;

public class DriverNotConfiguredException extends RuntimeException{
    public DriverNotConfiguredException(String message){
        super(message);
    }

    public DriverNotConfiguredException(String message, Throwable cause){
        super(message, cause);
    }
}
