整个网络的汇集点

60: eth1@if61: ymfront-overlay
56: eth0@if57: 系统定义的ingress网络
ingress_sbox 
docker network inspect docker_gwbridge
"IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.18.0.0/16",
                    "Gateway": "172.18.0.1"
                }
            ]
        },
"Containers": {
            "b36e9dee7b83a30f39e931fe119d80b1af17a394d6457afbdd3fb4a53c645cf7": {
                "Name": "gateway_4db53a6fc07f",
                "EndpointID": "1d94522b843ff87b1e57714aae4ae2d5946eb7d1321923ab2f43aa87a59f3a69",
                "MacAddress": "02:42:ac:12:00:03",
                "IPv4Address": "172.18.0.3/16",
                "IPv6Address": ""
            },
            "ingress-sbox": {
                "Name": "gateway_ingress-sbox",
                "EndpointID": "d23cddf9f062fce3249f5d5a1afa92c7d0ffc63471922f7b2d54ad747c0c2b14",
                "MacAddress": "02:42:ac:12:00:02",
                "IPv4Address": "172.18.0.2/16",
                "IPv6Address": ""
            }
        },


[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec 4db53a6fc07f ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
56: eth0@if57: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 02:42:0a:ff:00:33 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.255.0.51/16 brd 10.255.255.255 scope global eth0
       valid_lft forever preferred_lft forever
58: eth2@if59: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:12:00:03 brd ff:ff:ff:ff:ff:ff link-netnsid 2
    inet 172.18.0.3/16 brd 172.18.255.255 scope global eth2
       valid_lft forever preferred_lft forever
60: eth1@if61: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 02:42:0a:00:01:3c brd ff:ff:ff:ff:ff:ff link-netnsid 1
    inet 10.0.1.60/24 brd 10.0.1.255 scope global eth1
       valid_lft forever preferred_lft forever


自定义网络 ymfront-overlay


[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec 1-nctxuzypay ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
2: br0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 26:47:9e:12:42:80 brd ff:ff:ff:ff:ff:ff
        inet 10.0.1.1/24 brd 10.0.1.255 scope global br0
       valid_lft forever preferred_lft forever
53: vxlan0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UNKNOWN group default 
    link/ether 7e:15:36:1b:e0:e2 brd ff:ff:ff:ff:ff:ff link-netnsid 0
55: veth0@if54: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UP group default 
    link/ether 26:47:9e:12:42:80 brd ff:ff:ff:ff:ff:ff link-netnsid 1
61: veth1@if60: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue master br0 state UP group default 
    link/ether 82:e7:9f:40:ef:63 brd ff:ff:ff:ff:ff:ff link-netnsid 2

自定义网络 ymfront-overlay

[JP004556@ymmicrosrv-shylf-13 ~]$ sudo ip netns exec lb_nctxuzypa ip addr  
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
54: eth0@if55: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1450 qdisc noqueue state UP group default 
    link/ether 02:42:0a:00:01:3d brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.0.1.61/24 brd 10.0.1.255 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.0.1.62/32 brd 10.0.1.62 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.0.1.59/32 brd 10.0.1.59 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.0.1.142/32 brd 10.0.1.142 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.0.1.148/32 brd 10.0.1.148 scope global eth0
       valid_lft forever preferred_lft forever
    inet 10.0.1.145/32 brd 10.0.1.145 scope global eth0
       valid_lft forever preferred_lft forever