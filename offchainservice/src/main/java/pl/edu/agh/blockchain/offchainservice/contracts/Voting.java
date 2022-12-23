package pl.edu.agh.blockchain.offchainservice.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
public class Voting extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162001a0e38038062001a0e8339810160408190526200003491620002a0565b600080546001600160a01b038087166001600160a01b031992831617909255600180549288169282168317905560028054909116909117905560036200007b848262000462565b50815162000091906004906020850190620000c6565b506008805482919061ff001916610100836002811115620000b657620000b66200052e565b0217905550505050505062000544565b82805482825590600052602060002090810192821562000111579160200282015b8281111562000111578251829062000100908262000462565b5091602001919060010190620000e7565b506200011f92915062000123565b5090565b808211156200011f5760006200013a828262000144565b5060010162000123565b5080546200015290620003d3565b6000825580601f1062000163575050565b601f01602090049060005260206000209081019062000183919062000186565b50565b5b808211156200011f576000815560010162000187565b80516001600160a01b0381168114620001b557600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f191681016001600160401b0381118282101715620001fb57620001fb620001ba565b604052919050565b600082601f8301126200021557600080fd5b81516001600160401b03811115620002315762000231620001ba565b602062000247601f8301601f19168201620001d0565b82815285828487010111156200025c57600080fd5b60005b838110156200027c5785810183015182820184015282016200025f565b506000928101909101919091529392505050565b805160038110620001b557600080fd5b600080600080600060a08688031215620002b957600080fd5b620002c4866200019d565b94506020620002d58188016200019d565b60408801519095506001600160401b0380821115620002f357600080fd5b620003018a838b0162000203565b955060608901519150808211156200031857600080fd5b818901915089601f8301126200032d57600080fd5b815181811115620003425762000342620001ba565b8060051b62000353858201620001d0565b918252838101850191858101908d8411156200036e57600080fd5b86860192505b83831015620003af578251858111156200038e5760008081fd5b6200039e8f89838a010162000203565b835250918601919086019062000374565b80985050505050505050620003c76080870162000290565b90509295509295909350565b600181811c90821680620003e857607f821691505b6020821081036200040957634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156200045d57600081815260208120601f850160051c81016020861015620004385750805b601f850160051c820191505b81811015620004595782815560010162000444565b5050505b505050565b81516001600160401b038111156200047e576200047e620001ba565b62000496816200048f8454620003d3565b846200040f565b602080601f831160018114620004ce5760008415620004b55750858301515b600019600386901b1c1916600185901b17855562000459565b600085815260208120601f198616915b82811015620004ff57888601518255948401946001909101908401620004de565b50858210156200051e5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b634e487b7160e01b600052602160045260246000fd5b6114ba80620005546000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c806398c0793811610097578063c0f2a77711610066578063c0f2a7771461023a578063c631b29214610242578063cc2ee1961461024c578063fc36e15b1461026157600080fd5b806398c07938146101fe578063a3ec138d14610207578063a4d4334a1461022a578063bf63a5771461023257600080fd5b8063409e2205116100d3578063409e2205146101a657806357c9d738146101c6578063597e1fb5146101d9578063889ae9f2146101f657600080fd5b806302d05d3f1461010557806319ad7cd2146101355780631ee1f8001461016e5780634051ddac1461018d575b600080fd5b600054610118906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b610160610143366004610fe7565b805160208183018101805160058252928201919093012091525481565b60405190815260200161012c565b60085461018090610100900460ff1681565b60405161012c91906110ae565b610195610274565b60405161012c959493929190611126565b6101b96101b4366004611176565b61033c565b60405161012c919061118f565b6101606101d4366004610fe7565b6103e8565b6008546101e69060ff1681565b604051901515815260200161012c565b6101b9610410565b61016060075481565b6101e66102153660046111a9565b60066020526000908152604090205460ff1681565b6101606104bd565b6101b9610747565b600754610160565b61024a610754565b005b61025461081c565b60405161012c91906111d2565b61024a61026f366004610fe7565b610b6d565b600080546007546008546060928492839285926001600160a01b03169160039160ff1661029f610410565b8380546102ab90611234565b80601f01602080910402602001604051908101604052809291908181526020018280546102d790611234565b80156103245780601f106102f957610100808354040283529160200191610324565b820191906000526020600020905b81548152906001019060200180831161030757829003601f168201915b50505050509350945094509450945094509091929394565b6004818154811061034c57600080fd5b90600052602060002001600091509050805461036790611234565b80601f016020809104026020016040519081016040528092919081815260200182805461039390611234565b80156103e05780601f106103b5576101008083540402835291602001916103e0565b820191906000526020600020905b8154815290600101906020018083116103c357829003601f168201915b505050505081565b60006005826040516103fa919061126e565b9081526020016040518091039020549050919050565b60606000600854610100900460ff16600281111561043057610430611098565b03610458575060408051808201909152600881526773747564656e747360c01b602082015290565b6001600854610100900460ff16600281111561047657610476611098565b0361049f5750604080518082019091526009815268656d706c6f7965657360b81b602082015290565b50604080518082019091526003815262185b1b60ea1b602082015290565b60006002600854610100900460ff1660028111156104dd576104dd611098565b036105ea5760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa15801561052a573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061054e919061128a565b806105c0575060025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa15801561059c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105c0919061128a565b6105e55760405162461bcd60e51b81526004016105dc906112ac565b60405180910390fd5b610740565b6000600854610100900460ff16600281111561060857610608611098565b036106955760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa158015610655573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610679919061128a565b6105e55760405162461bcd60e51b81526004016105dc906112fe565b6001600854610100900460ff1660028111156106b3576106b3611098565b036107405760025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa158015610700573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610724919061128a565b6107405760405162461bcd60e51b81526004016105dc90611342565b5060045490565b6003805461036790611234565b6000546001600160a01b031633146107ba5760405162461bcd60e51b815260206004820152602360248201527f4f6e6c792063726561746f722063616e2063616c6c20746869732066756e637460448201526234b7b760e91b60648201526084016105dc565b60085460ff161561080d5760405162461bcd60e51b815260206004820152601860248201527f566f74696e6720697320616c726561647920636c6f736564000000000000000060448201526064016105dc565b6008805460ff19166001179055565b60606002600854610100900460ff16600281111561083c5761083c611098565b036109405760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa158015610889573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108ad919061128a565b8061091f575060025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa1580156108fb573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061091f919061128a565b61093b5760405162461bcd60e51b81526004016105dc906112ac565b610a96565b6000600854610100900460ff16600281111561095e5761095e611098565b036109eb5760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa1580156109ab573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109cf919061128a565b61093b5760405162461bcd60e51b81526004016105dc906112fe565b6001600854610100900460ff166002811115610a0957610a09611098565b03610a965760025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa158015610a56573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a7a919061128a565b610a965760405162461bcd60e51b81526004016105dc90611342565b6004805480602002602001604051908101604052809291908181526020016000905b82821015610b64578382906000526020600020018054610ad790611234565b80601f0160208091040260200160405190810160405280929190818152602001828054610b0390611234565b8015610b505780601f10610b2557610100808354040283529160200191610b50565b820191906000526020600020905b815481529060010190602001808311610b3357829003601f168201915b505050505081526020019060010190610ab8565b50505050905090565b6002600854610100900460ff166002811115610b8b57610b8b611098565b03610c8f5760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa158015610bd8573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610bfc919061128a565b80610c6e575060025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa158015610c4a573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c6e919061128a565b610c8a5760405162461bcd60e51b81526004016105dc906112ac565b610de5565b6000600854610100900460ff166002811115610cad57610cad611098565b03610d3a5760025460405163417be9c960e11b81523360048201526001600160a01b03909116906382f7d39290602401602060405180830381865afa158015610cfa573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610d1e919061128a565b610c8a5760405162461bcd60e51b81526004016105dc906112fe565b6001600854610100900460ff166002811115610d5857610d58611098565b03610de55760025460405163ea66543f60e01b81523360048201526001600160a01b039091169063ea66543f90602401602060405180830381865afa158015610da5573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610dc9919061128a565b610de55760405162461bcd60e51b81526004016105dc90611342565b3360009081526006602052604090205460ff1615610e3e5760405162461bcd60e51b8152602060048201526016602482015275165bdd481a185d9948185b1c9958591e481d9bdd195960521b60448201526064016105dc565b60085460ff1615610e845760405162461bcd60e51b815260206004820152601060248201526f159bdd1a5b99c81a5cc818db1bdcd95960821b60448201526064016105dc565b6000805b600454811015610f1c5782604051602001610ea3919061126e565b6040516020818303038152906040528051906020012060048281548110610ecc57610ecc611387565b90600052602060002001604051602001610ee6919061139d565b6040516020818303038152906040528051906020012003610f0a5760019150610f1c565b80610f1481611452565b915050610e88565b5080610f625760405162461bcd60e51b815260206004820152601560248201527413dc1d1a5bdb88191bd95cc81b9bdd08195e1a5cdd605a1b60448201526064016105dc565b3360009081526006602052604090819020805460ff191660019081179091559051600590610f9190859061126e565b90815260200160405180910390206000828254610fae919061146b565b92505081905550600160076000828254610fc8919061146b565b90915550505050565b634e487b7160e01b600052604160045260246000fd5b600060208284031215610ff957600080fd5b813567ffffffffffffffff8082111561101157600080fd5b818401915084601f83011261102557600080fd5b81358181111561103757611037610fd1565b604051601f8201601f19908116603f0116810190838211818310171561105f5761105f610fd1565b8160405282815287602084870101111561107857600080fd5b826020860160208301376000928101602001929092525095945050505050565b634e487b7160e01b600052602160045260246000fd5b60208101600383106110d057634e487b7160e01b600052602160045260246000fd5b91905290565b60005b838110156110f15781810151838201526020016110d9565b50506000910152565b600081518084526111128160208601602086016110d6565b601f01601f19169290920160200192915050565b6001600160a01b038616815260a06020820181905260009061114a908301876110fa565b8560408401528415156060840152828103608084015261116a81856110fa565b98975050505050505050565b60006020828403121561118857600080fd5b5035919050565b6020815260006111a260208301846110fa565b9392505050565b6000602082840312156111bb57600080fd5b81356001600160a01b03811681146111a257600080fd5b6000602080830181845280855180835260408601915060408160051b870101925083870160005b8281101561122757603f198886030184526112158583516110fa565b945092850192908501906001016111f9565b5092979650505050505050565b600181811c9082168061124857607f821691505b60208210810361126857634e487b7160e01b600052602260045260246000fd5b50919050565b600082516112808184602087016110d6565b9190910192915050565b60006020828403121561129c57600080fd5b815180151581146111a257600080fd5b60208082526032908201527f4f6e6c792073747564656e747320616e6420656d706c6f796565732063616e2060408201527131b0b636103a3434b990333ab731ba34b7b760711b606082015260800190565b60208082526024908201527f4f6e6c792073747564656e74732063616e2063616c6c20746869732066756e636040820152633a34b7b760e11b606082015260800190565b60208082526025908201527f4f6e6c7920656d706c6f796565732063616e2063616c6c20746869732066756e60408201526431ba34b7b760d91b606082015260800190565b634e487b7160e01b600052603260045260246000fd5b600080835481600182811c9150808316806113b957607f831692505b602080841082036113d857634e487b7160e01b86526022600452602486fd5b8180156113ec57600181146114015761142e565b60ff198616895284151585028901965061142e565b60008a81526020902060005b868110156114265781548b82015290850190830161140d565b505084890196505b509498975050505050505050565b634e487b7160e01b600052601160045260246000fd5b6000600182016114645761146461143c565b5060010190565b8082018082111561147e5761147e61143c565b9291505056fea2646970667358221220584df3eed531272e019138e20a21b2b1c05bac20ea7ceeef2ae7bc38cea4c0cb64736f6c63430008110033";

    public static final String FUNC_AUDIENCE = "audience";

    public static final String FUNC_CLOSEVOTING = "closeVoting";

    public static final String FUNC_CLOSED = "closed";

    public static final String FUNC_CREATOR = "creator";

    public static final String FUNC_GETAUDIENCETOSTRING = "getAudienceToString";

    public static final String FUNC_GETOPTIONVOTES = "getOptionVotes";

    public static final String FUNC_GETOPTIONS = "getOptions";

    public static final String FUNC_GETOPTIONSCOUNT = "getOptionsCount";

    public static final String FUNC_GETSUMMARY = "getSummary";

    public static final String FUNC_GETVOTERSCOUNT = "getVotersCount";

    public static final String FUNC_OPTIONS = "options";

    public static final String FUNC_OPTIONSVOTES = "optionsVotes";

    public static final String FUNC_TOPIC = "topic";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_VOTERSCOUNT = "votersCount";

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> audience() {
        final Function function = new Function(FUNC_AUDIENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> closeVoting() {
        final Function function = new Function(
                FUNC_CLOSEVOTING, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> closed() {
        final Function function = new Function(FUNC_CLOSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> creator() {
        final Function function = new Function(FUNC_CREATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAudienceToString() {
        final Function function = new Function(FUNC_GETAUDIENCETOSTRING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getOptionVotes(String option) {
        final Function function = new Function(FUNC_GETOPTIONVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(option)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getOptions() {
        final Function function = new Function(FUNC_GETOPTIONS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
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

    public RemoteFunctionCall<BigInteger> getOptionsCount() {
        final Function function = new Function(FUNC_GETOPTIONSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple5<String, String, BigInteger, Boolean, String>> getSummary() {
        final Function function = new Function(FUNC_GETSUMMARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, BigInteger, Boolean, String>>(function,
                new Callable<Tuple5<String, String, BigInteger, Boolean, String>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, Boolean, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, Boolean, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getVotersCount() {
        final Function function = new Function(FUNC_GETVOTERSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> options(BigInteger param0) {
        final Function function = new Function(FUNC_OPTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> optionsVotes(String param0) {
        final Function function = new Function(FUNC_OPTIONSVOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> topic() {
        final Function function = new Function(FUNC_TOPIC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(String option) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(option)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> votersCount() {
        final Function function = new Function(FUNC_VOTERSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Voting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _factoryAddress, String _creator, String _topic, List<String> _options, BigInteger _audience) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _factoryAddress), 
                new org.web3j.abi.datatypes.Address(160, _creator), 
                new org.web3j.abi.datatypes.Utf8String(_topic), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_options, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(_audience)));
        return deployRemoteCall(Voting.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _factoryAddress, String _creator, String _topic, List<String> _options, BigInteger _audience) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _factoryAddress), 
                new org.web3j.abi.datatypes.Address(160, _creator), 
                new org.web3j.abi.datatypes.Utf8String(_topic), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_options, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(_audience)));
        return deployRemoteCall(Voting.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _factoryAddress, String _creator, String _topic, List<String> _options, BigInteger _audience) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _factoryAddress), 
                new org.web3j.abi.datatypes.Address(160, _creator), 
                new org.web3j.abi.datatypes.Utf8String(_topic), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_options, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(_audience)));
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _factoryAddress, String _creator, String _topic, List<String> _options, BigInteger _audience) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _factoryAddress), 
                new org.web3j.abi.datatypes.Address(160, _creator), 
                new org.web3j.abi.datatypes.Utf8String(_topic), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_options, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.generated.Uint8(_audience)));
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
