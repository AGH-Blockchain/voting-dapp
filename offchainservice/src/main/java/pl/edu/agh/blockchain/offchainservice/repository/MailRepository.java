package pl.edu.agh.blockchain.offchainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.blockchain.offchainservice.model.Mail;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    List<Mail> findByMail(String mail);
}
