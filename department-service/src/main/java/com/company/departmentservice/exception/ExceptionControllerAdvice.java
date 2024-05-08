package com.company.departmentservice.exception;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//providing error details about date and time of exception along with message and http status code.
// we can do that creating custom errors.
@RestControllerAdvice
@AllArgsConstructor
public class ExceptionControllerAdvice {

    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<ErrorInfo> departmentExceptionHandler(DepartmentException departmentException) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMesssage(environment.getProperty(departmentException.getMessage()));
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
