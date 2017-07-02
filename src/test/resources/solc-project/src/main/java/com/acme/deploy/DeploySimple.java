package com.acme.deploy;

import com.acme.contract.SimpleStorage;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DeploySimple {

    public static final String PASSWORD = "password";


    public static void main(String[] args) throws Exception {

        Web3j web3 = Web3j.build(new HttpService());

        Parity parity = Parity.build(new HttpService());  // defaults to http://localhost:8545/

               PersonalUnlockAccount personalUnlockAccount = parity.personalUnlockAccount(
                //"0x38d8c1b1382249e1567af808f3f70a022cca733f"
                "0x814ae26aa441b46530fd15f7a22ecd2965ad5100"
                , PASSWORD)
                .send();

        if (personalUnlockAccount.accountUnlocked()) {

            String ETH_HOME = "/Users/oschmitt/ethereum/1.6.6";//System.getenv("ETH_HOME");
            /*
            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    "/home/olivierschmitt/java/poc/block/chains/keystore/UTC--2017-06-13T09-16-32.804957175Z--38d8c1b1382249e1567af808f3f70a022cca733f");

            */
            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    ETH_HOME + "/data/keystore/UTC--2017-07-01T16-32-38.594279530Z--814ae26aa441b46530fd15f7a22ecd2965ad5100");


            //UTC--2017-07-01T16-32-38.594279530Z--814ae26aa441b46530fd15f7a22ecd2965ad5100

            BigInteger gasPrice =  BigInteger.valueOf(18_000_000_000L);
            BigInteger gasLimit = Contract.GAS_LIMIT;
            BigInteger initialWeiValue = BigInteger.ONE;

            Future<SimpleStorage> future = SimpleStorage.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue);

            SimpleStorage simpleStorage = null;
            String address = null;
            boolean done = false;
            do {
                Thread.sleep(500);
                if(done = future.isDone()){
                    simpleStorage = future.get();
                    System.out.println(address = simpleStorage.getContractAddress());
                    Optional<TransactionReceipt> transactionReceipt = simpleStorage.getTransactionReceipt();
                    System.out.println(transactionReceipt.get().getTransactionHash());
                }
                else {
                    System.out.println("Waiting for contract to be deployed ...");
                }
            } while(!done);

            simpleStorage = SimpleStorage.load(address,web3, credentials,
                    gasPrice, gasLimit);

            simpleStorage.set(new Uint256(100)).get();

            simpleStorage = SimpleStorage.load(address,web3, credentials,
                    gasPrice, gasLimit);

            Uint256 result = simpleStorage.get().get();

            System.out.println(result);

        }
    }
}
