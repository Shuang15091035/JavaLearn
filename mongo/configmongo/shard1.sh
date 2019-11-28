#!/bin/bash
WORK_DIR=/usr/local/mongo-cluster
MONGOD=$WORK_DIR/bin/mongod
MONGO=$WORK_DIR/bin/mongo

echo "start shard1"

$MONGOD --config shard1_master.conf 

$MONGOD --config shard1_slaver.conf

$MONGOD --config  shard1_arbiter.conf

$MONGO --port 27001 --host localhost
