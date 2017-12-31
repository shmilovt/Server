package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import java.util.List;

public class AssertionErrorAggregation extends AssertionError {

    private List<AssertionError> errors;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public List<AssertionError> getErrors() {
        return errors;
    }

    public void setErrors(List<AssertionError> errors) {
        this.errors = errors;
    }

    public AssertionErrorAggregation(List<AssertionError> errors){
        super(generateMessage(errors));
        this.errors = errors;
    }

    private static String generateMessage(List<AssertionError> errors){
        String message = ANSI_RED  + "";
        for (AssertionError error: errors) {
            message = message + error.getMessage() + "\n";
        }

        message = message + ANSI_RESET;
        return message;
    }

}
