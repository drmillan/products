#!/bin/bash -l
docker build -t products .
docker run --rm products