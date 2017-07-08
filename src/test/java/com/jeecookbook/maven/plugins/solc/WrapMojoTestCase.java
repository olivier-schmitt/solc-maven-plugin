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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.model.fileset.FileSet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WrapMojoTestCase {

    public static final String SRC_TEST_RESOURCES_SOLC = "src/test/resources/solc";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private String currentPath;

    @Test
    public void testBuildDefaultCmd() throws MojoExecutionException {

        WrapMojo wrapMojo = new WrapMojo();
        File outputFile = new File("target/test");
        outputFile.mkdirs();
        FileSet defaultFileSet = createDefaultBinsFileSet();
        Map<String, WrapMojo.BinAndAbi> result = wrapMojo.computeBinsForSols(
                defaultFileSet,
                defaultFileSet.getDirectory(),
                outputFile.getAbsolutePath());
        assertNotNull(result);
        String key = result.keySet().iterator().next();
        WrapMojo.BinAndAbi binAndAbi = result.get(key);
        assertNotNull(binAndAbi);
        assertTrue(binAndAbi.getBin().endsWith("/SimpleStorage.bin") );
        assertTrue(binAndAbi.getAbi().endsWith("/SimpleStorage.abi") );

    }

    @Test
    public void testGenerateWrapperWithNullPkg() throws MojoExecutionException {

        thrown.expect(MojoExecutionException.class);

        WrapMojo wrapMojo = new WrapMojo();

        File outputFile = new File("target/test");
        outputFile.mkdirs();

        WrapMojo.BinAndAbi binAndAbi = new WrapMojo.BinAndAbi(
                "./SimpleStorage.bin",
                "./SimpleStorage.abi");

        wrapMojo.generateWrapper(outputFile.getAbsolutePath(),binAndAbi);
    }

    @Test
    public void testGenerateWrapperWithPkg() throws MojoExecutionException {
        WrapMojo wrapMojo = new WrapMojo();
        File outputFile = new File("target/test");
        outputFile.mkdirs();
        FileSet defaultFileSet = createDefaultBinsFileSet();
        Map<String, WrapMojo.BinAndAbi> result = wrapMojo.computeBinsForSols(
                defaultFileSet,
                defaultFileSet.getDirectory(),
                outputFile.getAbsolutePath());
        assertNotNull(result);
        String key = result.keySet().iterator().next();
        WrapMojo.BinAndAbi binAndAbi = result.get(key);

        wrapMojo.setTargetPackage("test");

        boolean success = wrapMojo.generateWrapper(outputFile.getAbsolutePath(),binAndAbi);
        assertFalse(success);

        File wrappedSol = new File(outputFile,"test/SimpleStorage.java");
        assertTrue(wrappedSol.exists());
        wrappedSol.deleteOnExit();
    }

    @Test
    public void testExecute() throws MojoExecutionException {
        WrapMojo wrapMojo = new WrapMojo();
        File outputFile = new File("target/test");
        outputFile.mkdirs();
        wrapMojo.setOutputDirectory(new File(getCurrentPath() + File.separator + "src/test/resources"));
        wrapMojo.setTargetPackage("test");
        wrapMojo.setWrapOutputDirectory(outputFile.getAbsolutePath());
        wrapMojo.execute();

        File wrappedSol = new File(outputFile,"test/SimpleStorage.java");
        assertTrue(wrappedSol.exists());
        wrappedSol.deleteOnExit();
    }


    protected static FileSet createDefaultBinsFileSet() {
        FileSet solcFileSet = new FileSet();
        solcFileSet.setDirectory(SRC_TEST_RESOURCES_SOLC);
        List<String> solcBins = new ArrayList<String>();
        solcBins.add("**/*.abi");
        solcBins.add("**/*.bin");
        solcFileSet.setIncludes(solcBins);
        return solcFileSet;
    }

    public String getCurrentPath() {
        return new File(".").getAbsolutePath();
    }
}
