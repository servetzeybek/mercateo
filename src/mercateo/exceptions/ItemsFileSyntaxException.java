package mercateo.exceptions;

public class ItemsFileSyntaxException extends RuntimeException {

    public ItemsFileSyntaxException(String message) {
        super(message);
    }

    public ItemsFileSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
