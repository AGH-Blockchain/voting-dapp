package pl.edu.agh.blockchain.offchainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.blockchain.offchainservice.model.PendingVerification;

import java.util.Date;
import java.util.List;

@Repository
public interface PendingRepository extends JpaRepository<PendingVerification, String> {
    List<PendingVerification> findByTokenSentDateGreaterThan(Date date);

    List<PendingVerification> findByEmail(String email);
}
