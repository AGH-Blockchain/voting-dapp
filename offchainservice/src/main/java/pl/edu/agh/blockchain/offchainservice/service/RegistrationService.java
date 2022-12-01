package pl.edu.agh.blockchain.offchainservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.dto.PendingVerificationDTO;
import pl.edu.agh.blockchain.offchainservice.dto.RegisterInformationDTO;
import pl.edu.agh.blockchain.offchainservice.exceptions.*;
import pl.edu.agh.blockchain.offchainservice.model.PendingVerification;
import pl.edu.agh.blockchain.offchainservice.model.User;
import pl.edu.agh.blockchain.offchainservice.repository.UserRepository;
import pl.edu.agh.blockchain.offchainservice.repository.PendingRepository;
import pl.edu.agh.blockchain.offchainservice.utils.CodeGenerator;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final PendingRepository pendingRepository;

    private final EMailService eMailService;

    private final VotingFactoryService votingFactoryService;

    private final ModelMapper modelMapper = new ModelMapper();

    public RegistrationService(UserRepository userRepository, EMailService eMailService,
                               PendingRepository pendingRepository, VotingFactoryService votingFactoryService) {
        this.userRepository = userRepository;
        this.eMailService = eMailService;
        this.pendingRepository = pendingRepository;
        this.votingFactoryService = votingFactoryService;
    }

    public void verifyUser(String email) {
        if (!email.endsWith("@agh.edu.pl") && !email.endsWith("@student.agh.edu.pl")) {
            throw new InvalidEmailDomainException(email + " is not AGH email");
        }
        if (!userRepository.existsByEmail(email)) {
            try {
                String token = CodeGenerator.generateNewToken();
                eMailService.sendEmail(email, token);
                savePendingVerification(email, token);
            } catch (IOException e) {
                throw new SendFailedException(e.getMessage());
            }
        } else {
            User user = userRepository.findByEmail(email).get(0);
            throw new UserAlreadyExistsException(MessageFormat.format("User with mail: {0} was registered: {1}", user.getEmail(),
                    user.getCreationDate()));
        }
    }

    public void registerUser(RegisterInformationDTO registerInformationDTO) {
        Optional<PendingVerificationDTO> pendingVerificationDTO = registrationPending(registerInformationDTO.getEmail());
        if (pendingVerificationDTO.isPresent()) {
            PendingVerificationDTO pendingVerification = pendingVerificationDTO.get();
            if (!correctToken(registerInformationDTO, pendingVerification)) {
                throw new InvalidVerificationToken("Token is not correct. Check your mail once again or wait about 20 minutes and generate new token");
            }
            saveUser(registerInformationDTO.getEmail());
            removeFromPending(registerInformationDTO.getEmail());
            votingFactoryService.addAddress(registerInformationDTO.getEmail(), registerInformationDTO.getBlockchainAddress());
        } else {
            throw new VerificationTimeException("You have not verified or your token expired");
        }
    }

    private void savePendingVerification(String email, String token) {
        PendingVerificationDTO pendingVerificationDTO = PendingVerificationDTO.builder()
                .email(email)
                .token(token)
                .tokenSentDate(LocalDateTime.now())
                .build();
        PendingVerification pendingVerification = modelMapper.map(pendingVerificationDTO,
                PendingVerification.class);
        pendingRepository.save(pendingVerification);
    }

    private void saveUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setCreationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    private void removeFromPending(String email) {
        PendingVerification pendingVerification = pendingRepository.findByEmail(email).get(0);
        pendingRepository.delete(pendingVerification);
    }

    private Optional<PendingVerificationDTO> registrationPending(String email) {
        List<PendingVerification> pendingVerifications = pendingRepository.findByEmail(email);
        PendingVerificationDTO pendingVerificationDTO = pendingVerifications.isEmpty() ?
                null : PendingVerificationDTO.builder()
                .email(pendingVerifications.get(0).getEmail())
                .token(pendingVerifications.get(0).getToken())
                .tokenSentDate(pendingVerifications.get(0).getTokenSentDate())
                .build();
        return Optional.ofNullable(pendingVerificationDTO);
    }

    private boolean correctToken(RegisterInformationDTO registerInformationDTO, PendingVerificationDTO pendingVerificationDTO) {
        return registerInformationDTO.getToken().equals(pendingVerificationDTO.getToken());
    }

}
