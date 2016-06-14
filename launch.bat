@ECHO off
@SET max_connections=100
@SET webroot=D:\Website
@SET homepage=index.html
@SET port=8080

java -cp simplewebserver-0.0.1-SNAPSHOT-jar-with-dependencies.jar bpaunescu.simplewebserver.App %max_connections% %webroot% %homepage% %port%