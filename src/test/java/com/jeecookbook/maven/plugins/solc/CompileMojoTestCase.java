/*-
 * -\-\-
 * solc-maven-plugin
 * --
 * Copyright (C) 2017 Olivier Schmitt
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
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CompileMojoTestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBuildDefaultCmd() throws MojoExecutionException {

        CompileMojo compileMojo = new CompileMojo();
        String cmd = compileMojo.buildCmd("target");

        assertNotNull(cmd);
        assertEquals(cmd,"null -o target/solc ");
    }


    @Test
    public void testBuildCmd() throws MojoExecutionException {

        CompileMojo compileMojo = new CompileMojo();
        compileMojo.setCompilerCmdPath("solc");

        String cmd = compileMojo.buildCmd("target");
        assertNotNull(cmd);
        assertEquals(cmd,"solc -o target/solc ");

        compileMojo.setAbi(true);
        compileMojo.setAsm(true);
        compileMojo.setAst(true);
        compileMojo.setAstjson(true);
        compileMojo.setBin(true);
        compileMojo.setAsmjson(true);
        compileMojo.setFormal(true);
        compileMojo.setHashes(true);

        cmd = compileMojo.buildCmd("target");
        assertNotNull(cmd);
        assertEquals(cmd,"solc -o target/solc --abi --asm --asm-json --ast --ast-json --bin --formal --hashes ");
    }

    @Test
    public void testProcessSourceSet() throws MojoExecutionException {

        CompileMojo compileMojo = new CompileMojo();
        compileMojo.setCompilerCmdPath("solc");

        CompilerBridge.CompilerResult result = new CompilerBridge.CompilerResult();
        result.setThrowable(null);
        result.setStatus(0);
        result.setSuccess(true);
        result.setOutput("");

        FakeCompilerBridge fakeCompilerBridge = new FakeCompilerBridge(result);

        compileMojo.compilerBridge = fakeCompilerBridge;

        FileSet basicSourceSet = new FileSet();
        basicSourceSet.setDirectory("src/test/resources/solc-project/src/contracts/basic");
        basicSourceSet.addInclude("**/*.sol");

        compileMojo.processSourceSet(new FileSetManager(),basicSourceSet);
        String cmd = fakeCompilerBridge.cmds.get(0);
        assertNotNull(cmd);
        assertTrue(cmd.contains("SimpleStorage.sol"));
    }

    @Test
    public void testProcessAllSourceSet() throws MojoExecutionException {

        CompileMojo compileMojo = new CompileMojo();
        compileMojo.setCompilerCmdPath("solc");

        CompilerBridge.CompilerResult compilerResult = FakeCompilerBridge.build(
                0,
                true,
                "",
                null);


        FakeCompilerBridge fakeCompilerBridge = new FakeCompilerBridge(compilerResult);

        compileMojo.compilerBridge = fakeCompilerBridge;

        FileSet basicSourceSet = new FileSet();
        basicSourceSet.setDirectory("src/test/resources/solc-project/src/contracts/basic");
        basicSourceSet.addInclude("**/*.sol");

        FileSet soliditySourceSet = new FileSet();
        soliditySourceSet.setDirectory("src/test/resources/solc-project/src/contracts/solidity");
        soliditySourceSet.addInclude("**/*.sol");

        compileMojo.setSources(new FileSet[]{basicSourceSet,soliditySourceSet});
        compileMojo.processSources();


        String basicCmd = fakeCompilerBridge.cmds.get(0);
        assertNotNull(basicCmd);
        assertTrue(basicCmd.contains("SimpleStorage.sol"));
        String solidityCmd = fakeCompilerBridge.cmds.get(1);
        assertNotNull(solidityCmd);

        assertTrue(solidityCmd.contains("Purchase.sol"));
        assertTrue(solidityCmd.contains("SimpleAuction.sol"));
        assertTrue(solidityCmd.contains("BlindAuction.sol"));
        assertTrue(solidityCmd.contains("Ballot.sol"));
    }
}
