package rocketseat.com.passin.domain.check_in.exceptions;

public class CheckInAlreadyDoneException extends RuntimeException {
    public CheckInAlreadyDoneException(String message) {
        super(message);
    }
}
