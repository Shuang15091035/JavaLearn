@echo off

title ��ļ��Ҫ�ļ�����

setlocal enabledelayedexpansion

@rem ԭ��ַ
set originFolder=D:\szt\recv\ISP04

@rem Ŀ�ĵ�ַ
set destFolder=D:\szt\back\recv\ISP04

for /F "delims=" %%i in ('dir /b/s %originFolder%\*.pdf') do (

  for /F "delims=," %%j in ('curl -X POST -F "file=@%%i" http://localhost:9095/batch/upload -s -o NUL -w %%{http_code}') do (
    
    if %%j==200 (
      echo %%i
      move %%i %destFolder%
    )else (
      echo ��Ҫ�ļ�����ʧ�ܣ�%%j����
    )
  )
)

set pdfnum=0

for /f %%i in ('dir /b/s %originFolder%\*.pdf') do (
 set /A pdfnum+=1
)

if %pdfnum%==0 (
  echo ��Ҫ�ļ��Ѵ������
  move %originFolder%\* %destFolder%
  echo ��Ҫ�ļ��ƶ�������Ŀ¼%destFolder%���
)

cmd