package me.nightwarrior.epayconsumes.exception;

import me.nightwarrior.epayconsumes.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> genericException(Exception e) {
        Response response = new Response();
        response.setStatus(Response.EpayResponseStatus.GENERAL_ERROR);
        response.setErrorCode(Response.EpayResponseStatus.GENERAL_ERROR.getStatus());
        response.setErrorDes(Response.EpayResponseStatus.GENERAL_ERROR.name());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
