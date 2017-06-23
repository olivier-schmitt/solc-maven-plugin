package com.jeecookbook.maven.plugins.it;

import junit.framework.TestCase;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Rule;
import org.junit.Test;
import com.jeecookbook.maven.plugins.CheckMojo;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckMojoTestCaseIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void testVersionPerformOnBadCmdKO() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
        checkMojo.performCheck();
    }


    @Test
    public void testVersionKO() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
        checkMojo.execute();
    }
}
