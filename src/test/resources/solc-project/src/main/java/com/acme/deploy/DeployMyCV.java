package com.acme.deploy;

import com.acme.contract.MyExampleContract;
import com.acme.contract.SimpleStorage;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.Future;

// 0x317f260821a8c575a6f5403b3af368a55b15b090
public class DeployMyCV {

    public static final String PASSWORD = "password";
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

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


            BigInteger gasPrice = GAS_PRICE;
            BigInteger gasLimit = GAS_LIMIT;
            BigInteger initialWeiValue = BigInteger.ONE;

            Future<MyExampleContract> future = MyExampleContract.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue);


            boolean done = false;
            do {
                Thread.sleep(500);
                if(done = future.isDone()){
                    MyExampleContract myExampleContract = future.get();
                    System.out.println(myExampleContract.getContractAddress());
                    Optional<TransactionReceipt> transactionReceipt = myExampleContract.getTransactionReceipt();
                    System.out.println(transactionReceipt.get().getTransactionHash());
                }
                else {
                    System.out.println("Waiting for contract to be deployed ...");
                }
            } while(!done);

        }
    }
}
