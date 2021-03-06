#! /bin/bash

export MAVEN_REPO=/mnt/c/Users/asim2/.m2/repository
export CLASSPATH=/mnt/c/Dev/workspace/gitrepo/MarketData/target/classes
export CLASSPATH=$CLASSPATH:$MAVEN_REPO/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar
export CLASSPATH=$CLASSPATH:$MAVEN_REPO/org/slf4j/slf4j-simple/1.7.5/slf4j-simple-1.7.5.jar
export CLASSPATH=$CLASSPATH:$MAVEN_REPO/org/hdrhistogram/HdrHistogram/2.1.9/HdrHistogram-2.1.9.jar

java -cp $CLASSPATH -Xms1024m -Xmx1024m -Xloggc:client-gc.log -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps org.marketdata.client.MarketDataClient


