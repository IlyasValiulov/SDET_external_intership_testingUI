@echo off
set HUB_PORT=4444
set GRID_CONFIG=config.json

echo Starting Selenium Hub on port %HUB_PORT%
start "Selenium Hub" java -jar selenium-server-<version>.jar hub --port %HUB_PORT% --config %GRID_CONFIG%