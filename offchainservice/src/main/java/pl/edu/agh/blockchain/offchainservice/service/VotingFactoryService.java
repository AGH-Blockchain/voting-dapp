package pl.edu.agh.blockchain.offchainservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import pl.edu.agh.blockchain.offchainservice.contracts.VotingFactory;
import pl.edu.agh.blockchain.offchainservice.exceptions.AddBlockchainAddressException;

import java.math.BigInteger;


@Service
public class VotingFactoryService {


    private final String contractAddress;

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    @Autowired
    public VotingFactoryService(@Value("${voting_factory.contract_address}") String contractAddress, @Value("${voting_factory.service_url}") String serviceUrl, @Value("${voting_factory.private_key}") String privateKey) {
        this.contractAddress = contractAddress;

        Web3jService service = new HttpService(serviceUrl);
        this.web3j = Web3j.build(service);
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975));
    }

    public void addAddress(String email, String blockchainAddress) {

        VotingFactory contract = VotingFactory.load(contractAddress, web3j, credentials, gasProvider);
        try {
            if (email.endsWith("@student.agh.edu.pl")) {
                contract.addStudent(blockchainAddress).send();
            } else {
                contract.addEmployee(blockchainAddress).send();
            }
        } catch (Exception exception) {
            throw new AddBlockchainAddressException(exception.getMessage());
        }
    }
}
