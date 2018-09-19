@echo off
title ymfront-finchina-ms
setlocal enabledelayedexpansion
set port=8762
::set /p port=«Î ‰»Î∂Àø⁄∫≈£∫
for /f "tokens=1-5" %%a in ('netstat -ano ^| find ":%port%"') do (
    if "%%e%" == "" (
        set pid=%%d
    ) else (
        set pid=%%e
    )
    echo !pid!
    taskkill /f /pid !pid!
)
taskkill /f /fi "windowtitle eq ymfront-finchina-ms:8762"