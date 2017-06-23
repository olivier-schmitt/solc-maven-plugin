package com.jeecookbook.maven.plugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
                StringBuffer sb = new StringBuffer();
                while((line = bufferedReader.readLine())!=null){
                    sb.append(line).append("\n");
                }
                compilerResult.setOutput(sb.toString());
                compilerResult.setSuccess(true);
                return compilerResult;
            } else {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while((line = bufferedReader.readLine())!=null){
                    sb.append(line).append("\n");
                }
                compilerResult.setOutput(sb.toString());
                compilerResult.setSuccess(false);
                return compilerResult;
            }
        } catch (Throwable throwable) {
            compilerResult.setSuccess(false);
            compilerResult.setThrowable(throwable);
            return compilerResult;
        }

    }
}
