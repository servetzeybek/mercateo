package mercateo.exceptions;

public class MaxAllowedItemCostExceedException extends RuntimeException {
    public MaxAllowedItemCostExceedException(String message) {
        super(message);
    }
}
