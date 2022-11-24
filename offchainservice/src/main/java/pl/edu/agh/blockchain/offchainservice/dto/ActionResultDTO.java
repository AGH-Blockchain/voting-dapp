package pl.edu.agh.blockchain.offchainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.text.MessageFormat;

@Data
@Builder
public class ActionResultDTO {

    private boolean complete;

    private String feedback;

    public static ActionResultDTO success(String email) {
        return ActionResultDTO.builder()
                .complete(true)
                .feedback(MessageFormat.format("Authorization message was sent to: {0}", email))
                .build();
    }

    public static ActionResultDTO failure(String message) {
        return ActionResultDTO.builder()
                .complete(false)
                .feedback(message)
                .build();
    }
}
