package pl.edu.agh.blockchain.offchainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTO {

    private String email;

    private Date creationDate;
}
