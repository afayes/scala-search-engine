#!/bin/sh
#set -e
#set -u

start() {
    docker-compose  up -d
}

down() {
    docker-compose  down
}

purge() {
    # Nothing to do yet
    echo "Nothing to do yet"
}

# $1 is the composer name
# $2 is the docker image name
# $3 is the docker tag
build_component() {
    local name=$1
    local image_name=$2
    local tag=$3
    docker-compose kill $name
    docker-compose rm -f $name
    docker rmi "$image_name:$tag"
    docker-compose  build $name
}

build_all() {
    mvn clean install -DskipTests=true -Dmaven.test.skip=true
    build_component "crawler" "inexus.ixxus.co.uk:28443/ixxus/hlv-crawler" "development"
}

tail() {
    docker-compose  logs -f
}

rm_all_images() {
	docker rmi $(docker images -q)
}

rm_all_containers() {
	docker rm $(docker ps -a -q)
}

echo
echo "******************************************"
echo "Remeber to do:"
echo "    docker login inexus.ixxus.co.uk:28443"
echo "******************************************"
echo

case "$1" in
  build)
    down
    build_all
    start
    tail
    ;;
  loader)
    echo "Disabled!"
    start
    tail
    ;;
  crawler)
    mvn clean install -DskipTests=true
    build_component "crawler" "inexus.ixxus.co.uk:28443/ixxus/hlv-crawler" "development"
    start
    tail
    ;;
  start)
    start
    tail
    ;;
  stop)
    down
    ;;
  purge)
    down
    purge
    ;;
  tail)
    tail
    ;;
  *)
    echo "Usage: $0 {build|start|stop|purge|tail|loader|crawler}"
esac
