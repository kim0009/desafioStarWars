package br.com.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.desafio.domain.dto.internal.ErrorDto;
import br.com.desafio.utils.ApiMessage;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler({ ApiException.class, DeleteException.class, SaveException.class , NoDataException.class, SwApiException.class})
    public final ResponseEntity<ErrorDto> handleException(Exception ex, WebRequest request) { 

        if(ex instanceof DeleteException) 
            return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage(), true, HttpStatus.BAD_GATEWAY.value())
                                                , HttpStatus.BAD_GATEWAY); 
        else if(ex instanceof SaveException) 
            return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage(), true, HttpStatus.BAD_GATEWAY.value())
                                                , HttpStatus.BAD_GATEWAY); 
        else if(ex instanceof SwApiException) 
            return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage(), true, HttpStatus.SERVICE_UNAVAILABLE.value())
                                                , HttpStatus.SERVICE_UNAVAILABLE);
        else if(ex instanceof NoDataException) 
            return new ResponseEntity<ErrorDto>(new ErrorDto(ex.getMessage(), true, HttpStatus.SERVICE_UNAVAILABLE.value())
                                                , HttpStatus.SERVICE_UNAVAILABLE);
        else 
            return new ResponseEntity<ErrorDto>(new ErrorDto(ApiMessage.API_INTERNAL_ERROR_MESSAGE, false, HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}