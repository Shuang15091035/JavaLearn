version: '3.7'
services:
  master:
    image: redis
    command: redis-server /usr/local/etc/redis/redis.conf
    ports:
      - 6379:6379
    networks:
      - ymfront-redis
    volumes:
      - ~/myredis/master/conf/redis:/usr/local/etc/redis/
  slave1:
    image: redis
    depends_on:
      - master
    command: redis-server /usr/local/etc/redis/redis.conf
    ports:
      - 6380:6379
    networks:
      - ymfront-redis
    volumes:
      - ~/myredis/slave1/conf/redis:/usr/local/etc/redis/
  slave2:
    image: redis
    command: redis-server /usr/local/etc/redis/redis.conf
    ports:
      - 6381:6379
    depends_on:
      - master
    networks:
      - ymfront-redis
    volumes:
      - ~/myredis/slave2/conf/redis:/usr/local/etc/redis/
networks:
  ymfront-redis:
    external: true
