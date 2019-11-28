#!/bin/bash

WORK_DIR=/usr/local/mongo-cluster

mkdir -p $WORK_DIR/config/config1/data
mkdir -p $WORK_DIR/config/config2/data
mkdir -p $WORK_DIR/config/config3/data

mkdir -p $WORK_DIR/shard1/master/data
mkdir -p $WORK_DIR/shard1/slaver/data
mkdir -p $WORK_DIR/shard1/arbiter/data

mkdir -p $WORK_DIR/shard2/master/data
mkdir -p $WORK_DIR/shard2/slaver/data
mkdir -p $WORK_DIR/shard2/arbiter/data

mkdir -p $WORK_DIR/mongos/mongos1
mkdir -p $WORK_DIR/mongos/mongos2
mkdir -p $WORK_DIR/mongos/mongos3

mkdir -p $WORK_DIR/keyfile
openssl rand -base64 756 > mongo.key
chmod 400 mongo.key
mv mongo.key $WORK_DIR/keyfile

