package se.lexicon.semester_app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Malformed JSON request");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiValidationError> list = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            list.add(new ApiValidationError(error.getObjectName(), error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
        }
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Malformed JSON request");
        apiError.setDebugMessage(ex.getMessage());
        apiError.setSubErrors(list);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> recordDuplicateException(RecordNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ArgumentException.class)
    protected ResponseEntity<Object> argumentException(ArgumentException exception) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> globalException(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
