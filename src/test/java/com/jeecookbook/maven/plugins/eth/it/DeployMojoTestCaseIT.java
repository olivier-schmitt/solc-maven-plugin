package com.jeecookbook.maven.plugins.eth.it;

import com.jeecookbook.maven.plugins.solc.eth.DeployMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class DeployMojoTestCaseIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testVersionPerformOK() throws MojoExecutionException {

        DeployMojo deployMojo = new DeployMojo();

        deployMojo.getAccount().setPublicKey("0x38d8c1b1382249e1567af808f3f70a022cca733f");
        deployMojo.getAccount().setPassword("password");

        deployMojo.getWallet().setPassword("password");
        deployMojo.getWallet().setPath("/home/olivierschmitt/java/poc/block/chains/keystore/UTC--2017-06-13T09-16-32.804957175Z--38d8c1b1382249e1567af808f3f70a022cca733f");

        deployMojo.setOutputDirectory(new File("src/test/resources"));

        deployMojo.execute();
    }

}
