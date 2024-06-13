#!/bin/bash

echo "Compiling Java sources DevsuClientPerson..."
cd DevsuClientPerson
mvn install -DskipTests
echo "Finishing Compiling Java sources..."
echo "Building DevsuClientPerson container, port 8080..."
docker build -t devsu-client-person-docker:latest .
docker run -d --name devsu-client-person -p 8080:8080 devsu-client-person-docker:latest .
echo "Finishing container set up, 8080 port..."
cd ..
echo "Compiling Java sources DevsuAccountOperation..."
cd DevsuAccountOperation
mvn install -DskipTests
echo "Finishing Compiling Java sources..."
echo "Building DevsuAccountOperation container, port 8081..."
docker build -t devsu-account-operation-docker:latest .
docker run -d --name devsu-account-operation -p 8081:8081 devsu-account-operation-docker:latest .
echo "Finishing container set up, 8081 port..."
cd ..