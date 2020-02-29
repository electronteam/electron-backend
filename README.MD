mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

## Building the container 
docker build -t electron-backend .

## Building the container using dockerfile-maven-plugin
mvn clean package 

## Running the container
docker run -p 8080:8080 --name=electron-backend --link mysql-electron -e MYSQL_HOST=mysql-electron --link redis-electron -e REDIS_HOST=redis-electron --link solr-electron -e SOLR_HOST=solr-electron electron-backend

## Running the container as part of electron-network
docker run -p 8080:8080 --name=electron-backend --network=electron-network -e MYSQL_HOST=mysql-electron -e REDIS_HOST=redis-electron -e SOLR_HOST=solr-electron electron-backend

## Starting the container
docker start electron-backend

## Stopping the container
docker stop electron-backend

## Removing the container
docker rm electron-backend

## Create a custom network for electron containers
docker network create electron-network

## Connect a running container to a network
docker network connect electron-network electron-backend

## Inspect the network
docker inspect electron-network

