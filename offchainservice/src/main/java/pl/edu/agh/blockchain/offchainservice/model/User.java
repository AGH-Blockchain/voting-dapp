package pl.edu.agh.blockchain.offchainservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "creation_date")
    private Date creationDate;

    public String getEmail() {
        return email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
