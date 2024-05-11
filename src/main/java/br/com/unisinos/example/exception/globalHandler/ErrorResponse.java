package br.com.unisinos.example.exception.globalHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private Collection<String> details;
}
