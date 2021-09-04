package mercateo.exceptions;

public class MaxAllowedItemCountExceedException extends RuntimeException {
    public MaxAllowedItemCountExceedException(String message) {
        super(message);
    }
}
