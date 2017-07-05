# Docker Client

[![Build Status](https://travis-ci.org/spotify/docker-client.svg?branch=master)](https://travis-ci.org/spotify/docker-client)
[![codecov](https://codecov.io/github/spotify/docker-client/coverage.svg?branch=master)](https://codecov.io/github/spotify/docker-client?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.spotify/docker-client.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.spotify%22%20docker-client)
[![License](https://img.shields.io/github/license/spotify/docker-client.svg)](LICENSE)

This is a [Solc](https://github.com/ethereum/solc) Maven plugin written in Java.
It is used it in many critical production systems at Spotify.

* [Version compatibility](#version-compatibility)
* [Example](#exemple)

## Version compatibility
solc-maven-plugin is built and tested against the most recent releases of Solc.
Right now these are 0.4.11 - .

## Example


This configures the actual plugin to build your image with `mvn
solc:check` and push it with `mvn deploy`.  Of course you can also say
`mvn dockerfile:build` explicitly.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.jeecookbook</groupId>
                <artifactId>solc-maven-plugin</artifactId>
                <version>${solc.plugin.version}</version>
                <configuration>
                    <wrapOutputDirectory>src/main/java</wrapOutputDirectory>
                    <compilerVersion>${solc.compiler.version}</compilerVersion>
                    <overwrite>true</overwrite>
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

## What does it give me?

There are many advantages to using this plugin for your builds.

