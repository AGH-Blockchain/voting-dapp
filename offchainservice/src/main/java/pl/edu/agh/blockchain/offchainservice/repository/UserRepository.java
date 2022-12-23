package pl.edu.agh.blockchain.offchainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.blockchain.offchainservice.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByEmail(String mail);

    boolean existsByEmail(String mail);
}
