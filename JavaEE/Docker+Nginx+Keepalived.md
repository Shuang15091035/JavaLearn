docker+Nginx+keepalived
+ docker pull centos
+ docker run -it centos /bash/bin
+ 安装Nginx

        #使用yum安装nginx需要包括Nginx的库，安装Nginx的库

        rpm -Uvh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm

        # 使用下面命令安装nginx
        #yum install nginx

        #安装网络包(需要使用ifconfig和ping命令)
        yum install net-tools

        #安装vim
        yum install vim
+ 安装keepalived
            
            #安装keepalived环境依赖
            
            yum install -y gcc openssl-devel popt-devel
            
            #安装keepalived
            
            通过yum install keepalived
            
            
            
            #或者通过源码安装
            
            wget http://124.205.69.132/files/90630000053A2BB4/www.keepalived.org/software/keepalived-1.3.4.tar.gz
            
            tar zxvf keepalived-1.3.4.tar.gz 
            
            cd keepalived-1.3.4
            
            ./configure --prefix=/usr/local/keepalived
            
            make && make install
            
            
            
            拷贝几个文件到CentOS7环境中：
            
            cp keepalived-1.3.4/keepalived/etc/init.d/keepalived /etc/init.d/
            mkdir /etc/keepalived
            cp /usr/local/keepalived/etc/keepalived/keepalived.conf /etc/keepalived/
            cp keepalived-1.3.4/keepalived/etc/sysconfig/keepalived /etc/sysconfig/
            cp /usr/local/keepalived/sbin/keepalived /usr/sbin/+
+ 配置/etc/keepalived/keepalived.conf 
    主：
    
        ! Configuration File for keepalived
        
        global_defs {
        router_id master
        }
        vrrp_script check_nginx {
        script "/etc/keepalived/check_nginx"
        interval 2
        weight -20
        }
        vrrp_instance VI_1 {
        state MASTER
        interface eth0
        virtual_router_id 52
        priority 100
        advert_int 2
        nopreempt
        authentication {
        auth_type PASS
        auth_pass 123456
        }
        virtual_ipaddress {
        172.17.0.210
        }
        }


    从：
        ! Configuration File for keepalived

        global_defs {
        router_id backup
        }
        vrrp_script check_nginx {
        script "/etc/keepalived/check_nginx"
        interval 2
        weight -20
        }
        vrrp_instance VI_1 {
        state BACKUP
        interface eth0
        virtual_router_id 2
        priority 99
        advert_int 2
        authentication {
        auth_type PASS
        auth_pass 123456
        }
        virtual_ipaddress {
        172.17.0.210
        }
        }
    检测脚本：
    
        A=`ps -ef | grep nginx | grep -v grep | wc -l`
        if [ $A -eq 0 ];then
        nginx
        sleep 2
        if [ `ps -ef | grep nginx | grep -v grep | wc -l` -eq 0 ];then
        #killall keepalived
        ps -ef|grep keepalived|grep -v grep|awk '{print $2}'|xargs kill -9
        fi
        
        fi
+ Keepalived服务命令
        systemctl daemon-reload  重新加载
        systemctl enable keepalived.service  设置开机自动启动
        systemctl disable keepalived.service 取消开机自动启动
        systemctl start keepalived.service 启动
        systemctl stop keepalived.service停止
        systemctl status keepalived.service  查看服务状态
+ systemctl enable keepalived.service  设置开机自动启动
+ systemctl start keepalived.service 启动
+ docker commit 容器ID centos_keepalived_nginx:v1
+ 启动容器 docker run --privileged  -tid --name  keepalived_master centos_keepalived_nginx:v1 /usr/sbin/init
