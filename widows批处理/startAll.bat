@echo off
copy  "D:\PowerShell Server 2016\sftproot\ymfront-center\target\ymfront-center.jar" "D:\ymfront\projectJar"
copy  "D:\PowerShell Server 2016\sftproot\ymfront-ms\ymfront-finchina-ms\target\ymfront-finchina-ms-exec.jar" "D:\ymfront\projectJar"
copy  "D:\PowerShell Server 2016\sftproot\ymfront-gm\target\ymfront-gm.jar" "D:\ymfront\projectJar"
copy  "D:\PowerShell Server 2016\sftproot\ymfront-route\target\ymfront-route.jar" "D:\ymfront\projectJar"
call D:\ymfront\killScript\ymfront-center.bat
call D:\ymfront\killScript\ymfront-finchina-ms.bat
call D:\ymfront\killScript\ymfront-gm.bat
call D:\ymfront\killScript\ymfront-route.bat
start D:\ymfront\startScript\ymfront-center.bat
start D:\ymfront\startScript\ymfront-finchina-ms.bat
start D:\ymfront\startScript\ymfront-gm.bat
start D:\ymfront\startScript\ymfront-route.bat