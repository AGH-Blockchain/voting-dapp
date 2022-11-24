package pl.edu.agh.blockchain.offchainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PendingVerificationDTO {

    private String email;

    private String token;

    private Date tokenSentDate;
}
