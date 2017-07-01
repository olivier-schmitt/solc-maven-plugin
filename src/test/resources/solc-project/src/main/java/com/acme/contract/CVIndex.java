package com.acme.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.2.
 */
public final class CVIndex extends Contract {
    private static final String BINARY = "6060604052600060015560006002555b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b610c0c806100616000396000f3006060604052361561008c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806341c0e1b51461008e57806354e20048146100a05780635b48e7f4146100c05780638888437f146100e657806390174a6c1461011e578063b2ad739d14610154578063c8c1fce714610174578063da6f99fd146101d4575bfe5b341561009657fe5b61009e610234565b005b34156100a857fe5b6100be60048080359060200190919050506102c8565b005b34156100c857fe5b6100d0610539565b6040518082815260200191505060405180910390f35b34156100ee57fe5b6101046004808035906020019091905050610544565b604051808215151515815260200191505060405180910390f35b341561012657fe5b610152600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610572565b005b341561015c57fe5b610172600480803590602001909190505061080e565b005b341561017c57fe5b6101926004808035906020019091905050610b2b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156101dc57fe5b6101f26004808035906020019091905050610b9f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156102c557600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156105355760016003600083815260200190815260200160002060000160146101000a81548160ff021916908315150217905550600260008154809291906001019190505550600360008281526020019081526020016000206004600060025481526020019081526020016000206000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000820160149054906101000a900460ff168160000160146101000a81548160ff0219169083151502179055506001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600282015481600201559050506002547f74e0fa1e41bb18756915b0e4bdd9e5dc1671a75ee07fa8233a9e237c5998b0076003600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1642604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a25b5b5b50565b600060025490505b90565b60006003600083815260200190815260200160002060000160149054906101000a900460ff1690505b919050565b60006001600081548092919060010191905055508190508073ffffffffffffffffffffffffffffffffffffffff16637e9aef536000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156105f557fe5b6102c65a03f1151561060357fe5b5050506040518051905015610803576080604051908101604052808373ffffffffffffffffffffffffffffffffffffffff1681526020016000151581526020013373ffffffffffffffffffffffffffffffffffffffff1681526020014281525060036000600154815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548160ff02191690831515021790555060408201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550606082015181600201559050503373ffffffffffffffffffffffffffffffffffffffff166001547ff116068e52439f20b6bfac8c83fb068c1d029cdae615693df802ef93d689690d8442604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a3600154600560008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550610809565b60006000fd5b5b5050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610b275760006004600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610b2557600060036000600560006004600087815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054815260200190815260200160002060000160146101000a81548160ff021916908315150217905550600460006002548152602001908152602001600020600460008381526020019081526020016000206000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000820160149054906101000a900460ff168160000160146101000a81548160ff0219169083151502179055506001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506002820154816002015590505060046000600254815260200190815260200160002060006000820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556000820160146101000a81549060ff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560028201600090555050600260008154809291906001900391905055505b5b5b5b50565b60006004600083815260200190815260200160002060000160149054906101000a900460ff1615610b94576004600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050610b9a565b60006000fd5b919050565b60006003600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690505b9190505600a165627a7a7230582031dbdb64c7b62a6ca4fcca42703e523ef106b124d2decc882e166195c7e3de990029";

    private CVIndex(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private CVIndex(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ProposedCVEventResponse> getProposedCVEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("ProposedCV", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ProposedCVEventResponse> responses = new ArrayList<ProposedCVEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ProposedCVEventResponse typedResponse = new ProposedCVEventResponse();
            typedResponse.cvindex = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.by = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.cvaddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.date = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ProposedCVEventResponse> proposedCVEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("ProposedCV", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ProposedCVEventResponse>() {
            @Override
            public ProposedCVEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ProposedCVEventResponse typedResponse = new ProposedCVEventResponse();
                typedResponse.cvindex = (Uint256) eventValues.getIndexedValues().get(0);
                typedResponse.by = (Address) eventValues.getIndexedValues().get(1);
                typedResponse.cvaddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.date = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<ActivatedCVEventResponse> getActivatedCVEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("ActivatedCV", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ActivatedCVEventResponse> responses = new ArrayList<ActivatedCVEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ActivatedCVEventResponse typedResponse = new ActivatedCVEventResponse();
            typedResponse.cvindex_active = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.cvaddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.date = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ActivatedCVEventResponse> activatedCVEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("ActivatedCV", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ActivatedCVEventResponse>() {
            @Override
            public ActivatedCVEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ActivatedCVEventResponse typedResponse = new ActivatedCVEventResponse();
                typedResponse.cvindex_active = (Uint256) eventValues.getIndexedValues().get(0);
                typedResponse.cvaddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.date = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> activateCV(Uint256 cvIndex) {
        Function function = new Function("activateCV", Arrays.<Type>asList(cvIndex), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getNumCVs() {
        Function function = new Function("getNumCVs", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> isCvActive(Uint256 _index) {
        Function function = new Function("isCvActive", 
                Arrays.<Type>asList(_index), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addCV(Address cvAddress) {
        Function function = new Function("addCV", Arrays.<Type>asList(cvAddress), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> deactivateCV(Uint256 cvIndexActive) {
        Function function = new Function("deactivateCV", Arrays.<Type>asList(cvIndexActive), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getAddressAtIndex(Uint256 _index) {
        Function function = new Function("getAddressAtIndex", 
                Arrays.<Type>asList(_index), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getAddressAtIndexUnconfirmed(Uint256 _index) {
        Function function = new Function("getAddressAtIndexUnconfirmed", 
                Arrays.<Type>asList(_index), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<CVIndex> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(CVIndex.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<CVIndex> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(CVIndex.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static CVIndex load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CVIndex(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CVIndex load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CVIndex(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ProposedCVEventResponse {
        public Uint256 cvindex;

        public Address by;

        public Address cvaddress;

        public Uint256 date;
    }

    public static class ActivatedCVEventResponse {
        public Uint256 cvindex_active;

        public Address cvaddress;

        public Uint256 date;
    }
}
