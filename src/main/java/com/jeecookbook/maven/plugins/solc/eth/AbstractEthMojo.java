package com.jeecookbook.maven.plugins.solc.eth;

import com.jeecookbook.maven.plugins.solc.eth.model.Account;
import com.jeecookbook.maven.plugins.solc.eth.model.Wallet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.model.fileset.FileSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEthMojo extends AbstractMojo {

    @Parameter
    private FileSet[] sources;

    @Parameter(defaultValue = "${project.build.directory}")
    private File outputDirectory;

    @Parameter(defaultValue = "http://localhost:8545")
    private String web3Endpoint;

    @Parameter
    private Account account = new Account();

    @Parameter
    private Wallet wallet = new Wallet();

    public FileSet[] getSources() {
        return sources;
    }

    public void setSources(FileSet[] sources) {
        this.sources = sources;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getWeb3Endpoint() {
        return web3Endpoint;
    }

    public void setWeb3Endpoint(String web3Endpoint) {
        this.web3Endpoint = web3Endpoint;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    protected FileSet createDefaultBinsFileSet() {
        FileSet solcFileSet = new FileSet();
        solcFileSet.setDirectory(getOutputDirectory()+"/solc");
        List<String> solcBins = new ArrayList<String>();
        solcBins.add("**/*.abi");
        solcBins.add("**/*.bin");
        solcFileSet.setIncludes(solcBins);
        return solcFileSet;
    }

    protected String extractSolFile(String file) {
        int index = file.lastIndexOf(".");
        return file.substring(0,index);
    }
}
