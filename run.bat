@echo off
title BudgetManager - Iniciando...
echo ====================================================
echo   INICIANDO BUDGET MANAGER
echo ====================================================

:: Verificar que Java esté instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java no esta instalado o no esta en el PATH.
    echo Por favor instala Java 21 o superior.
    echo https://adoptium.net/temurin/releases/?version=21
    pause
    exit /b
)

:: Arrancar la app
echo Compilando y arrancando la aplicacion...
call mvnw.cmd spring-boot:run

pause

@echo off
echo 