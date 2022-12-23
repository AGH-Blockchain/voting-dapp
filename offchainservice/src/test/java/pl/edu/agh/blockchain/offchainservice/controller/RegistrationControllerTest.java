package pl.edu.agh.blockchain.offchainservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.agh.blockchain.offchainservice.dto.RegisterInformationDTO;
import pl.edu.agh.blockchain.offchainservice.exceptions.InvalidEmailDomainException;
import pl.edu.agh.blockchain.offchainservice.exceptions.InvalidVerificationToken;
import pl.edu.agh.blockchain.offchainservice.service.RegistrationService;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void verifyUser() throws Exception {
        doThrow(new InvalidEmailDomainException("test@gmail.com is not AGH email")).when(registrationService).verifyUser("test@gmail.com");
        this.mockMvc.perform(put("/verify-user")).andExpect(status().isBadRequest());
    }

    @Test
    void registerUser() throws Exception {
        RegisterInformationDTO registerInformationDTO = RegisterInformationDTO.builder()
                .email("test@student.agh.edu.pl")
                .token("2dq1e")
                .blockchainAddress("test")
                .build();
        doThrow(new InvalidVerificationToken("Token is not correct. Check your mail once again or wait about 20 minutes and generate new token")).when(registrationService).registerUser(registerInformationDTO);
        this.mockMvc.perform(put("/register-user")).andExpect(status().isBadRequest());
    }
}