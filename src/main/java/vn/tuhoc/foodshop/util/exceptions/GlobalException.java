package vn.tuhoc.foodshop.util.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.tuhoc.foodshop.domain.RestResponse;

@RestControllerAdvice
public class GlobalException {
    // @ExceptionHandler(value = {
    // CategoryException.class
    // })
    // public ResponseEntity<RestResponse<Object>> handleCategoryException(Exception
    // exception) {
    // RestResponse<Object> restResponse = new RestResponse<>();
    // restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
    // restResponse.setError(exception.getMessage());
    // restResponse.setMessage("Call API error");

    // System.out.println(restResponse.getError());

    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    // }

    @ExceptionHandler(value = {
            IdInvalidException.class,
            UsernameNotFoundException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<RestResponse<Object>> handleException(Exception exception) {
        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(exception.getMessage());
        restResponse.setMessage("Call API error");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> validationError(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        RestResponse<Object> restResponse = new RestResponse<>();
        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        restResponse.setError(methodArgumentNotValidException.getBody().getDetail());

        List<Map<String, String>> errors = fieldErrors.stream()
                .map(f -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("defaultMessage", f.getDefaultMessage());
                    errorMap.put("field", f.getField());
                    return errorMap;
                })
                .collect(Collectors.toList());
        restResponse.setMessage(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }
}
