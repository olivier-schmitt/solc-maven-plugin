# Solc Maven Plugin
[![Travis CI](https://travis-ci.org/olivier-schmitt/solc-maven-plugin.svg?branch=master)](https://travis-ci.org/olivier-schmitt/solc-maven-plugin/) 
[![License](https://img.shields.io/github/license/spotify/docker-client.svg)](LICENSE)

This is a [Solc](https://github.com/ethereum/solc) Maven plugin written in Java.

Check the wiki to understand its [design principles](https://github.com/olivier-schmitt/solc-maven-plugin/wiki).

**WARNING: the plugin is not deployed on Maven Central yet. You have to build it from sources.**

**WARNING: The Solc Maven Plugin might merge with the Web3J's Maven plugin (check Design principles).**

The plugin supports the following goals : 

* **help**: display help 
* **check**: check the Solc's version declared in your POM against the Solc version running on your system
* **compile**: compile Solidity file into what Solc compiler is capable of (abi, bin, ...)
* **wrap**: generate Java wrapper by leveraging web3j library (needs abi and bin files compiled from your contracts)

## Version compatibility

solc-maven-plugin is built and tested against the most recent releases of Solc.
Right now these are 0.4.11 - ?.

The plugins uses web3j 2.2.2 for Java wrappers generation.

## Example

This configures the actual plugin:
* to check 0.4.1 version against your actual Solc compiler's version installed on your OS
* to build your solidity contracts 
* to generate Java wrappers into src/main/java directory

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.jeecookbook</groupId>
                <artifactId>solc-maven-plugin</artifactId>
                <version>${solc.plugin.version}</version>
                <configuration>
                    <wrapOutputDirectory>src/main/java</wrapOutputDirectory>
                    <compilerVersion>0.4.1</compilerVersion>
                    <overwrite>true</overwrite>
                    <executions>
                        <execution>
                            <phase>process-resources</phase>
                            <goals>
                                <goal>check</goal>                     
                                <goal>compile</goal>
                                <goal>wrap</goal>
                            </goals>
                        </execution>
                    </executions>
                    <sources>
                        <fileset>
                            <directory>src/contracts/basic</directory>
                            <includes>
                                <include>**/*.sol</include>
                            </includes>
                        </fileset>
                    </sources>
                </configuration>
            </plugin>
        </plugins>
      </build>
```
