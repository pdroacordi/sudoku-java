package exception;

public class NumberAlreadyPresent extends RuntimeException {
    public NumberAlreadyPresent(String message) {
        super(message);
    }
}
