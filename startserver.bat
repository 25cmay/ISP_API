@echo off
setlocal EnableDelayedExpansion

REM Load variables from local.env
for /f "tokens=1,2 delims==" %%A in (local.env) do (
    set "key=%%A"
    set "val=%%B"
    set !key!=!val!
)

call mvnw spring-boot:run