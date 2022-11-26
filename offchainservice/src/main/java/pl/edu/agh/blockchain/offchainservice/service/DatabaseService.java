package pl.edu.agh.blockchain.offchainservice.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.agh.blockchain.offchainservice.model.PendingVerification;
import pl.edu.agh.blockchain.offchainservice.repository.PendingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DatabaseService {

    private final PendingRepository pendingRepository;

    public DatabaseService(PendingRepository pendingRepository) {
        this.pendingRepository = pendingRepository;
    }

    @Scheduled(cron="*/15 * * * *")
    public void updatePendingTable() {
        List<PendingVerification> pendingVerifications = pendingRepository.findByTokenSentDateBefore(LocalDateTime.now().minusMinutes(15));
        pendingRepository.deleteAll(pendingVerifications);
    }
}
