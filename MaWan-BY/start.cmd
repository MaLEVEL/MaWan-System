@echo off
echo 正在关闭旧的 Java 进程...
taskkill /F /IM java.exe >nul 2>&1
timeout /t 2 /nobreak >nul

echo 正在启动 MaWan-BY...
cd /d "%~dp0"
.\mvnw.cmd spring-boot:run
