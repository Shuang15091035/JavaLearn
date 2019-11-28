## 网络
### OVS 隧道模式(将一种网络歇息包装d在另一种协议中传输的技术)，（overlay网络）

### docker容器网络配置

				是否支持多机			南北通信方式(容器和外部) 			东西通信方式(容器内)
	bridge：		否					宿主机端口绑定		Linux bridge
	host：		是 					按宿主机网路通信 		按宿主机网路通信
	node：		否					无法通信				link


host :
采用host模式的容器，可以直接使用宿主机的IP地址与外界进行通信，若宿主机具有公有IP，那么容器也拥有这个公有IP。同时容器内服务的端口也可以使用宿主机的端口，
无需额外进行NAT转换，而且由于容器通信时，不再需要通过linuxbridge等方式转发或者数据包的拆封，性能上有很大优势。
当然，这种模式有优势，也就有劣势，主要包括以下几个方面：
1）最明显的就是容器不再拥有隔离、独立的网络栈。容器会与宿主机竞争网络栈的使用，并且容器的崩溃就可能导致宿主机崩溃，在生产环境中，这种问题可能是不被允许的。
2）容器内部将不再拥有所有的端口资源，因为一些端口已经被宿主机服务、bridge模式的容器端口绑定等其他服务占用掉了
bridge:
bridge模式是docker默认的，也是开发者最常使用的网络模式。在这种模式下，docker为容器创建独立的网络栈，保证容器内的进程使用独立的网络环境，
实现容器之间、容器与宿主机之间的网络栈隔离。同时，通过宿主机上的docker0网桥，容器可以与宿主机乃至外界进行网络通信。
其网络模型可以参考下图：

## Linux network namespace
	通过安装ip utility， iproute2 进行网路信息查看
	管理容器namespace：
		1.通过docker inspect --format '{{.State.Pid}}' containerId查看容器的PID
		2.ln -s /proc/pid/ns/net /var/run/docker/netns/containerId 建立软连接
		3.ip netns list 查看网络命名空间
		4。通过ip工具进行网络配置
		note:
			进行网络配置需要开启特权模式：--privileged=true，否则是没有权限的，但会给主机单来安全隐患，建议使用ip netns exec
			Ubuntu系统下通过nsenter操作namespace
	pipeWork原理解析：
		容器和主机处在同一个网络中，可通过交换机进行网络数据交互，虚拟场景下可通过建立虚拟网桥将容器连接到一个二层网络中，
		安装网桥工具 yum install bridge-utils	
		wget http://launchpad.net/bridgeutils/main/1.4/+download/bridge-utils-1.4.tar.gz
		pipework:
			桥接，直接路由，跨主机通信方式简单有效，但是要求主机在同一个局域网中，两台主机在不同的二级网络中，可通过隧道模式解决容器的跨主机网络通信。
		OVS 划分VLAN
			docker容器的VLAN划分 默认所有容器都连接在docker0 网桥上，这是一个普通的Linux网桥，Open VSwitch代替docker0进行VLAN划分，
