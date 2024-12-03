package com.pos.system.smartpos.advisor;


import com.pos.system.smartpos.exception.NotFoundException;
import com.pos.system.smartpos.utility.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    //default exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(Exception e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error Cought: "+e.getCause() , e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        StandardResponse response = new StandardResponse(404, "Error: Not Found", e.getMessage());
        ResponseEntity<StandardResponse> standardResponse = new ResponseEntity<StandardResponse>(
                response,
                HttpStatus.NOT_FOUND
        );

        return standardResponse;
    }


}
