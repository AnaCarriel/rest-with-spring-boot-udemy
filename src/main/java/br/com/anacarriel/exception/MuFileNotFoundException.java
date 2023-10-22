package br.com.anacarriel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus (HttpStatus.NOT_FOUND)
public class MuFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MuFileNotFoundException(String exception){
        super(exception);
    }

    public MuFileNotFoundException(String exception, Throwable cause){
        super(exception, cause);
    }
}
