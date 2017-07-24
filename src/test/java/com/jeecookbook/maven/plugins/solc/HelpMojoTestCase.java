package com.jeecookbook.maven.plugins.solc;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

public class HelpMojoTestCase {

    @Test
    public void testBuildDefaultCmd() throws MojoExecutionException, MojoFailureException {
        HelpMojo helpMojo = new HelpMojo();
        helpMojo.execute();
    }
}
