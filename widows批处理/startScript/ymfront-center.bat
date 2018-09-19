@echo off
title ymfront-center:8761
taskkill /f /fi "windowtitle eq ymfront-center"
java -jar "D:\ymfront\projectJar\ymfront-center.jar"