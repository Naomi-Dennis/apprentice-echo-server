#!/bin/bash

project_path="src/main/java"
manifest_file="manifest.txt"
manifest_file_path="${project_path}/${manifest_file}"
package="echoserver"
package_path="${project_path}/${package}"

compile (){

  eval "javac ${package_path}/*.java"
  echo .............Complimation complete!
}

create_manifest_file (){
	eval "echo -e 'Main-Class: echoserver.EchoServerProgram\nClass-Path: ${project_path}/' > ${manifest_file_path}"
	echo ..............Manifest file created at ${manifest_file_path}!
}

create_jar_file (){
	echo "..............Creating Jar................."
	eval "jar cfmv EchoServer.jar ${manifest_file_path} ${package_path}/EchoServerProgram.class ${project_path}/echoserver"
        echo "..............Jar file created in current directory"
}

compile
create_manifest_file
create_jar_file

