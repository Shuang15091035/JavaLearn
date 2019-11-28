系统定义的ingress网络


[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec 1-dx2pv9umgv ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: br0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 8e:4b:c7:af:29:8e brd ff:ff:ff:ff:ff:ff
    inet 10.255.0.1/16 brd 10.255.255.255 scope global br0
       valid_lft forever preferred_lft forever
5: vxlan0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UNKNOWN group default 
    link/ether 8e:4b:c7:af:29:8e brd ff:ff:ff:ff:ff:ff link-netnsid 0
7: veth0@if6: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UP group default 
    link/ether c2:f9:b9:63:2f:a4 brd ff:ff:ff:ff:ff:ff link-netnsid 1
57: veth7@if56: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UP group default 
    link/ether 9e:32:3d:40:4c:e4 brd ff:ff:ff:ff:ff:ff link-netnsid 2



[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec ingress_sbox ip addr

连接ingress network
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
6: eth0@if7: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 02:42:0a:ff:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.255.0.2/16 brd 10.255.255.255 scope global eth0
       valid_lft forever preferred_lft forever
[JP004556@ymmicrosrv-shylf-12 ~]$ sudo ip netns list
RTNETLINK answers: Invalid argument
RTNETLINK answers: Invalid argument
netns
1b8328a9cce1 (id: 5)   ymfront-route
ea5aa0a9ea23 (id: 4)   ymfront-finchina
1-nctxuzypay (id: 2)
lb_nctxuzypa (id: 3)
1-dx2pv9umgv (id: 0)
ingress_sbox (id: 1)


docker_gwbridge :
"Containers": {
            "61a4e889a45d3385af877fc0aea8c0f5818c404bee2fa8631df810975813b0ec": {
                "Name": "gateway_ea5aa0a9ea23",		ymfront-finchina
                "EndpointID": "3ff2caeb6001ca15f70e285a2d4c1e693a4f7643b42674794e13ea7d5f9038b6",
                "MacAddress": "02:42:ac:12:00:03",
                "IPv4Address": "172.18.0.3/16",
                "IPv6Address": ""
            },
            "a885d5e3e500f6f4b7d375c15ac882f938871da6943ba30e1746ce61cf70cb8f": {
                "Name": "gateway_1b8328a9cce1",		 ymfront-route
                "EndpointID": "b57ba631b1acce589898c57cf24fc40521834a4bc6cabc4b998b378617e1ef7b",
                "MacAddress": "02:42:ac:12:00:04",
                "IPv4Address": "172.18.0.4/16",
                "IPv6Address": ""
            },
            "ingress-sbox": {
                "Name": "gateway_ingress-sbox",
                "EndpointID": "03fb1d9da81d27e488a792d7785a236ce51dce212306f4ff1b80a86a4d7e4d24",
                "MacAddress": "02:42:ac:12:00:02",
                "IPv4Address": "172.18.0.2/16",
                "IPv6Address": ""
            }
        },


 "SandboxKey": "/var/run/docker/netns/1b8328a9cce1",
    inet 10.255.0.52/32 brd 10.255.0.52 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.255.0.50/32 brd 10.255.0.50 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.255.1.42/32 brd 10.255.1.42 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.255.1.46/32 brd 10.255.1.46 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.255.1.44/32 brd 10.255.1.44 scope global eth0
    
<!    用于连接docker_gwbridge>

       valid_lft forever preferred_lft forever
9: eth1@if10: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:12:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 172.18.0.2/16 brd 172.18.255.255 scope global eth1
       valid_lft forever preferred_lft forever
系统 ingress网络  
ingress_sbox + 