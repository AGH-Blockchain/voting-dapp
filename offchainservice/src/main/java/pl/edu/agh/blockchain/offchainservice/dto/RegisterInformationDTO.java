package pl.edu.agh.blockchain.offchainservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterInformationDTO {

    private String email;

    private String token;

    private String blockchainAddress;
}
