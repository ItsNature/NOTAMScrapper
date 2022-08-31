package hr.lyra.exceptions;

public class InvalidDateTimeException extends Exception {

    public InvalidDateTimeException(String message) {
        super("Invalid date and time input '" + message + "'");
    }
}
