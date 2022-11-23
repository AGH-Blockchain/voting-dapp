package pl.edu.agh.blockchain.offchainservice.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.repository.MailRepository;

@Service
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;

    MailServiceImpl(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Override
    public Boolean ifMailExists(String mail) {
        return !mailRepository.findByMail(mail).isEmpty();
    }
}
