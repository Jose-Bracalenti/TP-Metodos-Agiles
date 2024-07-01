package Mappers;

import org.eclipse.persistence.exceptions.ValidationException;

public class MyValidationException extends ValidationException {
    
    public MyValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
