package du.spring_test.lib.exceptions;

public class RequiredFieldException extends Exception {

    public RequiredFieldException(String message) {
        super(message);
    }
}
