@echo off
title ymfront-route:8764
taskkill /f /fi "windowtitle eq ymfront-route"
java -jar "D:\ymfront\projectJar\ymfront-route.jar"