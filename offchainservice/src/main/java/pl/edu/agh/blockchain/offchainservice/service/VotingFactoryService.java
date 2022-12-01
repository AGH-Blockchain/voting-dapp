package pl.edu.agh.blockchain.offchainservice.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.contracts.VotingFactory;


@Service
public class VotingFactoryService {
    public void addAddress(String email, String blockchainAddress) {
        if (email.endsWith("@student.agh.edu.pl")) {
            VotingFactory.addStudentAddress(blockchainAddress);
        } else {
            VotingFactory.addEmployeeAddress(blockchainAddress);
        }
    }
}
