package br.com.unisinos.example.exception;

public class DataSaveErrorException extends RuntimeException{

    public DataSaveErrorException(String message){
        super(message);
    }
}
