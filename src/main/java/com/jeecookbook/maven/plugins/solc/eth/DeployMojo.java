package com.jeecookbook.maven.plugins.solc.eth;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Contract;
import org.web3j.tx.RawTransactionManager;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Goal which deploy solc.
 */
@Mojo(name = "deploy", defaultPhase = LifecyclePhase.DEPLOY)
public class DeployMojo extends AbstractEthMojo {

    class Bin {
        private String path;
    }

    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering deploy mojo.");
            FileSet defaultBinsFileSet = createDefaultBinsFileSet();
            String basePath = defaultBinsFileSet.getDirectory();

            Map<String, Bin> binsForSols = computeBinsForSols(defaultBinsFileSet, basePath);
            boolean hasErrors = false;
            for (String solFile : binsForSols.keySet()) {
                Bin bin = binsForSols.get(solFile);
                hasErrors = deploy(bin);
            }
            if (hasErrors) {
                throw new MojoExecutionException("Wrapper failed.");
            }
        } finally {
            getLog().debug("Exiting deploy mojo.");
        }
    }

    protected boolean deploy(Bin bin) {
        boolean hasErrors = false;
        getLog().debug("Generate wrapper for : " + bin.path);
        if (bin != null) {
            try {

                String hexBin = load(bin.path);

                String address = getAccount().getPublicKey();
                String password = getAccount().getPassword();

                Parity parity = Parity.build(new HttpService());
                Web3j web3j = Web3j.build(new HttpService());

                PersonalUnlockAccount personalUnlockAccount = parity
                        .personalUnlockAccount(address, password)
                        .send();

                if(personalUnlockAccount.accountUnlocked()){

                    Credentials credentials = WalletUtils.loadCredentials(
                            getWallet().getPassword(),
                            getWallet().getPath());

                    TransactionReceipt transactionReceipt = sendContractTransaction(web3j, credentials,hexBin);
                    if(transactionReceipt != null){
                        String txhash = transactionReceipt.getTransactionHash();
                        String contractAdress = transactionReceipt.getContractAddress();
                        getLog().info("Tx hash :" + txhash);
                        getLog().info("Contract address :" + contractAdress);
                    }

                } else {
                    throw new IllegalStateException("Can not unlock address " + address);
                }
            } catch (Exception e) {
                getLog().error(e.getMessage());
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    private TransactionReceipt sendContractTransaction(Web3j web3j, Credentials credentials, String binary)
            throws IOException, InterruptedException {

        RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials);

        EthSendTransaction ethSendTransaction = transactionManager.sendTransaction(
                Contract.GAS_PRICE,
                Contract.GAS_LIMIT,
                null,
                binary,
                BigInteger.ZERO);

        if(ethSendTransaction.hasError()){
            getLog().error("Transaction failed.");
            return null;
        } else {
            String txhash = ethSendTransaction.getTransactionHash();

            getLog().info("Tx hash :" + txhash);

            EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txhash).send();

            while(!ethGetTransactionReceipt.getTransactionReceipt().isPresent()){
                Thread.sleep(500);
                getLog().debug("Wait for " + txhash + " to complete.");
                ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(txhash).send();
            }

            TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();
            if(transactionReceipt == null){
                getLog().error("Transaction failed, without receipt.");
                return null;
            } else {
                return transactionReceipt;
            }
        }
    }

    protected Map<String, Bin> computeBinsForSols(FileSet defaultBinsFileSet, String basePath) {
        Map<String, Bin> binsForSols = new HashMap<>();
        if (defaultBinsFileSet != null) {
            FileSetManager fileSetManager = new FileSetManager();
            String[] includedFiles = fileSetManager.getIncludedFiles(defaultBinsFileSet);
            for (String include : includedFiles) {
                String file = basePath
                        + File.separator + include;
                getLog().debug("Included file : " + file);
                String solFile = extractSolFile(file);
                DeployMojo.Bin bin = binsForSols.get(solFile);
                if (bin == null) {
                    bin = new DeployMojo.Bin();
                    binsForSols.put(solFile, bin);
                }
                if (file.endsWith(".bin")) {
                    bin.path = file;
                }
            }
        }
        return binsForSols;
    }

    protected BigInteger getNonce(Ethereum parity, String address) throws Exception {

        EthGetTransactionCount ethGetTransactionCount = parity.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();
        return ethGetTransactionCount.getTransactionCount();
    }


    protected String load(String filePath) throws URISyntaxException, IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        return new String(bytes);
    }
}
