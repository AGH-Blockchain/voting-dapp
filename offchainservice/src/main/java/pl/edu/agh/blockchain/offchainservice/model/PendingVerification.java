package pl.edu.agh.blockchain.offchainservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "pending")
public class PendingVerification {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "token_sent_date")
    private Date tokenSentDate;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public Date getTokenSentDate() {
        return tokenSentDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String code) {
        this.token = code;
    }

    public void setTokenSentDate(Date codeSentDate) {
        this.tokenSentDate = codeSentDate;
    }
}
