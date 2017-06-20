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

/**
 * Goal which compile SOL files.
 *
 */
@Mojo( name = "compile")
public class CompileMojo extends AbstractSolcMojo {


    public void execute() throws MojoExecutionException {
        try {
            getLog().debug("Entering compile mojo.");
            getLog().debug("Overwrite :" + getOverwrite());
            FileSet fileset = getFileset();
            if(fileset != null) {
                String[] includes = fileset.getIncludesArray();
                for(String include:includes){
                    getLog().debug(include);
                }

            }

        } finally {
            getLog().debug( "Exiting compile mojo." );
        }
    }


    private String buildCmdVersion() {
        return getCompilerCmdPath() + " " + "--version";
    }
}
