###iptables命令（http://www.zsythink.net/archives/1199/）http://www.zsythink.net/archives/tag/iptables/
  iptables(用户空间)并不是真正的防火墙，而是一个客户端代理，通过代理将用户的安全设定执行到对应的安全框架中，安全框架才是真正的防火墙，框架名称：netfilter(位于内核空间)
    网络地址转换
    数据包内容修改
    数据包过滤
    service iptables start/stop/restart
  iptables处理数据包：accept（放行），reject（拒绝），drop（丢弃）
  内核支持IP_FORWARD:（执行流程）
    prerouting（路由前）是否为本机-->(input) ，else forward（转发）本机-->（output）,postrouting（路由后）
  表：相同功能的规则的集合，iptables定义了四种表
    filter表：过滤功能，防火墙，内核模块：iptables_filter
    nat表：network address translation,网络地址转换功能，内核模块：iptable_nat
    mangle表：拆解报文，做出修改，重新封装报文，iptable_mangle
    raw表：关闭nat表上启用的连接追踪机制,内核模块：iptable_raw
  表链关系：链到表，表到链的关系
    prerouting: nat,raw,mangle(prerouting中的规则只存放在nat表，raw表，mangle表中)
    input：mangle,filter(centos7还有nat表，centos6没有)
    forward：mangle，filter
    output：raw，mangle,nat,filter
    postrouting:mangle,nat
  链中表的优先级：
    prerouting: raw-->mangle-->nat-->filter
    只用output链有四种表
  规则的概念：
    匹配条件
      基本匹配条件：
        源地址：sourceIP，目标地址：DestinationIP
      扩展匹配条件：
        需要依赖对应的扩展模块
    处理动作：
      ACCEPT:
      DROP:
      REJECT:
      SNAT:源地址转换解决内网用户使用同一个公网地址上网的问题
      MASQUERADE:SNAT的特殊形式，适用于动态的，临时会变的IP上
      DNAT:目标地址转换
      REDIRECT:本机端口映射
      LOG:在/var/log/messages文件中记录日志信息，传递到下一条规则
  IPtable规则管理：（增加，修改，删除，保存）
    http://www.zsythink.net/archives/1517
Swarm(服务端资源统一管理)
Swarm集群创建方式：
  1.使用etcd创建集群
  2.使用consul创建集群
  3.使用Zookeeper创建集群
  4.使用静态文件创建集群
Swarm集群的调度方式：
  docker -d --label storage=ssd
  docker run -d -p -e constraint:storage=ssd
  最新方式:
      docker node update --label-add role=web node1
      docker node inspect node1
      docker node update --label-rm role node1
      * service方式
        docker service create --name nginx_2 --constraint 'node.labels.role == web' nginx
      * stack方式
        deploy:
          placement:
            constraints:
              - node.labels.role==web
  通过以上filter选择运行的机器，还通过strategy选出最终运行的宿主机，
    random：在宿主机中随机选择一台机器
    binpacking(权衡宿主机CPU和内存的占用率选择能分配到最大资源的那台机器)，
    spread：把每个容器平均的部署到每个节点上
Swarm集群的高可用：（HA）
  HA需要分布式键/值数据库为基础，（Consul(0.5.1+)，Etcd(2.0+)，Zookeeper(3.4.5+)）
  一主两备HA集群
    *创建集群(replication(告诉swarm，Swarm manager有多个)advertise(Swarm manager的地址))
      swarm manage -H :4000 <tls-config-flags> --replication --advertise <manager_ip1:port> etcd://<etcd_ip>/<path>
    *查询集群
      export DOCKER_HOST=<manager_ip:port>
      docker info
    *通过关闭集群中一个节点测试集群的可用性
Machine(跨平台宿主机管理工具)
  与Machine接驳：不同平台需要提供不同的认证方式
    docker-macinie create -h
  连接信息：
    docker-machine url

Swarm网络
  overlay:
    LVS：
      简介：
        内部通过LVS(Linux Virtual Server(开源的负载均衡项目集成到Linux中实现基于IP的数据请求负载均衡调用方案)方式
      工作模式：
        NAT（Network Address Translation）模式：
          LVS调度器可以使用两块网卡配置不同的IP地址，eth0设置为私有IP地址与内部网络通过交换设备相互连接，Eth1设置为外网IP与外部网络联通（VIP（Virtual IP Address））
        TUN模式：
          在LVS(NAT)模式的集群环境中，后端服务器大于10台，LVS调度器成为集群的瓶颈，请求和响应分离，调度器处理请求，真是服务器直接返回响应数据，IP tunning（IP 隧道）是一种数据包封装技术，它可以将原始数据包封装并添加新的包头（内容包括新的源地址及端口、目标地址及端口），从而实现将一个目标为调度器的VIP地址的数据包封装，通过隧道转发给后端的真实服务器（Real Server），通过将客户端发往调度器的原始数据包封装，并在其基础上添加新的数据包头（修改目标地址为调度器选择出来的真实服务器的IP地址及对应端口），LVS（TUN）模式要求真实服务器可以直接与外部网络连接，真实服务器在收到请求数据包后直接给客户端主机响应数据
        DR模式
    Overlay 网络与服务发现：
      Overlay 驱动实现了跨主机集群内部虚拟网络
        作用：
          *将运行的多个容器（不同主机），附加（attach to）到一个网络
          *默认情况下，服务发现为群集中的每个服务分配虚拟IP地址（VIP）和 动态 DNS，使其可以通过服务名称将其提供给同一网络上的容器。即在一个 Overlay 虚拟网络内，使用服务名称访问，将实现任务级别的负载均衡
          *在群集中使用覆盖网络，需要在群集节点之间打开以下端口：
            端口7946 TCP / UDP用于容器网络发现。
            端口4789 UDP用于容器覆盖网络。
      服务发现：
        默认情况下，创建一个连接到网络的服务时，群集将为该服务分配VIP。VIP根据服务名称映射到DNS别名。网络上的容器使用共享服务的DNS映射，因此网络上的任何容器都可以通过其服务名称访问该服务。
          注意：只有用户自定义网络，才有 dns 服务和服务自动发现
    LVS管理工具--ipvsadm
      https://www.cnblogs.com/lipengxiang2009/p/7353373.html
Swarm网络流向分析：
  Linux上使用net namespace来隔离docker创建的overlay网络
    sandbox:包含了网络协议栈的配置，包含对容器的网卡，路由表，以及DNS设置的管理 包含多个network的endpoint
    endpoint：一个endpoint只能属于一个网络和一个sandbox
    network：是一个可以互相通信的Endpoint的集合，Network的实现可以是一个Linux网桥，一个VLAN
  查看一下防火墙的规则
    *sudo iptables -nL -t nat
      ---
        Chain DOCKER-INGRESS (2 references)
        target     prot opt source               destination         
        DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8764 to:172.18.0.2:8764
        DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8085 to:172.18.0.2:8085
        DNAT       tcp  --  0.0.0.0/0            0.0.0.0/0            tcp dpt:8760 to:172.18.0.2:8760
        RETURN     all  --  0.0.0.0/0            0.0.0.0/0           
      ---
      所有对端口的请求都转发到172.18.0.2上
    *查看当前node的所有IP地址
      ip a
        ---
          6: docker_gwbridge: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
          link/ether 02:42:8e:31:22:f0 brd ff:ff:ff:ff:ff:ff
          inet 172.18.0.1/16 brd 172.18.255.255 scope global docker_gwbridge
             valid_lft forever preferred_lft forever
          inet6 fe80::42:8eff:fe31:22f0/64 scope link 
             valid_lft forever preferred_lft forever
        ---
    * 查看dockerdaemon的内部网络
      docker network ls
        414bf186b9d7        docker_gwbridge      bridge              local
    * 查看docker docker_gwbridge网络详情
        docker network inspect docker_gwbridge 
        ------
          "my-overlay-sbox": {
                "Name": "gateway_ingress_sbox",
                "EndpointID": "2d4e9361184367495a1445131c73bd5f186efcb9c02579aaae4e03cee4f96959",
                "MacAddress": "02:42:ac:12:00:02",
                "IPv4Address": "172.18.0.2/16",
                "IPv6Address": ""
            }
        -----
        扩展：
     * 查看网络命名空间
        ls /var/run/docker/netns/
     *进入ingress_sbox(netns:Docker为每一个worker节点创建的一个特殊的net namespace（sandbox），有连个endpoint，一个连接ingress network，另一个连接local bridge network docker_gwbridge,ingress network的IP空间为10.255.0.10/16，所有的route mesh的service共用此空间)
        nsenter --net=/var/run/docker/netns/ingress_sbox
     * 查看sandbox中的IP地址
       ip a
       ------
        17: eth1@if18: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
          link/ether 02:42:ac:12:00:02 brd ff:ff:ff:ff:ff:ff
          inet 172.18.0.2/16 brd 172.18.255.255 scope global eth1
          valid_lft forever preferred_lft forever
       ------
     * 数据进入端口后，进入LVS，转发到这两个地址（LVS管理工具--ipvsadm）
     https://www.cnblogs.com/lipengxiang2009/p/7353373.html
      yum install ipvsadm
        iptables -nL -t mangle
        ipvsadm -l


      给容器添加三块网卡eth0和eth1，eth2，eth0连接overlay类型网络名为ingress用于在不同主机间通信，eth1连接bridge类网络名为docker_gwbridge，用于让容器能访问外网。eth2连接到我们自己创建的mynet网络
      上，同样的作用也是用于容器之间的访问(区别于eth2网络存在dns解析即服务发现功能)



OracleDataGuard
 1  vi .bash_profile 
    2  sqlplus  / as sysdba
    3  cd $ORACLE_HOME
    4  cd network/admin/
    5  ls -lrt
    6  vi listener.ora 
    7  vi tnsnames.ora 
    8  cd ../../dbs/
    9  ls -lrt
   10  vi mv initYUMAO.ora initYMNEW
   11   mv initYUMAO.ora initYMNEW.ora
   12  ls -lrt
   13  mv orapwYUMAO orapwYMNEW
   14  vi initYMNEW.ora 
   15  cat initYMNEW.ora 
   16  mkdir -p /u01/app/oracle/admin/YMNEW/adump
   17  df -h
   18  fdisk -l
   19  sqlplus / as sysdba
   20  vi .bash_profile 
   21  sqlplus / as sysdba
   22  free -mm
   23  cd $ORACLE_HOME
   24  cd dbs/
   25  vi initYMNEW.ora 
   26  sqlplus / as sysdba
   27  free -m
   28  df -h
   29  vi initYMNEW.ora 
   30  sqlplus / as sysdba
   31  cd ../network/admin/
   32  ls -rlt
   33  vi listener.ora 
   34  lsnrctl start
   35  cd $ORACLE_HOME
   36  cd network/admin/
   37  vi tnsnames.ora 
   38  rman target sys/Ds*0019891@YUMAO auxiliary=sys/Ds*0019891@YMNEW nocatalog
   39  cd /data0/oradata/YMTD
   40  cd ../../dbs/
   41  vi initYMNEW.ora 
   42  cd /data0/oradata/YMNEW/
   43  ls -rlt
   44  sqlplus / as sysdba
   45  rman target sys/Ds*0019891@YUMAO auxiliary=sys/Ds*0019891@YMNEW nocatalog
   46  ls -lrt
   47  cd /data0/
   48  ls -rlt
   49  mkdir orabak
   50  ls -lrt
   51  cd orabak/
   52  ls -rlt
   53  ps -ef|grep pmon
   54  cd /tmp
   55  du -sh *
   56  sqlplus / as sysdba
   57  cd /data0/orabak/
   58  cd ../oradata/YMNEW/
   59  ls -rlt
   60  rman target sys/Ds*0019891@YUMAO auxiliary=sys/Ds*0019891@YMNEW nocatalog
   61  sqlplus / as sysdba
   62  rman target sys/Ds*0019891@YUMAO auxiliary=sys/Ds*0019891@YMNEW nocatalog
   63  sqlplus / as sysdba
   64  cd $ORACLE_HOME
   65  sqlplus / as sysdba
   66  cd  /u01/app/oracle/diag/rdbms/ymnew/YMNEW/trace
   67  view alert_YMNEW.log 
   68  tail -f alert_YMNEW.log 
   69  mysql -S mysql.sock -p
   70  sqlplus / as sysdba
   71  lsnrctl status
   72  tail -f alert_YMNEW.log 
   73  cd /data0/oradata/YMNEW/
   74  mkdir arch
   75  ls -lrt
   76  cd  /u01/app/oracle/diag/rdbms/ymnew/YMNEW/trace
   77  tail -f alert_YMNEW.log 
   78  sqlplus / as sysdba
   79  history

