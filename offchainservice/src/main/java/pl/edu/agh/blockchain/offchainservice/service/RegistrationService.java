package pl.edu.agh.blockchain.offchainservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.dto.ActionResultDTO;
import pl.edu.agh.blockchain.offchainservice.dto.PendingVerificationDTO;
import pl.edu.agh.blockchain.offchainservice.dto.RegisterInformationDTO;
import pl.edu.agh.blockchain.offchainservice.model.PendingVerification;
import pl.edu.agh.blockchain.offchainservice.model.User;
import pl.edu.agh.blockchain.offchainservice.repository.EmailRepository;
import pl.edu.agh.blockchain.offchainservice.repository.PendingRepository;
import pl.edu.agh.blockchain.offchainservice.utils.CodeGenerator;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final EmailRepository emailRepository;

    private final PendingRepository pendingRepository;

    private final EMailService eMailService;

    private final DeploymentService deploymentService;

    private final ModelMapper modelMapper = new ModelMapper();

    public RegistrationService(EmailRepository emailRepository, EMailService eMailService,
                               PendingRepository pendingRepository, DeploymentService deploymentService) {
        this.emailRepository = emailRepository;
        this.eMailService = eMailService;
        this.pendingRepository = pendingRepository;
        this.deploymentService = deploymentService;
    }

    public ActionResultDTO verifyUser(String email) {
        if (!emailRepository.existsByEmail(email)) {
            try {
                String token = CodeGenerator.generateNewToken();
                eMailService.sendEmail(email, token);
                savePendingVerification(email, token);
                return ActionResultDTO.success(email);
            } catch (IOException e) {
                return ActionResultDTO.failure(e.getMessage());
            }
        } else {
            User user = emailRepository.findByEmail(email).get(0);
            return ActionResultDTO.failure(MessageFormat.format("User with mail: {0} was registered: {1}", user.getEmail(),
                    user.getCreationDate()));
        }
    }

    public ActionResultDTO registerUser(RegisterInformationDTO registerInformationDTO) {
        Optional<PendingVerificationDTO> pendingVerificationDTO = registrationPending(registerInformationDTO.getEmail());
        if (pendingVerificationDTO.isPresent()) {
            PendingVerificationDTO pendingVerification = pendingVerificationDTO.get();
            return correctToken(registerInformationDTO, pendingVerification) ?
                    deploymentService.deployContract()
                    : ActionResultDTO.failure("Token is not correct. Check your mail once again or wait about 20 minutes and generate new token");
        } else {
            return ActionResultDTO.failure("It's been 15 minutes since the email was sent. Please generate the verification email again");
        }
    }

    private void savePendingVerification(String email, String token) {
        PendingVerificationDTO pendingVerificationDTO = PendingVerificationDTO.builder()
                .email(email)
                .token(token)
                .tokenSentDate(new Date())
                .build();
        PendingVerification pendingVerification = modelMapper.map(pendingVerificationDTO,
                PendingVerification.class);
        pendingRepository.save(pendingVerification);
    }

    private Optional<PendingVerificationDTO> registrationPending(String email) {
        List<PendingVerification> pendingVerifications = pendingRepository.findByEmail(email);
        PendingVerificationDTO pendingVerificationDTO = pendingVerifications.isEmpty() ?
                null : modelMapper.map(pendingVerifications.get(0), PendingVerificationDTO.class);
        return Optional.ofNullable(pendingVerificationDTO);
    }

    private boolean correctToken(RegisterInformationDTO registerInformationDTO, PendingVerificationDTO pendingVerificationDTO) {
        return registerInformationDTO.getToken().equals(pendingVerificationDTO.getToken());
    }

}
