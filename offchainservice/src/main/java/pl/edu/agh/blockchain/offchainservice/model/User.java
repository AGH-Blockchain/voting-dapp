package pl.edu.agh.blockchain.offchainservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
