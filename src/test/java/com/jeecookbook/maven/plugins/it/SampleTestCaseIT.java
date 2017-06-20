package com.jeecookbook.maven.plugins.it;

import junit.framework.TestCase;
import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SampleTestCaseIT extends TestCase {


    public void testMyPlugin() throws Exception {

        // Check in your dummy Maven project in /src/test/resources/...
        // The testdir is computed from the location of this
        // file.
        File testDir = ResourceExtractor
                .simpleExtractResources(getClass(), "/solc-project");

        Verifier verifier;

        verifier = new Verifier(testDir.getAbsolutePath());

        List cliOptions = new ArrayList();

        verifier.executeGoal("solc:check");

        verifier.verifyErrorFreeLog();
        verifier.verifyTextInLog("solc, the solidity compiler commandline interface");

        List<String> lines = getStrings(verifier);

        verifier.resetStreams();

        /*
         * The verifier also supports beanshell scripts for
         * verification of more complex scenarios. There are
         * plenty of examples in the core-it tests here:
         * https://svn.apache.org/repos/asf/maven/core-integration-testing/trunk
         */
    }

    private List<String> getStrings(Verifier verifier) throws VerificationException {
        String logFilename = verifier.getLogFileName();
        String baseDir = verifier.getBasedir();

        return verifier.loadFile(new File(baseDir,logFilename),true);
    }
}