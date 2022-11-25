package pl.edu.agh.blockchain.offchainservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VerificationTimeException extends RuntimeException{

    public VerificationTimeException(String message) {
        super(message);
    }
}
