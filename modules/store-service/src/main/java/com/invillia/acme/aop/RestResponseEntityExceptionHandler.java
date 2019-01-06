package com.invillia.acme.aop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.invillia.acme.exceptions.BusinessException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value 
      = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Store Service - Illegal Argument or State";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(value 
    		= { BusinessException.class })
    protected ResponseEntity<Object> handleNotFound(
    		RuntimeException ex, WebRequest request) {
    	String bodyOfResponse = ex.getMessage();
    	return handleExceptionInternal(ex, bodyOfResponse, 
    			new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
}
