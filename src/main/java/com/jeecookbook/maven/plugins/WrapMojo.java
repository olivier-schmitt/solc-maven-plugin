package com.jeecookbook.maven.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
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
 */

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Goal which compile SOL files.
 *
 */
@Mojo( name = "wrap")
public class WrapMojo extends AbstractSolcMojo {


    class BinAndAbi {
        private String bin;
        private String abi;
    }

    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering wrapping mojo.");
            getLog().debug("Overwrite :" + getOverwrite());
            FileSet defaultBinsFileSet = createDefaultBinsFileSet();
            String basePath = defaultBinsFileSet.getDirectory();
            String outputDir = getWrapOutputDirectory();
            Map<String,BinAndAbi> binsForSols = computeBinsForSols(defaultBinsFileSet, basePath, outputDir);
            boolean hasErrors = false;
            for(String solFile : binsForSols.keySet()){
                BinAndAbi binAndAbi = binsForSols.get(solFile);
                hasErrors = generateWrapper(outputDir, binAndAbi);
            }
            if(hasErrors){
                throw new MojoExecutionException("Wrapper failed.");
            }

        } finally {
            getLog().debug( "Exiting wrapping mojo." );
        }
    }

    protected boolean generateWrapper(String outputDir, BinAndAbi binAndAbi) {
        boolean hasErrors = false;
        getLog().debug("Generate wrapper for : " + binAndAbi.abi + ", " + binAndAbi.bin );
        if(binAndAbi != null) {
            try {
                SolidityFunctionWrapperGenerator.main(
                        new String[]{
                                binAndAbi.bin,
                                binAndAbi.abi,
                                "-o",
                                outputDir,
                                "-p",
                                getTargetPackage()
                        }
                );
            } catch (Exception e) {
                getLog().error(e.getMessage());
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    protected Map<String,BinAndAbi>  computeBinsForSols(FileSet defaultBinsFileSet, String basePath, String outputDir) {
        Map<String,BinAndAbi> binsForSols = new HashMap<String,BinAndAbi>();
        if(defaultBinsFileSet != null) {
            FileSetManager fileSetManager = new FileSetManager();
            String[] includedFiles = fileSetManager.getIncludedFiles( defaultBinsFileSet );
            new File(outputDir).mkdirs();
            for(String include:includedFiles){
                String file = basePath
                        + File.separator + include;
                getLog().debug("Included file : " + file);
                String solFile = extractSolFile(file);
                BinAndAbi binAndAbi = binsForSols.get(solFile);
                if(binAndAbi == null){
                    binAndAbi = new BinAndAbi();
                    binsForSols.put(solFile,binAndAbi);
                }
                if(file.endsWith(".bin")){
                    binAndAbi.bin = file;
                }
                else if(file.endsWith(".abi")){
                    binAndAbi.abi = file;
                }
            }
        }
        return binsForSols;
    }

    protected FileSet createDefaultBinsFileSet() {
        FileSet solcFileSet = new FileSet();
        solcFileSet.setDirectory("target/solc");
        List<String> solcBins = new ArrayList<String>();
        solcBins.add("**/*.abi");
        solcBins.add("**/*.bin");
        solcFileSet.setIncludes(solcBins);
        return solcFileSet;
    }

    protected String extractSolFile(String file) {
        int index = file.lastIndexOf(".");
        return file.substring(0,index);
    }


}