package com.jeecookbook.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.model.fileset.FileSet;

import java.io.File;

public abstract class AbstractSolcMojo extends AbstractMojo {

    @Parameter(defaultValue = "solc")
    private String compilerCmdPath;

    @Parameter(defaultValue = "0.4.11")
    private String compilerVersion;

    @Parameter(defaultValue = "true")
    private Boolean overwrite;

    @Parameter
    private Boolean abi;

    @Parameter
    private Boolean ast;

    @Parameter
    private Boolean astjson;

    @Parameter
    private Boolean asm;

    @Parameter
    private Boolean asmjson;

    @Parameter
    private Boolean bin;

    @Parameter
    private Boolean formal;

    @Parameter
    private Boolean hashes;

    @Parameter
    private FileSet fileset;

    @Parameter(defaultValue = "${project.build.directory}")
    private File outputDirectory;



    public FileSet getFileset() {
        return fileset;
    }

    public void setFileset(FileSet fileset) {
        this.fileset = fileset;
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
}
