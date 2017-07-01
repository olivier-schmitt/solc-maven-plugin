package com.acme.deploy;

import com.acme.contract.CVIndex;
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
// 0x7cd37aa05d3d17adc8aee1dcf349fdd288c16742
public class DeployCVIndex {

    public static final String PASSWORD = "password";
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

    public static void main(String[] args) throws Exception {

        Web3j web3 = Web3j.build(new HttpService());

        Parity parity = Parity.build(new HttpService());  // defaults to http://localhost:8545/

        PersonalUnlockAccount personalUnlockAccount = parity.personalUnlockAccount(
                "0x38d8c1b1382249e1567af808f3f70a022cca733f"
                , PASSWORD)
                .send();

        if (personalUnlockAccount.accountUnlocked()) {

            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    "/home/olivierschmitt/java/poc/block/chains/keystore/UTC--2017-06-13T09-16-32.804957175Z--38d8c1b1382249e1567af808f3f70a022cca733f");



            BigInteger gasPrice = GAS_PRICE;
            BigInteger gasLimit = GAS_LIMIT;
            BigInteger initialWeiValue = BigInteger.ONE;

            Future<CVIndex> future = CVIndex.deploy(web3, credentials, gasPrice, gasLimit, initialWeiValue);


            boolean done = false;
            do {
                Thread.sleep(500);
                if(done = future.isDone()){
                    CVIndex cvIndex = future.get();
                    System.out.println(cvIndex.getContractAddress());
                    Optional<TransactionReceipt> transactionReceipt = cvIndex.getTransactionReceipt();
                    System.out.println(transactionReceipt.get().getTransactionHash());
                }
                else {
                    System.out.println("Waiting for contract to be deployed ...");
                }
            } while(!done);

        }
    }
}
