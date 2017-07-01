package com.jeecookbook.maven.plugins.solc;

import com.jeecookbook.maven.plugins.solc.CompileMojo;
import com.jeecookbook.maven.plugins.solc.CompilerBridge;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
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

    class FakeCompilerBridge implements CompilerBridge {

        CompilerResult compilerResult;
        List<String> cmds = new ArrayList<String>();

        public FakeCompilerBridge(CompilerResult compilerResult){
            this.compilerResult = compilerResult;
        }

        public CompilerBridge.CompilerResult executeCmd(String cmd) {
            this.cmds.add(cmd);
            return this.compilerResult;
        }
    };


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
