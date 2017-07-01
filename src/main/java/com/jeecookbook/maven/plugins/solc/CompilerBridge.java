package com.jeecookbook.maven.plugins.solc;

import org.apache.maven.plugin.logging.Log;

public interface CompilerBridge {

    CompilerResult executeCmd(String cmd);

    class CompilerResult {

        int status;
        boolean success;
        String output;
        Throwable throwable;

        public Throwable getThrowable() {
            return throwable;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }
    }

}
