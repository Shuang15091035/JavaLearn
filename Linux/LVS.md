IPVS是LVS项目的一部分，运行在Linux Kernel的四层负载均衡器，用户空间通过ipvsadm管理
IPtables，IPVS
	1.当客户端的请求到达负载均衡器的内核空间时，首先会到达PREROUTING链。
	2.当内核发现请求数据包的目的地址是本机时，将数据包送往INPUT链。
	3.当数据包到达INPUT链时，首先会被IPVS检查，如果数据包里面的目的地址及端口没有在IPVS规则里面，那么这条数据包将被放行至用户空间。
	4.如果数据包里面的目的地址及端口在IPVS规则里面，那么这条数据报文的目的地址被修改为通过负载均衡调度算法选好的后端服务器（DNAT），并送往POSTROUTING链。
	5.最后经由POSTROUTING链发往后端服务器
命令参数说明：
	惯例大写用户virtual server，小写表示real server
   -A, --add-service 添加virtual server, virtual server必须唯一(IP+端口+协议用于区分一个唯一的虚拟服务)

   -E, --edit-service 编辑虚拟服务

   -D, --delete-service 删除一个虚拟服务, 包括与之关联的真实服务器

   -C, --clear 清空虚拟服务表

   -R, --restore 通过标准输入恢复虚拟服务表(IPVS table)

   -S, --save 以可以通过-R参数恢复的格式导出虚拟服务表到标准输出

   -a, --add-server 添加一个真实服务器到一个虚拟服务

   -e, --edit-server 在一个虚拟服务当中编辑一个真实服务器

   -d, --delete-server 从一个虚拟服务当中移除一个真实服务器

   -L, -l, --list 列出虚拟服务表, 默认是列出所有虚拟服务表, 若需要列出某服务表, 后面指定服务地址即可

   -Z, --zero 清零一个所有服务的数据包 / 字节 / 速率计数器

LVS集群类型中的术语
	Director：负载均衡器,也称VS(Virtual Server)
	RS:真实服务器(RealServer)
	CIP：客户端IP(Client IP)
	VIP:  Client所请求的，提供虚拟服务的IP，可以用Keepalive做高可用
	DIP:在Director实现与RS通信的IP
	RIP:RealServer IP

作者：uangianlap
链接：https://www.jianshu.com/p/1f7203f12046
来源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
三种数据转发模式：
LVS-NAT（网络地址转换），LVS-DR（直接路由模式），LVS-TUN（隧道模式），LVS_FULLNAT()
	lvs-nat: 修改请求报文的目标IP
	lvs-dr: 操纵封闭新的MAC地址
	lvs-tun: 在原请求IP报文之外新加一个IP首部
	lvs-fullnat: 修改请求报文的源和目标IP
	https://blog.csdn.net/lixiaowei16/article/details/37927343
LVS的缺陷是：它不会考虑应用服务器的健康状况，如果应用服务器故障它也会选择