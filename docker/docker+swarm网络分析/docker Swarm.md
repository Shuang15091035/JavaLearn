ID                  NAME                            MODE                REPLICAS            IMAGE                            PORTS
y545ghvt4bhu        ymfrontcenter_ymfront-center1   replicated          1/1                 shuangluck/ymfront-center:prod   *:8760->8760/tcp
ujdok71t1zs0        ymfrontcenter_ymfront-center2   replicated          1/1                 shuangluck/ymfront-center:prod   *:8761->8760/tcp
4ejj56x7c5nz        ymfrontservices_ymfront-gm      replicated          1/1                 shuangluck/ymfront-gm:prod       *:8085->8085/tcp
07u3sq1h6snp        ymfrontservices_ymfront-route   replicated          1/1                 shuangluck/ymfront-route:prod    *:8764->8764/tcp



center1:
	namespace: ymfrontcenter

	networks:
		aliases:
			eureka,
			ymfront-centera
			publishMode: ingress (边界网络) 享受边界负载均衡，以及route Mesh ，host：失去边界负载均衡，确定了映射点，

	Endpoint:
		8760

		Vip 
			10.255.0.50/16
			10.0.1.59/24
center2:
	publishMode: ingress
	endpoint:
		10.255.0.52/16
		10.0.1.62/24

gm:
	publishMode: ingress
	10.255.0.91/16  容器内部：10.255.0.92
	10.0.1.114/24
route:
	publishMode: ingress
	10.255.0.93/16
	10.0.1.117/24


overlay-ymfront:
	subnet: 10.0.1.0/24
	gateway: 10.0.1.1
ingress：
	subnet:10.255.0/16
	gateway:10.255.0.1
docker_gwbridge:
	Subnet: 172.18.0.0/16
	Gateway: 172.18.0.1
bridge:
	Subnet:172.17.0.0/16
[JP004556@ymmicrosrv-shylf-13 ~]$ sudo iptables -tnat -nL
	Chain PREROUTING (policy ACCEPT)
	Chain DOCKER-INGRESS (2 references)
	target     prot opt source               destination         
	DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8764 to:172.18.0.2:8764
	DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8085 to:172.18.0.2:8085
	DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8760 to:172.18.0.2:8760
	DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8761 to:172.18.0.2:8761
	RETURN     all  --  0.0.0.0/0            0.0.0.0/0  

/**
ingress_sbox: 是swarm为每个节点默认创建的net namespace ，用于连接ingress overlay network，此处会设置mangle表，将
dst port 的数据做标记（fwmark）同时做DNAT转换成VIP，是数据包正常转发到ingress的ns中，该VIP由ingress_sbox的IPvs做负载均衡
**/
[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec ingress_sbox iptables -tmangle -nL
	Chain PREROUTING (policy ACCEPT)
	target     prot opt source               destination         
	MARK       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8761 MARK set 0x11e
	MARK       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8760 MARK set 0x120
	MARK       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8085 MARK set 0x148
	MARK       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8764 MARK set 0x14b