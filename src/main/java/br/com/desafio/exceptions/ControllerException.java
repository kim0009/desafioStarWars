package br.com.desafio.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.http.ResponseEntity.status;

import br.com.desafio.domain.dto.internal.ErrorDto;
import br.com.desafio.utils.ApiMessage;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler({ ApiException.class, DeleteException.class, SaveException.class , NoDataException.class, SwApiException.class, ConstraintViolationException.class})
    public final ResponseEntity<ErrorDto> handleException(Exception ex, WebRequest request) { 

        if(ex instanceof DeleteException) 
            return status(HttpStatus.BAD_GATEWAY).body(new ErrorDto(ex.getMessage(), true, HttpStatus.BAD_GATEWAY.value())); 
        else if(ex instanceof SaveException) 
            return status(HttpStatus.BAD_GATEWAY).body(new ErrorDto(ex.getMessage(), true, HttpStatus.BAD_GATEWAY.value())); 
        else if(ex instanceof ConstraintViolationException) 
            return status(HttpStatus.BAD_REQUEST).body(new ErrorDto(ApiMessage.SAVE_INVALID_MESSAGE, true, HttpStatus.OK.value())); 
        else if(ex instanceof SwApiException) 
            return status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorDto(ex.getMessage(), true, HttpStatus.SERVICE_UNAVAILABLE.value()));
        else if(ex instanceof NoDataException) 
            return status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorDto(ex.getMessage(), true, HttpStatus.SERVICE_UNAVAILABLE.value()));
        else 
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(ApiMessage.API_INTERNAL_ERROR_MESSAGE, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}