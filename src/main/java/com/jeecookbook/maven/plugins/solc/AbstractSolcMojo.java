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
import com.jeecookbook.maven.plugins.solc.bridge.CompilerBridgeImpl;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.model.fileset.FileSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractSolcMojo extends AbstractMojo {

    public static final String SOLC_OUTPUT = File.separator + "solc";
    protected CompilerBridge compilerBridge = new CompilerBridgeImpl();

    @Parameter(defaultValue = "solc")
    private String compilerCmdPath;

    @Parameter(defaultValue = "0.4.11")
    private String compilerVersion;

    @Parameter(defaultValue = "true")
    private Boolean overwrite;

    @Parameter(defaultValue = "true")
    private Boolean abi;

    @Parameter
    private Boolean ast;

    @Parameter
    private Boolean astjson;

    @Parameter
    private Boolean asm;

    @Parameter
    private Boolean asmjson;

    @Parameter(defaultValue = "true")
    private Boolean bin;

    @Parameter
    private Boolean formal;

    @Parameter
    private Boolean hashes;

    @Parameter
    private FileSet[] sources;

    @Parameter(defaultValue = "${project.build.directory}")
    private File outputDirectory;

    @Parameter(defaultValue = "http://localhost:8545")
    private String web3Endpoint;


    @Parameter
    private String wrapOutputDirectory;
    
    @Parameter(defaultValue = "${project.groupId}")
    private String targetPackage;

    public void setWrapOutputDirectory(String wrapOutputDirectory) {
        this.wrapOutputDirectory = wrapOutputDirectory;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getWeb3Endpoint() {
        return web3Endpoint;
    }


    public void setWeb3Endpoint(String web3Endpoint) {
        this.web3Endpoint = web3Endpoint;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public String getWrapOutputDirectory() {
        return wrapOutputDirectory;
    }

    public FileSet[] getSources() {
        return sources;
    }

    public void setSources(FileSet[] sources) {
        this.sources = sources;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public Boolean getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        this.overwrite = overwrite;
    }

    public Boolean getAbi() {
        return abi;
    }

    public void setAbi(Boolean abi) {
        this.abi = abi;
    }

    public Boolean getAst() {
        return ast;
    }

    public void setAst(Boolean ast) {
        this.ast = ast;
    }

    public Boolean getAstjson() {
        return astjson;
    }

    public void setAstjson(Boolean astjson) {
        this.astjson = astjson;
    }

    public Boolean getAsm() {
        return asm;
    }

    public void setAsm(Boolean asm) {
        this.asm = asm;
    }

    public Boolean getAsmjson() {
        return asmjson;
    }

    public void setAsmjson(Boolean asmjson) {
        this.asmjson = asmjson;
    }

    public Boolean getBin() {
        return bin;
    }

    public void setBin(Boolean bin) {
        this.bin = bin;
    }

    public Boolean getFormal() {
        return formal;
    }

    public void setFormal(Boolean formal) {
        this.formal = formal;
    }

    public Boolean getHashes() {
        return hashes;
    }

    public void setHashes(Boolean hashes) {
        this.hashes = hashes;
    }

    public String getCompilerCmdPath() {
        return compilerCmdPath;
    }

    public String getCompilerVersion() {
        return compilerVersion;
    }

    public void setCompilerVersion(String compilerVersion) {
        this.compilerVersion = compilerVersion;
    }

    public void setCompilerCmdPath(String compilerCmdPath) {
        this.compilerCmdPath = compilerCmdPath;
    }

    protected FileSet createDefaultBinsFileSet() {
        FileSet solcFileSet = new FileSet();
        solcFileSet.setDirectory(getOutputDirectory()+ SOLC_OUTPUT);
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
