
# windows/cygwin 
set MAVEN_REPO=C:/Users/asim2/.m2/repository
set CLASSPATH=c:/Dev/workspace/gitrepo/MarketData/target/classes
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%/org/slf4j/slf4j-simple/1.7.5/slf4j-simple-1.7.5.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%/org/hdrhistogram/HdrHistogram/2.1.9/HdrHistogram-2.1.9.jar

java -cp %CLASSPATH% -Xms1024m -Xmx1024m -Xloggc:server-gc.log -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+AggressiveOpts -XX:NewRatio=2 -XX:-HeapDumpOnOutOfMemoryError -XX:-PrintCompilation -XX:-PrintTenuringDistribution -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -XX:+UseCompressedOops org.marketdata.server.MarketDataServer
