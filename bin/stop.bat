set CATALINA_HOME=D:\tools\java\apache-tomcat-6.0.26
cmd /C %CATALINA_HOME%\bin\shutdown.bat
cmd /C java -jar %DERBY_HOME%\lib\derbyrun.jar server shutdown -user sa -password sa