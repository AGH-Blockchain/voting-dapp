package pl.edu.agh.blockchain.offchainservice.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.dto.ActionResultDTO;

@Service
public class DeploymentService {

    public ActionResultDTO deployContract() {
        return ActionResultDTO.builder().build();
    }
}
