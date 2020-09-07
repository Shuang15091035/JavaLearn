@echo off

title 公募概要文件传输

setlocal enabledelayedexpansion

@rem 原地址
set originFolder=D:\szt\recv\ISP04

@rem 目的地址
set destFolder=D:\szt\back\recv\ISP04

for /F "delims=" %%i in ('dir /b/s %originFolder%\*.pdf') do (

  for /F "delims=," %%j in ('curl -X POST -F "file=@%%i" http://localhost:9095/batch/upload -s -o NUL -w %%{http_code}') do (
    
    if %%j==200 (
      echo %%i
      move %%i %destFolder%
    )else (
      echo 概要文件发送失败（%%j）！
    )
  )
)

set pdfnum=0

for /f %%i in ('dir /b/s %originFolder%\*.pdf') do (
 set /A pdfnum+=1
)

if %pdfnum%==0 (
  echo 概要文件已传输完成
  move %originFolder%\* %destFolder%
  echo 概要文件移动到备份目录%destFolder%完成
)

cmd