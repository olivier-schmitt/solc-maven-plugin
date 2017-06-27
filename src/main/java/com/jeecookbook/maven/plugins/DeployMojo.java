package com.jeecookbook.maven.plugins;

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
import org.apache.maven.plugins.annotations.Mojo;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Goal which deploy contracts.
 *
 */
@Mojo( name = "deploy")
public class DeployMojo extends AbstractSolcMojo {

    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering deploy mojo.");
        } finally {
            getLog().debug( "Exiting check mojo." );
        }
    }


    protected BigInteger getNonce(Ethereum parity,String address) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = parity.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }


    protected String load(String filePath) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(url.toURI()));
        return new String(bytes);
    }

    private String sendCreateContractTransaction(
            Ethereum parity,
            String address,
            BigInteger gasPrice,
            BigInteger gasLimit,
            String binary) throws Exception {

        BigInteger nonce = getNonce(parity,address);

        String encodedConstructor =
                FunctionEncoder.encodeConstructor(Collections.singletonList(new Utf8String("")));

        Transaction transaction = Transaction.createContractTransaction(
                address,
                nonce,
                gasPrice,
                gasLimit,
                BigInteger.ZERO,
                binary + encodedConstructor);

        org.web3j.protocol.core.methods.response.EthSendTransaction
                transactionResponse = parity.ethSendTransaction(transaction)
                .sendAsync().get();

        return transactionResponse.getTransactionHash();
    }


}
