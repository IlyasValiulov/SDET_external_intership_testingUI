@echo off
set HUB_URL=http://localhost:4444
set NODE_PORT=5555
set THREADS=3

echo Starting Selenium Node connected to %HUB_URL% with %THREADS% threads
start "Selenium Node" java -jar selenium-server-<version>.jar node --hub %HUB_URL% --port %NODE_PORT% --max-sessions %THREADS% --max-threads %THREADS%