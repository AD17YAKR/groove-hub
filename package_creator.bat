@echo off
setlocal

REM Set the base directory
set "baseDir=src\main\java\com\groovehub"

REM Hardcode the module name
set "moduleName=auth"

REM Check if the base directory exists
if not exist "%baseDir%" (
    echo The base directory '%baseDir%' does not exist.
    echo Please create it before running this script.
    pause
    exit /b
)

REM Check if the module directory already exists
if exist "%baseDir%\%moduleName%" (
    echo The directory '%baseDir%\%moduleName%' already exists.
    echo Please choose a different module name or delete the existing directory.
    pause
    exit /b
)

REM Create the module directory and its sub-packages
echo Creating module directory and sub-packages...

mkdir "%baseDir%\%moduleName%" || exit /b
mkdir "%baseDir%\%moduleName%\controller"
mkdir "%baseDir%\%moduleName%\model"
mkdir "%baseDir%\%moduleName%\service"
mkdir "%baseDir%\%moduleName%\repository"
mkdir "%baseDir%\%moduleName%\dto"
mkdir "%baseDir%\%moduleName%\exception"
mkdir "%baseDir%\%moduleName%\util"

REM Confirmation message
echo Module '%moduleName%' and its sub-packages have been created successfully.
echo You can start adding your classes to the respective directories.

pause
endlocal