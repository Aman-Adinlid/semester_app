package se.lexicon.semester_app.exception;

import lombok.Data;

@Data
public class ApiValidationError {

    private final String object;
    private final String field;
    private final Object rejectedValue;
    private String message;

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
