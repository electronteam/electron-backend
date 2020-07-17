mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

## Building the container/image 
docker build -t electron-backend .

## Building the container/image using dockerfile-maven-plugin
mvn clean package 

## Running the container
docker run -p 8080:8080 --name=electron-backend --link mysql-electron -e MYSQL_HOST=mysql-electron --link redis-electron -e REDIS_HOST=redis-electron --link solr-electron -e SOLR_HOST=solr-electron electron-backend

## Running the container as part of electron-network - !old method, ignore it and use docker-compose
docker run -p 8080:8080 --name=electron-backend --network=electron-network -e MYSQL_HOST=mysql-electron -e REDIS_HOST=redis-electron -e SOLR_HOST=solr-electron electron-backend

docker run -p 8080:8080 --name=electron-backend --network=electron-network \
-e MYSQL_HOST=mysql-electron \
-e REDIS_HOST=redis-electron \
-e SOLR_HOST=solr-electron electron-backend \
-v /Users/rbaroncea/Documents/proiecte/electron-backend/data/medias/products:/data2/medias/products

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

#Build the container - use this when code changes have been done
docker-compose build

# Builds, (re)creates, starts, and attaches to containers for a service.
docker-compose up

# Stop and remove the containers at once
docker-compose down

# Start all the containers at once
docker-compose start

# Stop all the containers at once
docker-compose stop

## Connect to running container using shell
docker exec -it electron-backend /bin/sh

## check if volume has been correctly created/mounted
cd data && ls && cd medias && ls && cd products && ls
