set CATALINA_HOME=D:\tools\java\apache-tomcat-6.0.26
cmd /C %CATALINA_HOME%\bin\startup.bat
cmd /C java -Dderby.system.home=D:\derbydata -jar %DERBY_HOME%\lib\derbyrun.jar server start