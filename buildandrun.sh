#!/bin/sh

echo "Clean dir and rebuild beercloak"
mvn clean install
echo "Build new docker image with beercloak"
docker build -t block360/keycloak .
echo "Run beercloak"
docker-compose up