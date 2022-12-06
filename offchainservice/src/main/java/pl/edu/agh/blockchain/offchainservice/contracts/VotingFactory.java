package pl.edu.agh.blockchain.offchainservice.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class VotingFactory extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506115cb806100206000396000f3fe60806040523480156200001157600080fd5b5060043610620000465760003560e01c8063298f37ab146200004b5780633e04b578146200007f578063ebec4a1f1462000098575b600080fd5b620000626200005c366004620002c4565b620000b1565b6040516001600160a01b0390911681526020015b60405180910390f35b62000089620000dc565b604051620000769190620002de565b620000af620000a9366004620003ee565b62000140565b005b60008181548110620000c257600080fd5b6000918252602090912001546001600160a01b0316905081565b606060008054806020026020016040519081016040528092919081815260200182805480156200013657602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831162000117575b5050505050905090565b604080516000815260208101808352815190209162000162918591016200050c565b6040516020818303038152906040528051906020012003620001c45760405162461bcd60e51b8152602060048201526016602482015275151bdc1a58c81b5d5cdd081899481c1c9bdd9a59195960521b60448201526064015b60405180910390fd5b6001815111620002255760405162461bcd60e51b815260206004820152602560248201527f4174206c656173742074776f206f7074696f6e73206d7573742062652070726f6044820152641d9a59195960da1b6064820152608401620001bb565b60003383836040516200023890620002b6565b620002469392919062000558565b604051809103906000f08015801562000263573d6000803e3d6000fd5b50600080546001810182559080527f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e5630180546001600160a01b0319166001600160a01b0392909216919091179055505050565b610fb480620005e283390190565b600060208284031215620002d757600080fd5b5035919050565b6020808252825182820181905260009190848201906040850190845b81811015620003215783516001600160a01b031683529284019291840191600101620002fa565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156200036f576200036f6200032d565b604052919050565b600082601f8301126200038957600080fd5b813567ffffffffffffffff811115620003a657620003a66200032d565b620003bb601f8201601f191660200162000343565b818152846020838601011115620003d157600080fd5b816020850160208301376000918101602001919091529392505050565b600080604083850312156200040257600080fd5b823567ffffffffffffffff808211156200041b57600080fd5b620004298683870162000377565b93506020915081850135818111156200044157600080fd5b8501601f810187136200045357600080fd5b8035828111156200046857620004686200032d565b8060051b6200047985820162000343565b918252828101850191858101908a8411156200049457600080fd5b86850192505b83831015620004d557823586811115620004b45760008081fd5b620004c48c898389010162000377565b83525091860191908601906200049a565b809750505050505050509250929050565b60005b8381101562000503578181015183820152602001620004e9565b50506000910152565b6000825162000520818460208701620004e6565b9190910192915050565b6000815180845262000544816020860160208601620004e6565b601f01601f19169290920160200192915050565b6001600160a01b0384168152606060208083018290526000916200057f908401866200052a565b8381036040850152845180825282820190600581901b8301840184880160005b83811015620005d157601f19868403018552620005be8383516200052a565b948701949250908601906001016200059f565b50909a995050505050505050505056fe60806040523480156200001157600080fd5b5060405162000fb438038062000fb483398101604081905262000034916200022a565b600080546001600160a01b0319166001600160a01b03851617905560016200005d8382620003d4565b508051620000739060029060208401906200007d565b50505050620004a0565b828054828255906000526020600020908101928215620000c8579160200282015b82811115620000c85782518290620000b79082620003d4565b50916020019190600101906200009e565b50620000d6929150620000da565b5090565b80821115620000d6576000620000f18282620000fb565b50600101620000da565b508054620001099062000345565b6000825580601f106200011a575050565b601f0160209004906000526020600020908101906200013a91906200013d565b50565b5b80821115620000d657600081556001016200013e565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f191681016001600160401b038111828210171562000195576200019562000154565b604052919050565b600082601f830112620001af57600080fd5b81516001600160401b03811115620001cb57620001cb62000154565b6020620001e1601f8301601f191682016200016a565b8281528582848701011115620001f657600080fd5b60005b8381101562000216578581018301518282018401528201620001f9565b506000928101909101919091529392505050565b6000806000606084860312156200024057600080fd5b83516001600160a01b03811681146200025857600080fd5b602085810151919450906001600160401b03808211156200027857600080fd5b62000286888389016200019d565b945060408701519150808211156200029d57600080fd5b818701915087601f830112620002b257600080fd5b815181811115620002c757620002c762000154565b8060051b620002d88582016200016a565b918252838101850191858101908b841115620002f357600080fd5b86860192505b838310156200033457825185811115620003135760008081fd5b620003238d89838a01016200019d565b8352509186019190860190620002f9565b809750505050505050509250925092565b600181811c908216806200035a57607f821691505b6020821081036200037b57634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115620003cf57600081815260208120601f850160051c81016020861015620003aa5750805b601f850160051c820191505b81811015620003cb57828155600101620003b6565b5050505b505050565b81516001600160401b03811115620003f057620003f062000154565b620004088162000401845462000345565b8462000381565b602080601f831160018114620004405760008415620004275750858301515b600019600386901b1c1916600185901b178555620003cb565b600085815260208120601f198616915b82811015620004715788860151825594840194600190910190840162000450565b5085821015620004905787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b610b0480620004b06000396000f3fe608060405234801561001057600080fd5b50600436106100ea5760003560e01c8063a3ec138d1161008c578063c0f2a77711610066578063c0f2a777146101fc578063c631b29214610204578063cc2ee1961461020e578063fc36e15b1461022357600080fd5b8063a3ec138d146101c9578063a4d4334a146101ec578063bf63a577146101f457600080fd5b8063409e2205116100c8578063409e22051461017057806357c9d73814610190578063597e1fb5146101a357806398c07938146101c057600080fd5b806302d05d3f146100ef57806319ad7cd21461011f5780634051ddac14610158575b600080fd5b600054610102906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b61014a61012d366004610780565b805160208183018101805160038252928201919093012091525481565b604051908152602001610116565b610160610236565b6040516101169493929190610881565b61018361017e3660046108bd565b6102f7565b60405161011691906108d6565b61014a61019e366004610780565b6103a3565b6006546101b09060ff1681565b6040519015158152602001610116565b61014a60055481565b6101b06101d73660046108f0565b60046020526000908152604090205460ff1681565b60025461014a565b6101836103cb565b60055461014a565b61020c6103d8565b005b6102166104a5565b6040516101169190610919565b61020c610231366004610780565b61057e565b6000805460055460065460018054606094869485946001600160a01b039092169392909160ff169083906102699061097b565b80601f01602080910402602001604051908101604052809291908181526020018280546102959061097b565b80156102e25780601f106102b7576101008083540402835291602001916102e2565b820191906000526020600020905b8154815290600101906020018083116102c557829003601f168201915b50505050509250935093509350935090919293565b6002818154811061030757600080fd5b9060005260206000200160009150905080546103229061097b565b80601f016020809104026020016040519081016040528092919081815260200182805461034e9061097b565b801561039b5780601f106103705761010080835404028352916020019161039b565b820191906000526020600020905b81548152906001019060200180831161037e57829003601f168201915b505050505081565b60006003826040516103b591906109b5565b9081526020016040518091039020549050919050565b600180546103229061097b565b6000546001600160a01b031633146104435760405162461bcd60e51b815260206004820152602360248201527f4f6e6c792063726561746f722063616e2063616c6c20746869732066756e637460448201526234b7b760e91b60648201526084015b60405180910390fd5b60065460ff16156104965760405162461bcd60e51b815260206004820152601860248201527f566f74696e6720697320616c726561647920636c6f7365640000000000000000604482015260640161043a565b6006805460ff19166001179055565b60606002805480602002602001604051908101604052809291908181526020016000905b828210156105755783829060005260206000200180546104e89061097b565b80601f01602080910402602001604051908101604052809291908181526020018280546105149061097b565b80156105615780601f1061053657610100808354040283529160200191610561565b820191906000526020600020905b81548152906001019060200180831161054457829003601f168201915b5050505050815260200190600101906104c9565b50505050905090565b3360009081526004602052604090205460ff16156105d75760405162461bcd60e51b8152602060048201526016602482015275165bdd481a185d9948185b1c9958591e481d9bdd195960521b604482015260640161043a565b60065460ff161561061d5760405162461bcd60e51b815260206004820152601060248201526f159bdd1a5b99c81a5cc818db1bdcd95960821b604482015260640161043a565b6000805b6002548110156106b5578260405160200161063c91906109b5565b6040516020818303038152906040528051906020012060028281548110610665576106656109d1565b9060005260206000200160405160200161067f91906109e7565b60405160208183030381529060405280519060200120036106a357600191506106b5565b806106ad81610a9c565b915050610621565b50806106fb5760405162461bcd60e51b815260206004820152601560248201527413dc1d1a5bdb88191bd95cc81b9bdd08195e1a5cdd605a1b604482015260640161043a565b3360009081526004602052604090819020805460ff19166001908117909155905160039061072a9085906109b5565b908152602001604051809103902060008282546107479190610ab5565b925050819055506001600560008282546107619190610ab5565b90915550505050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561079257600080fd5b813567ffffffffffffffff808211156107aa57600080fd5b818401915084601f8301126107be57600080fd5b8135818111156107d0576107d061076a565b604051601f8201601f19908116603f011681019083821181831017156107f8576107f861076a565b8160405282815287602084870101111561081157600080fd5b826020860160208301376000928101602001929092525095945050505050565b60005b8381101561084c578181015183820152602001610834565b50506000910152565b6000815180845261086d816020860160208601610831565b601f01601f19169290920160200192915050565b6001600160a01b03851681526080602082018190526000906108a590830186610855565b60408301949094525090151560609091015292915050565b6000602082840312156108cf57600080fd5b5035919050565b6020815260006108e96020830184610855565b9392505050565b60006020828403121561090257600080fd5b81356001600160a01b03811681146108e957600080fd5b6000602080830181845280855180835260408601915060408160051b870101925083870160005b8281101561096e57603f1988860301845261095c858351610855565b94509285019290850190600101610940565b5092979650505050505050565b600181811c9082168061098f57607f821691505b6020821081036109af57634e487b7160e01b600052602260045260246000fd5b50919050565b600082516109c7818460208701610831565b9190910192915050565b634e487b7160e01b600052603260045260246000fd5b600080835481600182811c915080831680610a0357607f831692505b60208084108203610a2257634e487b7160e01b86526022600452602486fd5b818015610a365760018114610a4b57610a78565b60ff1986168952841515850289019650610a78565b60008a81526020902060005b86811015610a705781548b820152908501908301610a57565b505084890196505b509498975050505050505050565b634e487b7160e01b600052601160045260246000fd5b600060018201610aae57610aae610a86565b5060010190565b80820180821115610ac857610ac8610a86565b9291505056fea26469706673582212207d5dbee76903a4874b9ae26a67bf0c79de5e0f23804346b8ec19a6e8e2988efe64736f6c63430008110033a264697066735822122030467cbe21f9734af5d1c83178052ac035f6400eba48c6978a4927966bcafb6c64736f6c63430008110033";

    public static final String FUNC_CREATEVOTING = "createVoting";

    public static final String FUNC_DEPLOYEDVOTINGS = "deployedVotings";

    public static final String FUNC_GETDEPLOYEDVOTINGS = "getDeployedVotings";

    @Deprecated
    protected VotingFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VotingFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VotingFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VotingFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createVoting(String topic, List<String> options) {
        final Function function = new Function(
                FUNC_CREATEVOTING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(topic), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(options, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> deployedVotings(BigInteger param0) {
        final Function function = new Function(FUNC_DEPLOYEDVOTINGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getDeployedVotings() {
        final Function function = new Function(FUNC_GETDEPLOYEDVOTINGS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static VotingFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VotingFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VotingFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VotingFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VotingFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VotingFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VotingFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VotingFactory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VotingFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VotingFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<VotingFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VotingFactory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<VotingFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(VotingFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
