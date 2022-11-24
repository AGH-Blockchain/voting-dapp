package pl.edu.agh.blockchain.offchainservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.blockchain.offchainservice.dto.ActionResultDTO;
import pl.edu.agh.blockchain.offchainservice.dto.RegisterInformationDTO;
import pl.edu.agh.blockchain.offchainservice.service.RegistrationService;

@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PutMapping("verify-user")
    public ActionResultDTO verifyUser(@RequestBody String email) {
        return registrationService.verifyUser(email);
    }

    @PutMapping("register-user")
    public ActionResultDTO registerUser(@RequestBody RegisterInformationDTO registerInformationDTO) {
        return registrationService.registerUser(registerInformationDTO);
    }

}
