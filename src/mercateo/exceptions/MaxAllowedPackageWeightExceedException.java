package mercateo.exceptions;

public class MaxAllowedPackageWeightExceedException extends  RuntimeException {
    public MaxAllowedPackageWeightExceedException(String message) {
        super(message);
    }
}
