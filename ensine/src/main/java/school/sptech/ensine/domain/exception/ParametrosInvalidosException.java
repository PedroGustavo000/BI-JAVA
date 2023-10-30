package school.sptech.ensine.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParametrosInvalidosException extends RuntimeException {
    public ParametrosInvalidosException(String message){super(message);}
}
