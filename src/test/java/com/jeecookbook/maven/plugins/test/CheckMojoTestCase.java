package com.jeecookbook.maven.plugins.test;

import junit.framework.TestCase;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;
import com.jeecookbook.maven.plugins.CheckMojo;

import java.util.UUID;

public class CheckMojoTestCase extends TestCase {

    @Test
    public void testVersionPerformOK() throws MojoExecutionException {
        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerVersion("0.4.11");
        checkMojo.setCompilerCmdPath("solc");
        boolean result = checkMojo.performCheck();
        assertTrue(result);
    }

    @Test
    public void testVersionPerformOnBadVersionKO() throws MojoExecutionException {

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerVersion(UUID.randomUUID().toString());
        checkMojo.setCompilerCmdPath("solc");
        checkMojo.performCheck();
        boolean result = checkMojo.performCheck();
        assertFalse(result);
    }

    @Test
    public void testVersionPerformOnBadCmdKO() {
        try {
            CheckMojo checkMojo = new CheckMojo();
            checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
            checkMojo.performCheck();
        } catch (MojoExecutionException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testVersionKO() {
        try {
            CheckMojo checkMojo = new CheckMojo();
            checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
            checkMojo.execute();
        } catch (MojoExecutionException e) {
            e.printStackTrace();
        }
    }
}
