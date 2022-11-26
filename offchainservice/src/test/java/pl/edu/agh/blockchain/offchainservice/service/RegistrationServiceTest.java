package pl.edu.agh.blockchain.offchainservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.agh.blockchain.offchainservice.dto.RegisterInformationDTO;
import pl.edu.agh.blockchain.offchainservice.exceptions.InvalidEmailDomainException;
import pl.edu.agh.blockchain.offchainservice.exceptions.InvalidVerificationToken;
import pl.edu.agh.blockchain.offchainservice.exceptions.UserAlreadyExistsException;
import pl.edu.agh.blockchain.offchainservice.exceptions.VerificationTimeException;
import pl.edu.agh.blockchain.offchainservice.model.PendingVerification;
import pl.edu.agh.blockchain.offchainservice.model.User;
import pl.edu.agh.blockchain.offchainservice.repository.EmailRepository;
import pl.edu.agh.blockchain.offchainservice.repository.PendingRepository;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegistrationServiceTest {

    @InjectMocks
    RegistrationService registrationService;

    @Mock
    EMailService eMailService;

    @Mock
    DeploymentService deploymentService;

    @Mock
    EmailRepository emailRepository;

    @Mock
    PendingRepository pendingRepository;

    @Test
    void verifyUser_NotAghEmail_ThrowException() {
        Exception exception = assertThrows(InvalidEmailDomainException.class, () -> registrationService.verifyUser("test@gmail.com"));

        String expectedMessage = "test@gmail.com is not AGH email";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void verifyUser_EmailExists_ThrowException() {
        when(emailRepository.existsByEmail("test@agh.edu.pl")).thenReturn(true);
        when(emailRepository.findByEmail("test@agh.edu.pl")).thenReturn(Collections.singletonList(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> registrationService.verifyUser("test@agh.edu.pl"));
    }

    @Test
    void registerUser_TimeExceeded_ThrowException() {
        when(pendingRepository.findByEmail("test@agh.edu.pl")).thenReturn(Collections.emptyList());
        RegisterInformationDTO registerInformationDTO = RegisterInformationDTO.builder()
                .email("test@agh.edu.pl")
                .token("test")
                .blockchainAddress("test")
                .build();

        assertThrows(VerificationTimeException.class, () -> registrationService.registerUser(registerInformationDTO));
    }

    @Test
    void registerUser_InvalidToken_ThrowException() {
        PendingVerification pendingVerification = new PendingVerification();
        pendingVerification.setEmail("test@agh.edu.pl");
        pendingVerification.setToken("test");
        pendingVerification.setTokenSentDate(LocalDateTime.now());

        when(pendingRepository.findByEmail("test@agh.edu.pl")).thenReturn(Collections.singletonList(pendingVerification));
        RegisterInformationDTO registerInformationDTO = RegisterInformationDTO.builder()
                .email("test@agh.edu.pl")
                .token("invalid")
                .blockchainAddress("test")
                .build();

        assertThrows(InvalidVerificationToken.class, () -> registrationService.registerUser(registerInformationDTO));
    }
}