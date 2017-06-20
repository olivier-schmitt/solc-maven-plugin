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

import java.io.*;

/**
 * Goal which check SOLC version.
 *
 */
@Mojo( name = "check")
public class CheckMojo extends AbstractSolcMojo {


    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering check mojo.");
            getLog().debug("Overwrite :" + getOverwrite());
            boolean healthy = performCheck();
            if (healthy) {
                getLog().info("Compiler's version is fine (" + getCompilerVersion() + ")");
            } else {
                throw new MojoExecutionException("Check has failed, " + getCompilerVersion() + " is required. Check your SOLC command path.");
            }
        } finally {
            getLog().debug( "Exiting check mojo." );
        }
    }

    public boolean performCheck() throws MojoExecutionException {

        try {
            Process process = Runtime.getRuntime().exec(buildCmdVersion());
            int status = process.waitFor();
            if(status == 0){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while((line = bufferedReader.readLine())!=null){
                    getLog().info(line);
                    sb.append(line).append("\n");
                }
                return sb.toString().contains(getCompilerVersion());
            } else {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while((line = bufferedReader.readLine())!=null){
                    getLog().error(line);
                    sb.append(line).append("\n");
                }
                return false;
            }
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(),e);
        }

    }

    private String buildCmdVersion() {
        return getCompilerCmdPath() + " " + "--version";
    }
}
