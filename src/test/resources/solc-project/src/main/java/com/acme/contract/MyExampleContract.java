package com.acme.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public final class MyExampleContract extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b6106138061001c6000396000f30060606040523615610081576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631a0925411461008357806338cc48311461011c57806378c85525146101b55780637e9aef53146101c7578063a5faa125146101f1578063ebf9b9c114610304578063ff3c1a8f14610316575bfe5b341561008b57fe5b6100936103af565b60405180806020018281038252838181518152602001915080519060200190808383600083146100e2575b8051825260208311156100e2576020820191506020810190506020830392506100be565b505050905090810190601f16801561010e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561012457fe5b61012c6103f4565b604051808060200182810382528381815181526020019150805190602001908083836000831461017b575b80518252602083111561017b57602082019150602081019050602083039250610157565b505050905090810190601f1680156101a75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101bd57fe5b6101c5610439565b005b34156101cf57fe5b6101d761043c565b604051808215151515815260200191505060405180910390f35b34156101f957fe5b6102016104f0565b604051808060200180602001838103835285818151815260200191508051906020019080838360008314610254575b80518252602083111561025457602082019150602081019050602083039250610230565b505050905090810190601f1680156102805780820380516001836020036101000a031916815260200191505b508381038252848181518152602001915080519060200190808383600083146102c8575b8051825260208311156102c8576020820191506020810190506020830392506102a4565b505050905090810190601f1680156102f45780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b341561030c57fe5b610314610577565b005b341561031e57fe5b61032661057a565b6040518080602001828103825283818151815260200191508051906020019080838360008314610375575b80518252602083111561037557602082019150602081019050602083039250610351565b505050905090810190601f1680156103a15780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6103b76105bf565b604060405190810160405280601281526020017f5468697320697320616e206578616d706c65000000000000000000000000000081525090505b90565b6103fc6105bf565b604060405190810160405280601681526020017f687474703a2f2f7777772e6578616d706c652e6f72670000000000000000000081525090505b90565b5b565b60006104466105d3565b61044e6105bf565b6104566105bf565b61045e6103af565b925060008351141561047357600093506104ea565b61047b61057a565b925060008351141561049057600093506104ea565b6104986104f0565b915091508192506000835114156104b257600093506104ea565b8092506000835114156104c857600093506104ea565b6104d06103f4565b92506000835114156104e557600093506104ea565b600193505b50505090565b6104f86105bf565b6105006105bf565b604060405190810160405280600681526020017f54686f6d61730000000000000000000000000000000000000000000000000000815250604060405190810160405280601281526020017f74686f6d6173406578616d706c652e6f72670000000000000000000000000000815250915091505b9091565b5b565b6105826105bf565b604060405190810160405280600d81526020017f53696d706c654578616d706c650000000000000000000000000000000000000081525090505b90565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a72305820a4f4365a07b068b251c4b97551484fee32f8d5ea9541d056a32e1f3a39e9e0d40029";

    private MyExampleContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private MyExampleContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Utf8String> getDescription() {
        Function function = new Function("getDescription", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getAddress() {
        Function function = new Function("getAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> MyFunction1() {
        Function function = new Function("MyFunction1", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> elementsAreSet() {
        Function function = new Function("elementsAreSet", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<List<Type>> getAuthor() {
        Function function = new Function("getAuthor", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> MyFunction2() {
        Function function = new Function("MyFunction2", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getTitle() {
        Function function = new Function("getTitle", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<MyExampleContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MyExampleContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<MyExampleContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(MyExampleContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static MyExampleContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyExampleContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MyExampleContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyExampleContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
