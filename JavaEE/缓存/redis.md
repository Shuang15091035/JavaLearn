version: '3.7'
services:
  sentinel1:
    image: redis
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    restart: always
    ports:
      - 26379:26379
    volumes:
      - ~/master/conf/sentinel.conf:/usr/local/etc/redis/sentinel.conf
    networks:
      - ymfront-redis
  sentinel2:
    image: redis
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    restart: always
    ports:
      - 26380:26379
    volumes:
      - ~/slave1/conf/sentinel.conf:/usr/local/etc/redis/sentinel.conf
    networks:
      - ymfront-redis
  sentinel3:
    image: redis
    command: redis-sentinel /usr/local/etc/redis/sentinel.conf
    restart: always
    ports:
      - 26381:26379
    volumes:
      - ~/slave2/conf/sentinel.conf:/usr/local/etc/redis/sentinel.conf
    networks:
      - ymfront-redis
networks:
  - ymfront-redis