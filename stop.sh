#!/bin/bash

echo "Removing containers..."
docker stop devsu-account-operation
docker rm devsu-account-operation
docker stop devsu-client-person
docker rm devsu-client-person
echo "Finishing Removing containers..."
echo "Removing images..."
docker rmi devsu-account-operation-docker:latest
docker rmi devsu-client-person-docker:latest
echo "Finishing Removing images..."