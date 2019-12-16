表：
filter表：负责过滤功能，防火墙，iptable_filter
NAT:网络地址转换，；内核模块，iptable_nat
mangle表：拆解报文，做出修改，并重新封装；iptable_mangle
raw表：关闭nat表上启用的连接追踪机制，iptable_raw

iptables表的规则可以被链使用：
raw: prerouting
mangle:
nat:
filter:

当iptables定义的4张表，当他们处于同一条“链”时，执行的优先级
raw --> mangle --> nat--> filter

匹配条件：基本匹配条件与扩展匹配条件
处理动作： ACCEPT,DROP,REJECT,SNAT,MASQUERAED,DNAT,REDIRECT,LOG


output -> 同时存在四张表中
Input -> filter, mangle , nat
forward -> mangle, filter


yum install ipvsadm

yum install bridge-utils

查看容器的网络命名空间
docker inspect a885d5e3e500 | grep -i sandboxkey

进程网络命名空间恢复到主机目录
docker inspect 容器名称 | grep -i pid
ln -s /proc/容器进程号/ns/net /var/run/netns/容器

容器网络ID与命名空间文件的映射关系
docker network ls

找到网络的ID，既可以在此目录下找到对应的文件。

对overlay网络，一般网络前面都有1-或2-这种数字前缀，直接使用即可
使用如下命令恢复网络netns到主机上。

ln -s /var/run/docker/netns/网络ID前缀 /var/run/netns/网络netns名称

执行ip netns命令即可查到网络的netns。

这是因为在systemd管理的docker服务中，默认开启了MountFlags=slave，这样容器docker在启动守护进程时，会将守护进程的mount命名空间独立出去，导致系统进程无法查看到，需要进行手动恢复。
权限足够时，可以删除MountFlags=slave配置

/var/run/docker/netns/ea5aa0a9ea23

