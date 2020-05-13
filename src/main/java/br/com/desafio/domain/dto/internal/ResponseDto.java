package br.com.desafio.domain.dto.internal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    public Object data;
    public boolean sucess;
    public String message;

    public ResponseDto(Object data, boolean sucess, String message) {
        this.data = data;
        this.sucess = sucess;
        this.message = message;
    }
    
    public ResponseDto(Object data, boolean sucess) {
        this.data = data;
        this.sucess = sucess;
    }
    
    public ResponseDto(Object data) {
        this.data = data;
        this.sucess = true;
    }
}