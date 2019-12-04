#!/bin/bash

eval "cd /opt"
eval "(java -jar EchoService.jar &) && java -jar SimpleApplication.jar &"