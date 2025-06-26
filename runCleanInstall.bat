@echo off
echo Running clean install...
call mvn clean install -X

start runBackend.bat
timeout /t 5

start runFrontend.bat