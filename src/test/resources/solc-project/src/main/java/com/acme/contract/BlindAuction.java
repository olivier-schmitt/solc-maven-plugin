package com.acme.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public final class BlindAuction extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b604051606080610c6a833981016040528080519060200190919080519060200190919080519060200190919050505b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055504260018190555082420160028190555081600254016003819055505b5050505b610bbf806100ab6000396000f300606060405236156100b8576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806301495c1c146100ba57806312fa6feb1461011c5780632a24f46c1461014657806338af3eed146101585780633ccfd60b146101aa578063423b217f146101d45780634f245ef7146101fa578063900f080a1461022057806391f90157146102f7578063957bb1e014610349578063a6e6647714610365578063d57bde791461038b575bfe5b34156100c257fe5b6100f7600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506103b1565b6040518083600019166000191681526020018281526020019250505060405180910390f35b341561012457fe5b61012c6103f2565b604051808215151515815260200191505060405180910390f35b341561014e57fe5b610156610405565b005b341561016057fe5b61016861055c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156101b257fe5b6101ba610582565b604051808215151515815260200191505060405180910390f35b34156101dc57fe5b6101e46106ab565b6040518082815260200191505060405180910390f35b341561020257fe5b61020a6106b1565b6040518082815260200191505060405180910390f35b341561022857fe5b6102f560048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919050506106b7565b005b34156102ff57fe5b61030761092c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610363600480803560001916906020019091905050610952565b005b341561036d57fe5b610375610a08565b6040518082815260200191505060405180910390f35b341561039357fe5b61039b610a0e565b6040518082815260200191505060405180910390f35b6005602052816000526040600020818154811015156103cc57fe5b906000526020600020906002020160005b91509150508060000154908060010154905082565b600460009054906101000a900460ff1681565b60035480421115156104175760006000fd5b600460009054906101000a900460ff161515156104345760006000fd5b7fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600754604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a16001600460006101000a81548160ff021916908315150217905550600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc3073ffffffffffffffffffffffffffffffffffffffff16319081150290604051809050600060405180830381858888f19350505050151561055757fe5b5b5b50565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006000600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905060008111156106a2576000600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055503373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051809050600060405180830381858888f1935050505015156106a15780600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600091506106a7565b5b600191505b5090565b60025481565b60015481565b600060006000600060006000600060025480421115156106d75760006000fd5b60035480421015156106e95760006000fd5b600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805490509850888c5114151561073e5760006000fd5b888b5114151561074e5760006000fd5b888a5114151561075e5760006000fd5b600096505b888710156108db57600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020878154811015156107b757fe5b906000526020600020906002020160005b5095508b878151811015156107d957fe5b906020019060200201518b888151811015156107f157fe5b906020019060200201518b8981518110151561080957fe5b906020019060200201519450945094508484846040518084815260200183151515157f010000000000000000000000000000000000000000000000000000000000000002815260010182600019166000191681526020019350505050604051809103902060001916866000015460001916141515610886576108ce565b856001015488019750831580156108a1575084866001015410155b156108bc576108b03386610a14565b156108bb5784880397505b5b60006001028660000181600019169055505b8680600101975050610763565b3373ffffffffffffffffffffffffffffffffffffffff166108fc899081150290604051809050600060405180830381858888f19350505050151561091b57fe5b5b5b505b5050505050505050505050565b600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60025480421015156109645760006000fd5b600560003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002080548060010182816109b59190610b31565b916000526020600020906002020160005b6040604051908101604052808660001916815260200134815250909190915060008201518160000190600019169055602082015181600101555050505b5b5050565b60035481565b60075481565b600060075482111515610a2a5760009050610b2b565b6000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610ade5760075460086000600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b8160078190555082600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600190505b92915050565b815481835581811511610b5e57600202816002028360005260206000209182019101610b5d9190610b63565b5b505050565b610b9091905b80821115610b8c5760006000820160009055600182016000905550600201610b69565b5090565b905600a165627a7a72305820f4ae14c2acd0eb63b1f3dda318a68496159740e01a403199ec82435af338b57d0029";

    private BlindAuction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private BlindAuction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<AuctionEndedEventResponse> getAuctionEndedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("AuctionEnded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AuctionEndedEventResponse> responses = new ArrayList<AuctionEndedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
            typedResponse.winner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.highestBid = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AuctionEndedEventResponse> auctionEndedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("AuctionEnded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AuctionEndedEventResponse>() {
            @Override
            public AuctionEndedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
                typedResponse.winner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.highestBid = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<List<Type>> bids(Address param0, Uint256 param1) {
        Function function = new Function("bids", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<Bool> ended() {
        Function function = new Function("ended", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> auctionEnd() {
        Function function = new Function("auctionEnd", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> beneficiary() {
        Function function = new Function("beneficiary", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> withdraw() {
        Function function = new Function("withdraw", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> biddingEnd() {
        Function function = new Function("biddingEnd", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> auctionStart() {
        Function function = new Function("auctionStart", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> reveal(DynamicArray<Uint256> _values, DynamicArray<Bool> _fake, DynamicArray<Bytes32> _secret) {
        Function function = new Function("reveal", Arrays.<Type>asList(_values, _fake, _secret), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> highestBidder() {
        Function function = new Function("highestBidder", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> bid(Bytes32 _blindedBid) {
        Function function = new Function("bid", Arrays.<Type>asList(_blindedBid), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> revealEnd() {
        Function function = new Function("revealEnd", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> highestBid() {
        Function function = new Function("highestBid", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<BlindAuction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 _biddingTime, Uint256 _revealTime, Address _beneficiary) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_biddingTime, _revealTime, _beneficiary));
        return deployAsync(BlindAuction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Future<BlindAuction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, Uint256 _biddingTime, Uint256 _revealTime, Address _beneficiary) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_biddingTime, _revealTime, _beneficiary));
        return deployAsync(BlindAuction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static BlindAuction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlindAuction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BlindAuction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlindAuction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AuctionEndedEventResponse {
        public Address winner;

        public Uint256 highestBid;
    }
}
