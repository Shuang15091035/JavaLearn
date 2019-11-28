#!/bin/bash
WORK_DIR=/usr/local/mongo-cluster
MONGOD=$WORK_DIR/bin/mongod
MONGO=$WORK_DIR/bin/mongo

echo "start config"

$MONGOD --config config1.conf 

$MONGOD --config config2.conf

$MONGOD --config config3.conf

$MONGO --port 26001 --host localhost

