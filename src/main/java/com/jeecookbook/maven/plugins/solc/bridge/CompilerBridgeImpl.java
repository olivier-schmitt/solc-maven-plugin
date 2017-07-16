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

package com.jeecookbook.maven.plugins.solc.bridge;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Default implementation of a CompilerBridge.
 * It uses java.lang.Process class to interact with the system.
 *
 */
public class CompilerBridgeImpl implements CompilerBridge {

    public CompilerResult executeCmd(String cmd) {
        CompilerResult compilerResult = new CompilerResult();
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            int status = process.waitFor();
            compilerResult.setStatus(status);
            if(status == 0){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while((line = bufferedReader.readLine())!=null){
                    sb.append(line).append("\n");
                }
                compilerResult.setOutput(sb.toString());
                compilerResult.setSuccess(true);
                return compilerResult;
            } else {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while((line = bufferedReader.readLine())!=null){
                    sb.append(line).append("\n");
                }
                compilerResult.setOutput(sb.toString());
                compilerResult.setSuccess(false);
                return compilerResult;
            }
        } catch (Exception exception) {
            compilerResult.setSuccess(false);
            compilerResult.setThrowable(exception);
            return compilerResult;
        }
    }
}
