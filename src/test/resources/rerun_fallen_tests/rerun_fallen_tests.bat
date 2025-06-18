java -classpath testng.jar;%CLASSPATH% org.testng.TestNG -d test-outputs testng.xml
java -classpath testng.jar;%CLASSPATH% org.testng.TestNG -d test-outputs test-outputs\testng-failed.xml
