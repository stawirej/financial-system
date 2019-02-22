#!/usr/bin/env bash

#
# Run under Windows GitBash.
#

#
# Set environment variables.
#
JDK_PATH="c:\\Program Files\\Java\\jdk-11.0.1\\"
LOG_FILE="log.txt"

#
# Build application. Compile but not run tests.
#
echo "Building application ..."
JAVA_HOME=${JDK_PATH} mvn install -DskipTests

#
# Run Spring Boot service in background.
# Required for system test.
#
JAVA_HOME=${JDK_PATH} mvn spring-boot:run > ${LOG_FILE} &

# Get PID of last background process.
FINANCIAL_SYSTEM_PID=$!
echo "Starting Spring Boot Financial System (PID = ${FINANCIAL_SYSTEM_PID}) ..."

# Wait for Spring Boot service to start.
( tail -f -n0 log.txt & ) | grep -q "Started FinancialSystemService"

#
# Run all tests.
#
echo "Running all tests ..."
JAVA_HOME=${JDK_PATH} mvn clean install

#
# Cleanup.
#
echo "Cleanup"
disown ${FINANCIAL_SYSTEM_PID}
kill -9 ${FINANCIAL_SYSTEM_PID}
rm ${LOG_FILE}
