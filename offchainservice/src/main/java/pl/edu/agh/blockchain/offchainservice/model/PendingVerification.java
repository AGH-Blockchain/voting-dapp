package pl.edu.agh.blockchain.offchainservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "pending")
public class PendingVerification {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "token_sent_date")
    private LocalDateTime tokenSentDate;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getTokenSentDate() {
        return tokenSentDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String code) {
        this.token = code;
    }

    public void setTokenSentDate(LocalDateTime codeSentDate) {
        this.tokenSentDate = codeSentDate;
    }
}
