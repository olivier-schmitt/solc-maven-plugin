package com.acme.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.2.
 */
public final class Ballot extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051610acc380380610acc833981016040528080518201919050505b600033600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160016000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000181905550600090505b815181101561016b57600280548060010182816100f89190610173565b916000526020600020906002020160005b604060405190810160405280868681518110151561012357fe5b906020019060200201516000191681526020016000815250909190915060008201518160000190600019169055602082015181600101555050505b80806001019150506100db565b5b50506101d5565b8154818355818115116101a05760020281600202836000526020600020918201910161019f91906101a5565b5b505050565b6101d291905b808211156101ce57600060008201600090556001820160009055506002016101ab565b5090565b90565b6108e8806101e46000396000f3006060604052361561008c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630121b93f1461008e578063013cf08b146100ae5780632e4176cf146100f15780635c19a95c14610143578063609ff1bd146101795780639e7b8d611461019f578063a3ec138d146101d5578063e2ba53f014610264575bfe5b341561009657fe5b6100ac6004808035906020019091905050610292565b005b34156100b657fe5b6100cc6004808035906020019091905050610355565b6040518083600019166000191681526020018281526020019250505060405180910390f35b34156100f957fe5b610101610389565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561014b57fe5b610177600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506103af565b005b341561018157fe5b6101896106a2565b6040518082815260200191505060405180910390f35b34156101a757fe5b6101d3600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610729565b005b34156101dd57fe5b610209600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061082b565b60405180858152602001841515151581526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200194505050505060405180910390f35b341561026c57fe5b610274610888565b60405180826000191660001916815260200191505060405180910390f35b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff161515156102f45760006000fd5b60018160010160006101000a81548160ff021916908315150217905550818160020181905550806000015460028381548110151561032e57fe5b906000526020600020906002020160005b50600101600082825401925050819055505b5050565b60028181548110151561036457fe5b906000526020600020906002020160005b915090508060000154908060010154905082565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002091508160010160009054906101000a900460ff161515156104135760006000fd5b3373ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff161415151561044f5760006000fd5b5b600073ffffffffffffffffffffffffffffffffffffffff16600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561058e57600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1692503373ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16141515156105895760006000fd5b610450565b60018260010160006101000a81548160ff021916908315150217905550828260010160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156106855781600001546002826002015481548110151561065e57fe5b906000526020600020906002020160005b506001016000828254019250508190555061069c565b816000015481600001600082825401925050819055505b5b505050565b60006000600060009150600090505b60028054905081101561072357816002828154811015156106ce57fe5b906000526020600020906002020160005b50600101541115610715576002818154811015156106f957fe5b906000526020600020906002020160005b506001015491508092505b5b80806001019150506106b1565b5b505090565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161480156107d35750600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff16155b15156107df5760006000fd5b6001600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600001819055505b50565b60016020528060005260406000206000915090508060000154908060010160009054906101000a900460ff16908060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020154905084565b600060026108946106a2565b8154811015156108a057fe5b906000526020600020906002020160005b506000015490505b905600a165627a7a7230582084cfbb1701500e21ac02c1f8102a4f989bd30ab6d7e36849a977300fa3d353870029";

    private Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> vote(Uint256 proposal) {
        Function function = new Function("vote", Arrays.<Type>asList(proposal), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> proposals(Uint256 param0) {
        Function function = new Function("proposals", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<Address> chairperson() {
        Function function = new Function("chairperson", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> delegate(Address to) {
        Function function = new Function("delegate", Arrays.<Type>asList(to), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> winningProposal() {
        Function function = new Function("winningProposal", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> giveRightToVote(Address voter) {
        Function function = new Function("giveRightToVote", Arrays.<Type>asList(voter), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> voters(Address param0) {
        Function function = new Function("voters", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<Bytes32> winnerName() {
        Function function = new Function("winnerName", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Bytes32> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(proposalNames));
        return deployAsync(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, DynamicArray<Bytes32> proposalNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(proposalNames));
        return deployAsync(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
