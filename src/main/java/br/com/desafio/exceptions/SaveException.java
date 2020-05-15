package br.com.desafio.exceptions;

public class SaveException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public SaveException(String message) {
        super(message);
    } 
}