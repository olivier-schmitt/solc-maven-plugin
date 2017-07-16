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
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Goal which check SOLC version.
 * If the versions match then the mojo will produce a log info.
 * If not, a MojoException is thrown.
 * The mojo uses the compiler's command path to build the command line, it appends the --version option after it.
 */
@Mojo( name = "check",defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class CheckMojo extends AbstractSolcMojo {

    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering check mojo.");
            getLog().debug("Overwrite :" + getOverwrite());
            boolean healthy = performCheck();
            if (healthy) {
                getLog().info(String.format("Compiler's version is fine (%s)",getCompilerVersion()));
            } else {
                throw new MojoExecutionException(
                        String.format(
                                "Check has failed, %s is required. Check your SOLC command path.",
                                getCompilerVersion())
                        );
            }
        } finally {
            getLog().debug( "Exiting check mojo." );
        }
    }

    public boolean performCheck() throws MojoExecutionException {
        String cmd = buildCmdVersion();
        CompilerBridge.CompilerResult compilerResult = this.compilerBridge.executeCmd(cmd);
        String output = compilerResult.getOutput();
        if(compilerResult.isSuccess()){
            if(output == null){
                return false;
            } else {
                getLog().debug(output);
                return output.contains(this.getCompilerVersion());
            }
        } else {
            if(compilerResult.getThrowable() != null){
                getLog().error(compilerResult.getThrowable());
                String msg = compilerResult.getThrowable().getMessage();
                throw new MojoExecutionException(msg);
            }
            if(output != null){
                getLog().error(output);
            }
            getLog().error(String.format("Compiler exit with status %s",compilerResult.getStatus()));
            return false;
        }
    }

    private String buildCmdVersion() {
        return getCompilerCmdPath() + " " + "--version";
    }
}
