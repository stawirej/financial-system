#!/usr/bin/env bash

#
# Set environment variables.
#
JDK_PATH="/Users/stawirej/.sdkman/candidates/java/current"
LOG_FILE="log.txt"

#
# Build application. Compile but not run tests.
#
echo "Building application ..."
JAVA_HOME=${JDK_PATH} mvn clean install -DskipTests

#
# Run Spring Boot service in background.
# Required for system test.
#
JAVA_HOME=${JDK_PATH} mvn spring-boot:run > ${LOG_FILE} &

echo "Starting Spring Boot Financial System..."

# Wait for Spring Boot service to start.
( tail -f -n0 log.txt & ) | grep -q "Started FinancialSystemService"

#
# Run all tests.
#
echo "Running all tests ..."
JAVA_HOME=${JDK_PATH} mvn test

#
# Cleanup.
#
echo "Cleanup"

# Find all PIDs for financial system
for PID in $(ps -A | grep financial | awk '{print $1}');
  do
     echo "Welcome $PID times"
     disown ${PID}
     kill -9 ${PID}
 done

rm ${LOG_FILE}
