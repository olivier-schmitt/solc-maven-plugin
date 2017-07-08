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

import com.jeecookbook.maven.plugins.solc.bridge.CompilerBridge;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;

import java.io.File;

/**
 * Goal which compile SOL files.
 *
 */
@Mojo( name = "compile")
public class CompileMojo extends AbstractSolcMojo {

    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering compile mojo.");
            processSources();

        } finally {
            getLog().debug( "Exiting compile mojo." );
        }
    }


    protected void processSources() throws MojoExecutionException {
        FileSet[] sources = getSources();
        if(sources != null) {
            FileSetManager fileSetManager = new FileSetManager();
            for(FileSet sourceSet:sources){
                processSourceSet(fileSetManager, sourceSet);
            }
        } else {
            getLog().info("Nothing to compile.");
        }
    }

    protected void processSourceSet(FileSetManager fileSetManager, FileSet sourceSet)
            throws MojoExecutionException {

        String basePath = sourceSet.getDirectory();
        String[] includedFiles = fileSetManager.getIncludedFiles( sourceSet );

        String cmd = buildCmd("target");

        StringBuilder cmdBuilder = new StringBuilder(cmd);
        cmdBuilder.append(" ");

        for(String include:includedFiles){
            cmdBuilder
                    .append( basePath)
                    .append(File.separator)
                    .append(include)
                    .append(" ");

            getLog().debug("Included file :" + basePath + File.separator + include);
        }
        String cmdExec = cmdBuilder.toString();

        getLog().debug(" Cmd is " + cmdExec);

        CompilerBridge.CompilerResult compilerResult = compilerBridge.executeCmd(cmdExec);
        if(compilerResult.isSuccess()){
            getLog().info(compilerResult.getOutput());
        } else {
            if(compilerResult.getThrowable() != null){
                getLog().error(compilerResult.getThrowable());
            }
            getLog().error(compilerResult.getOutput());
            getLog().error("Compiler exit with status " +compilerResult.getStatus());
        }
    }

    public static String getSupportedOptions(){
        StringBuilder options = new StringBuilder();
        options
                .append("-o ")
                .append("--overwrite ")
                .append("--abi ")
                .append("--asm-json ")
                .append("--ast ")
                .append("--ast-json ")
                .append("--bin ")
                .append("--formal ")
                .append("--hashes ");
        return options.toString();
    }

    protected String buildCmd(String basePath) {

        StringBuilder cmBuilder = new StringBuilder();

        cmBuilder
                .append(getCompilerCmdPath())
                .append(" ")
                ;

        String outputDir = basePath + File.separator + "solc";

        new File(outputDir).mkdirs();

        cmBuilder.append("-o ")
                .append(outputDir)
                .append(" ");

        if(Boolean.TRUE.equals(getOverwrite())){
            cmBuilder.append("--overwrite").append(" ");
        }
        if(Boolean.TRUE.equals(getAbi())){
            cmBuilder.append("--abi").append(" ");
        }
        if(Boolean.TRUE.equals(getAsm())){
            cmBuilder.append("--asm").append(" ");
        }
        if(Boolean.TRUE.equals(getAsmjson())){
            cmBuilder.append("--asm-json").append(" ");
        }
        if(Boolean.TRUE.equals(getAst())){
            cmBuilder.append("--ast").append(" ");
        }
        if(Boolean.TRUE.equals(getAstjson())){
            cmBuilder.append("--ast-json").append(" ");
        }
        if(Boolean.TRUE.equals(getBin())){
            cmBuilder.append("--bin").append(" ");
        }
        if(Boolean.TRUE.equals(getFormal())){
            cmBuilder.append("--formal").append(" ");
        }
        if(Boolean.TRUE.equals(getHashes())){
            cmBuilder.append("--hashes").append(" ");
        }
        return cmBuilder.toString();
    }
}
