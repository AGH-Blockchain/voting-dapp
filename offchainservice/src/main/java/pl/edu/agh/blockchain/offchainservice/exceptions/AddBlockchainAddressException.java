package pl.edu.agh.blockchain.offchainservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AddBlockchainAddressException extends RuntimeException {
    public AddBlockchainAddressException(String message) {
        super(message);
    }

}
