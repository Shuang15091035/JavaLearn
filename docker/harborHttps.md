


1.

openssl genrsa -out ca.key 2048

2.

 openssl req -x509 -new -nodes -sha512 -days 3650 -subj "/C=CN/ST=SHANGHAI/L=SHANGHAI/O=JP/OU=YM/CN=jpym.ymfront.com" -key ca.key -out ca.crt
3.
openssl genrsa -out jpym.ymfront.com.key 2048
4.
openssl req -sha512 -new \
    -subj "/C=CN/ST=SHANGHAI/L=SHANGHAI/O=JP/OU=YM/CN=jpym.ymfront.com" \
    -key jpym.ymfront.com.key \
    -out jpym.ymfront.com.csr
5.
cat > v3.ext <<-EOF
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth
subjectAltName = @alt_names

[alt_names]
DNS.1=jpym.ymfront.com
EOF

6.
openssl x509 -req -sha512 -days 3650 \
-extfile v3.ext \
-CA ca.crt -CAkey ca.key -CAcreateserial \
-in jpym.ymfront.com.csr \
-out jpym.ymfront.com.crt

7.harbor需要配置的信息
cp jpym.ymfront.com.crt /data/cert/
cp jpym.ymfront.com.key /data/cert/

8.
openssl x509 -inform PEM -in jpym.ymfront.com.crt -out jpym.ymfront.com.cert

9.(每一个需要登录的主机的docker中都需要配置这个证书信息)
sudo mkdir -p /etc/docker/certs.d/jpym.ymfront.com/
sudo mv ca.crt jpym.ymfront.com.cert jpym.ymfront.com.key /etc/docker/certs.d/jpym.ymfront.com/
sudo echo 10.116.132.110 jpym.ymfront.com >> /etc/hosts
cp jpym.ymfront.com.cert /etc/docker/certs.d/jpym.ymfront.com/
cp jpym.ymfront.com.key /etc/docker/certs.d/jpym.ymfront.com/
cp ca.crt /etc/docker/certs.d/jpym.ymfront.com/
	note：docker中未配置证书信息，docker login ,docker push 会出现以下错误
		Error response from daemon: Get https://jpym.ymfront.com/v2/: x509: certificate signed by unknown authority
10.
sudo systemctl restart docker


11.
每一个需要登录harbor的主机的hosts需要添加一下内容
10.116.132.110 jpym.ymfront.com 


docker login jpym.ymfront.com/harbor


### 生产环境关于使用docker deploy 部署harbor私有镜像库中镜像
生产限制条件
	1.生产环境当前用户是普通用户，使用任务命令都是sudo方式，否则无权限
	2.harbor需要基于https方式才能访问镜像库。
	3.所在宿主机已经配置了基于https的方式访问harbor所需证书
通过以下命令部署的服务提示 No such images
 docker stack deploy  
正确处理方式：
	sudo docker stack deploy --with-registry-auth
