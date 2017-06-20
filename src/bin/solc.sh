#!/usr/bin/env bash

DIR=`pwd`

docker run --rm -v ${DIR}:/solidity ethereum/solc-dev:0.4.11 $*