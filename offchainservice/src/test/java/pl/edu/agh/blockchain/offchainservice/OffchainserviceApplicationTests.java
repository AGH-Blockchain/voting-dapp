package pl.edu.agh.blockchain.offchainservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.agh.blockchain.offchainservice.controller.RegistrationController;
import pl.edu.agh.blockchain.offchainservice.service.RegistrationService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OffchainserviceApplicationTests {

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private RegistrationService registrationService;

    @Test
    void contextLoads() {
        assertThat(registrationController).isNotNull();
        assertThat(registrationService).isNotNull();
    }

}
