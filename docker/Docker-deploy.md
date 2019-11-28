version: '3'
services:
  ymfront-finchina:
    image: shuangluck/ymfront-finchina:prod
    ports:
      - "8762"
    networks:
      - overlay-ymfront
    privileged: true
    deploy:
      replicas: 2
      restart_policy:
        condition: on-failure
        delay: 5s
        max_attempts: 3
        window: 60s
      placement:
        constraints: [node.labels.role==service]
    environment:
      eureka.client.serviceUrl.defaultZone: http://eureka:8760/eureka,http://eureka:8761/eureka
  ymfront-gm:
    image: shuangluck/ymfront-gm:prod
    depends_on:
      - ymfront-finchina
    privileged: true
    ports:
      - "8085"
    volumes:
      - /home/ldapusers/jzcert/:/var/jz/cert/
      - /home/ldapusers/kingdomConf/:/var/jz/conf/
    networks:
      - overlay-ymfront
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 5s
        max_attempts: 3
        window: 60s
      placement:
        constraints: [node.labels.role==service]
    environment:
      eureka.client.serviceUrl.defaultZone: http://eureka:8760/eureka,http://eureka:8761/eureka
  ymfront-route:
    image: shuangluck/ymfront-route:prod
    ports:
      - "8764:8764"
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 5s
        max_attempts: 3
        window: 60s
      placement:
        constraints: [node.labels.role==service]
    networks:
      - overlay-ymfront
    environment:
      eureka.client.serviceUrl.defaultZone: http://eureka:8760/eureka,http://eureka:8761/eureka
networks:
  overlay-ymfront:
    external:
      name: overlay-ymfront
