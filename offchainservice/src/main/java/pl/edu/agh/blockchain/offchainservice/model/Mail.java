package pl.edu.agh.blockchain.offchainservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "registered_mails")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail_address")
    private String mailAddress;

    @Column(name = "creation_date")
    private Date creationDate;
}
