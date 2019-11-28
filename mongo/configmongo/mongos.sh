#!/bin/bash
WORK_DIR=/usr/local/mongo-cluster
MONGOD=$WORK_DIR/bin/mongos
MONGO=$WORK_DIR/bin/mongo
BINDIP=127.0.0.1,192.168.209.130


echo "start mongos"

$MONGOD --config mongos1.conf --bind_ip $BINDIP

$MONGOD --config mongos2.conf --bind_ip $BINDIP

$MONGOD --config mongos3.conf --bind_ip $BINDIP

$MONGO --port 25001 --host localhost
