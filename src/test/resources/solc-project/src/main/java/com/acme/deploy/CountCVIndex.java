package com.acme.deploy;

import com.acme.contract.CVIndex;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class CountCVIndex {

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

            CVIndex cvIndex = CVIndex.load(
                    "0x7cd37aa05d3d17adc8aee1dcf349fdd288c16742",
                    web3,
                    credentials,
                    gasPrice, gasLimit);

            Future<TransactionReceipt> transactionReceiptFuture =  cvIndex.addCV(new Address("0x317f260821a8c575a6f5403b3af368a55b15b090"));
            boolean done = false;
            do {
                Thread.sleep(500);
                if(done = transactionReceiptFuture.isDone()){
                    TransactionReceipt transactionReceipt = transactionReceiptFuture.get();
                    System.out.println(transactionReceipt.getTransactionHash());

                }
                else {
                    System.out.println("Waiting for transaction receipt ...");
                }
            } while(!done);


            cvIndex.activateCV(new Uint256(0)).get();

            Uint256 num = cvIndex.getNumCVs().get();
            System.out.println(num.getValue());
        }
    }
}
