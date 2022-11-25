package pl.edu.agh.blockchain.offchainservice.exceptions;

public class SendFailedException extends RuntimeException {

    public SendFailedException(String message) {
        super(message);
    }
}
