package br.com.unisinos.example.exception;

public class DataErrorException extends RuntimeException{

    public DataErrorException(String message){
        super(message);
    }
}
