#!/bin/bash
WORK_DIR=/usr/local/mongo-cluster
MONGOD=$WORK_DIR/bin/mongod
MONGO=$WORK_DIR/bin/mongo

echo "start shard2"

$MONGOD --config shard2_master.conf 

$MONGOD --config shard2_slaver.conf

$MONGOD --config shard2_arbiter.conf

$MONGO --port 28001 --host localhost
