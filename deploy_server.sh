#!/bin/bash

eval "cd /opt"
eval "(java -jar EchoService.jar > echo_log.txt &) && java -jar SimpleApplication.jar > simple_app_log.txt &"