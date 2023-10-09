package exceptions;

public class ScreenshotNotAttachedException extends RuntimeException{
    public ScreenshotNotAttachedException(String message){
        super(message);
    }

    public ScreenshotNotAttachedException(String message, Throwable cause){
        super(message, cause);
    }
}
