package br.com.desafio.domain.dto.internal;

import lombok.Data;

@Data
public class ErrorDto {

    private String message;
    private int status;
    private boolean sucess;
    
    public ErrorDto(String message, boolean sucess, int status) {
        this.message = message;
        this.sucess = sucess;
        this.status = status;
    }
}