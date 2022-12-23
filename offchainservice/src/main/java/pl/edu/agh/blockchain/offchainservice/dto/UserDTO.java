package pl.edu.agh.blockchain.offchainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {

    private String email;

    private LocalDateTime creationDate;
}
