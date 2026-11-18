#!/bin/bash
echo "===================================================="
echo "  INICIANDO BUDGET MANAGER"
echo "===================================================="

if ! command -v java &> /dev/null; then
    echo "[ERROR] Java no esta instalado. Instala Java 21 o superior."
    echo https://adoptium.net/temurin/releases/?version=21
    exit 1
fi

./mvnw spring-boot:run