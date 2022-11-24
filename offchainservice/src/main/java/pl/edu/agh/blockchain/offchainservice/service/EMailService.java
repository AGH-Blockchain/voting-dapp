package pl.edu.agh.blockchain.offchainservice.service;

import java.io.IOException;

public interface EMailService {

    void sendEmail(String email, String token) throws IOException;
}
