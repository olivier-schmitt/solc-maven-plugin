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

import java.util.ArrayList;
import java.util.List;

public class FakeCompilerBridge implements CompilerBridge {

    CompilerBridge.CompilerResult compilerResult;
    List<String> cmds = new ArrayList<String>();

    public FakeCompilerBridge(CompilerBridge.CompilerResult compilerResult){
        this.compilerResult = compilerResult;
    }

    public CompilerBridge.CompilerResult executeCmd(String cmd) {
        this.cmds.add(cmd);
        return this.compilerResult;
    }

    static CompilerBridge.CompilerResult build(int status, boolean success, String output, Throwable throwable){
        CompilerBridge.CompilerResult result = new CompilerBridge.CompilerResult();
        result.setOutput(output);
        result.setThrowable(throwable);
        result.setSuccess(success);
        result.setStatus(status);
        return result;
    }

    static CompilerBridge.CompilerResult build(int status, boolean success, String output){
        CompilerBridge.CompilerResult result = new CompilerBridge.CompilerResult();
        result.setOutput(output);
        result.setSuccess(success);
        result.setStatus(status);
        return result;
    }
}
