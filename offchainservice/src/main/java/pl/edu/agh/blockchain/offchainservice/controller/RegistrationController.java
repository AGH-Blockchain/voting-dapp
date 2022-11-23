package pl.edu.agh.blockchain.offchainservice.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {


    @PostMapping("register-user")
    public Boolean ifMailExists(@RequestParam String mail) {
        return Boolean.TRUE;
    }
}
