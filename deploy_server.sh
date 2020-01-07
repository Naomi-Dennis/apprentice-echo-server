#!/bin/bash

eval "cd /opt"
eval "(nohup java -jar EchoService.jar &) && (nohup java -jar SimpleApplication.jar &)"