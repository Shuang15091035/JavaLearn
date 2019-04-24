ssh-keygen 所在包安装：yum  install -y openssh-server openssh-clients
启动ssh：

　　service sshd start 或 /etc/init.d/sshd start

　　配置开机启动：

　　chkconfig --level 2345 sshd on