@echo off
title ymfront-gm:8085
taskkill /f /fi "windowtitle eq ymfront-gm"
java -jar "D:\ymfront\projectJar\ymfront-gm.jar"