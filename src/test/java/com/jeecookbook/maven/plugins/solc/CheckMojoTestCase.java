/*-
 * -\-\-
 * solc-maven-plugin
 * --
 * Copyright (C) 2017 jeecookbook.blogger.com
 * --
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
 * -/-/-
 */

package com.jeecookbook.maven.plugins.solc;

import com.jeecookbook.maven.plugins.solc.bridge.CompilerBridge;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckMojoTestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPerformPerfectCase() throws MojoExecutionException {

        CheckMojo checkMojo = new CheckMojo();

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                "Version: 0.4.11+commit.68ef5810.Linux.g++",
                null);

        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);

        checkMojo.setCompilerVersion("0.4.11");
        checkMojo.setCompilerCmdPath("solc");

        boolean result = checkMojo.performCheck();
        assertTrue(result);
        assertEquals("solc --version",((FakeCompilerBridge)checkMojo.compilerBridge).cmds.get(0));
    }

    @Test
    public void testPerformOnNullOutput() throws MojoExecutionException {

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                null,
                null);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.setCompilerVersion(UUID.randomUUID().toString());
        checkMojo.setCompilerCmdPath("solc");

        boolean result = checkMojo.performCheck();
        assertFalse(result);
    }

    @Test
    public void testPerformOnBadVersion() throws MojoExecutionException {

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                "dummy",
                null);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.setCompilerVersion(UUID.randomUUID().toString());
        checkMojo.setCompilerCmdPath("solc");

        boolean result = checkMojo.performCheck();
        assertFalse(result);
    }

    @Test
    public void testPerformOnUnknownCmd() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                -1,
                false,
                null,
                new RuntimeException("Unknown command"));

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
        checkMojo.performCheck();
    }


    @Test
    public void testPerformInErrorWithoutException() throws MojoExecutionException {

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                -1,
                false,
                null,
                null);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.setCompilerCmdPath(UUID.randomUUID().toString());
        boolean result = checkMojo.performCheck();
        assertFalse(result);
    }

    @Test
    public void testExecuteWithoutException() throws MojoExecutionException {

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                "version");

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerVersion("version");
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.execute();
    }

    @Test
    public void testExecuteWithException() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                -1,
                false,
                null,
                new RuntimeException());


        CheckMojo checkMojo = new CheckMojo();
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.execute();
    }


    @Test
    public void testExecuteWithMismatch() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                UUID.randomUUID().toString(),
                null);

        CheckMojo checkMojo = new CheckMojo();
        checkMojo.setCompilerVersion(UUID.randomUUID().toString());
        checkMojo.compilerBridge = new FakeCompilerBridge(compilerResult);
        checkMojo.execute();
    }
}