package com.jeecookbook.maven.plugins.solc.it;


import com.jeecookbook.maven.plugins.solc.bridge.CompilerBridge;
import com.jeecookbook.maven.plugins.solc.bridge.CompilerBridgeImpl;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.UUID;

import static junit.framework.TestCase.*;


public class CompileBridgeImplTestCaseIT {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void badCmdTest(){

        CompilerBridgeImpl compilerBridgeImpl = new CompilerBridgeImpl();
        CompilerBridge.CompilerResult compilerResult = compilerBridgeImpl.executeCmd(UUID.randomUUID().toString());

        assertFalse(compilerResult.isSuccess());
        assertEquals(1,compilerResult.getStatus());
        Throwable t = compilerResult.getThrowable();
        assertNotNull(t);
        assertEquals(t.getClass(),IOException.class);

    }

    @Test
    public void versionCmdTest(){

        CompilerBridgeImpl compilerBridgeImpl = new CompilerBridgeImpl();
        CompilerBridge.CompilerResult compilerResult = compilerBridgeImpl.executeCmd("solc --version");

        Throwable t = compilerResult.getThrowable();
        assertNull(t);
        assertTrue(compilerResult.isSuccess());
        assertEquals(0,compilerResult.getStatus());
        assertTrue(compilerResult.getOutput().contains("solc, the solidity compiler commandline interface"));
    }


    @Test
    public void badOptionCmdTest(){

        CompilerBridgeImpl compilerBridgeImpl = new CompilerBridgeImpl();
        CompilerBridge.CompilerResult compilerResult = compilerBridgeImpl.executeCmd("solc --"+UUID.randomUUID().toString());

        Throwable t = compilerResult.getThrowable();
        assertNull(t);
        assertFalse(compilerResult.isSuccess());
        assertEquals(1,compilerResult.getStatus());
    }
}
