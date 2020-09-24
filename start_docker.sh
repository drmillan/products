#!/bin/bash -l
docker build -t products .
docker run -d --rm products