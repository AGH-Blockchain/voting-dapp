package pl.edu.agh.blockchain.offchainservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidVerificationToken extends RuntimeException {
    public InvalidVerificationToken(String message) {
        super(message);
    }
}
