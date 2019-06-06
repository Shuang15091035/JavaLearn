history           
    1  2019-03-29 16:50:26 oracle  cd /tmp/database/
    2  2019-03-29 16:50:27 oracle  ./runInstaller 
    3  2019-03-29 16:50:32 oracle  export display=10.106.132.159:0.0
    4  2019-03-29 16:50:40 oracle  export display=172.29.116.58:0.0
    5  2019-03-29 16:50:41 oracle  ./runInstaller 
    6  2019-03-29 16:50:52 oracle  export DISPLAY=172.29.116.58:0.0
    7  2019-03-29 16:50:53 oracle  ./runInstaller 
    8  2019-03-29 16:51:19 oracle  export DISPLAY=172.29.116.58:0.0
    9  2019-03-29 16:51:20 oracle  ./runInstaller 
   10  2019-03-29 16:51:34 oracle  echo $DISPLAY
   11  2019-03-29 16:51:43 oracle  export DISPLAY=:0.0
   12  2019-03-29 16:51:45 oracle  ./runInstaller 
   13  2019-03-29 16:52:55 oracle  who
   14  2019-03-29 16:53:32 oracle  export DISPLAY=172.29.116.58:0.0
   15  2019-03-29 16:58:08 oracle  ./runInstaller 
   16  2019-03-29 16:58:18 oracle  clock
   17  2019-03-29 16:58:51 oracle  ./runInstaller 
   18  2019-03-29 17:04:59 oracle  ip a
   19  2019-03-29 17:47:43 oracle  cd /tmp/database/
   20  2019-03-29 17:47:43 oracle  ls -lrt
   21  2019-03-29 17:48:15 oracle  ping 10.211.55.5
   22  2019-03-29 17:48:49 oracle  who
   23  2019-03-29 17:49:33 oracle  export DISPLAY=10.106.71.33:0.0
   24  2019-03-29 17:49:35 oracle  ./runInstaller 
   25  2019-03-29 17:49:45 oracle  export DISPLAY=172.29.116.58:0.0
   26  2019-03-29 17:49:46 oracle  ./runInstaller 
   27  2019-03-29 17:50:25 oracle  yum install xdpyinfo
   28  2019-03-29 17:50:46 oracle  cd /tmp/database/
   29  2019-03-29 17:50:48 oracle  export DISPLAY=172.29.116.58:0.0
   30  2019-03-29 17:50:50 oracle  ./runInstaller 
   31  2019-03-29 17:51:21 oracle  xdpyinfo 
   32  2019-03-29 17:52:14 oracle  ping 172.29.116.58
   33  2019-03-29 17:52:18 oracle  export DISPLAY=172.29.116.58:0.0
   34  2019-03-29 17:52:24 oracle  ./runInstaller 
   35  2019-03-29 17:53:16 oracle  ps -ef|grep oracle
   36  2019-03-29 22:46:39 oracle  cd /tmp
   37  2019-03-29 22:46:41 oracle  cd database/
   38  2019-03-29 22:47:26 oracle  ping 10.99.200.6
   39  2019-03-29 22:47:44 oracle  ip a
   40  2019-03-29 22:49:11 oracle  ping 169.254.98.8
   41  2019-03-29 22:49:28 oracle  ping 169.254.57.14
   42  2019-03-29 22:49:42 oracle  ping 192.168.137.1
   43  2019-03-29 22:50:48 oracle  ping 10.99.200.6
   44  2019-03-29 17:57:11 oracle  ping 172.29.116.123
   45  2019-04-01 09:37:48 oracle  cd /tmp/database/
   46  2019-04-01 09:38:26 oracle  export DISPLAY=0:0
   47  2019-04-01 09:38:28 oracle  ./runInstaller 
   48  2019-04-01 09:38:34 oracle  xdpyinfo 
   49  2019-04-01 09:38:44 oracle  export DISPLAY=172.29.116.58:0.0
   50  2019-04-01 09:38:46 oracle  xdpyinfo 
   51  2019-04-01 09:29:55 oracle  cd /tmp//database/
   52  2019-04-01 09:29:56 oracle  ls -lrt
   53  2019-04-01 09:30:14 oracle  ping 172.29.108.126
   54  2019-04-01 09:30:30 oracle  export DISPLAY=172.29.108.126:0.0
   55  2019-04-01 09:30:36 oracle  xdpyinfo 
   56  2019-04-01 09:30:46 oracle  ./runInstaller 
   57  2019-04-01 10:50:40 oracle  cd /tmp
   58  2019-04-01 10:50:41 oracle  cd database/
   59  2019-04-01 10:51:08 oracle  export DISPLAY=10.106.71.124:0.0
   60  2019-04-01 10:51:12 oracle  xdpyinfo 
   61  2019-04-01 11:17:40 oracle  export DISPLAY=10.106.71.124:0.0
   62  2019-04-01 11:17:45 oracle  cd /tmp/database/
   63  2019-04-01 11:17:46 oracle  ./runInstaller 
   64  2019-04-01 16:26:25 oracle  ssh-keygen
   65  2019-04-01 16:26:29 oracle  cd .ssh/
   66  2019-04-01 16:26:30 oracle  ls -lrt
   67  2019-04-01 16:26:37 oracle  cat id_rsa.pub 
   68  2019-04-01 16:26:46 oracle  vi authorized_keys
   69  2019-04-01 16:27:29 oracle  ssh 10.106.132.160 date
   70  2019-04-01 16:27:32 oracle  ssh 10.106.132.159 date
   71  2019-04-01 16:28:16 oracle  cat 
   72  2019-04-01 16:28:21 oracle  cat /etc/hosts
   73  2019-04-01 16:28:26 oracle  ssh 10.106.132.167 date
   74  2019-04-01 16:28:31 oracle  ping 10.106.132.167
   75  2019-04-01 16:28:36 oracle  ping 10.106.132.164
   76  2019-04-01 16:30:06 oracle  ping ymoradb-hzba-11
   77  2019-04-01 16:30:09 oracle  ssh ymoradb-hzba-11 date
   78  2019-04-02 14:39:48 oracle  cd /tmp/database/
   79  2019-04-02 14:39:49 oracle  ls -lrt
   80  2019-04-02 14:39:55 oracle  export DISPLAY=10.106.71.124:0.0
   81  2019-04-02 14:39:57 oracle  ./runInstaller 
   82  2019-04-02 15:01:28 oracle  vi $ORACLE_HOME/sysman/lib/ins_emagent.mk
   83  2019-04-02 15:55:34 oracle  sqlplus / as sysdba
   84  2019-04-02 15:55:40 oracle  vi .bash_profile 
   85  2019-04-02 15:56:07 oracle  yum install readline
   86  2019-04-02 15:57:01 oracle  sqlplus 
   87  2019-04-02 15:57:08 oracle  sqlplus / as sysdba
   88  2019-04-02 15:57:16 oracle  cd $ORACLE_HOME/dbs
   89  2019-04-02 15:57:17 oracle  ls -lrt
   90  2019-04-02 16:05:11 oracle  df -h
   91  2019-04-02 16:06:18 oracle  vi initYUMAO.ora
   92  2019-04-02 16:07:37 oracle  free -m
   93  2019-04-02 16:07:38 oracle  vi initYUMAO.ora
   94  2019-04-02 16:07:47 oracle  free -m
   95  2019-04-02 16:07:48 oracle  top
   96  2019-04-02 16:08:03 oracle  sqlplus / as sysdba
   97  2019-04-02 16:08:14 oracle  vi ~/.bash_profile 
   98  2019-04-02 16:08:39 oracle  source ~/.bash_profile 
   99  2019-04-02 16:08:41 oracle  cat initYUMAO.ora 
  100  2019-04-02 16:08:51 oracle  mkdir /data0/oradata/YUMAO/
  101  2019-04-02 16:08:52 oracle  mkdir /data0/oradata/YUMAO/ -p
  102  2019-04-02 16:09:13 oracle  cd $ORACLE_HOME/dbs
  103  2019-04-02 16:09:22 oracle  mkdir -p /u01/app/oracle/admin/YUMAO/adump
  104  2019-04-02 16:19:45 oracle  vi .bash_profile 
  105  2019-04-02 16:19:53 oracle  source .bash_profile 
  106  2019-04-02 16:23:49 oracle  cat /etc/sysc
  107  2019-04-02 16:23:50 oracle  cat /etc/sysctl.
  108  2019-04-02 16:23:51 oracle  cat /etc/sysctl.conf 
  109  2019-04-02 16:24:36 oracle  df -h
  110  2019-04-02 16:24:39 oracle  sqlplus / as sysdba
  111  2019-04-02 16:25:10 oracle  vi /etc/fstab
  112  2019-04-02 16:25:20 oracle   cat /etc/fstab | grep tmpfs
  113  2019-04-02 16:25:20 oracle  vi /etc/fstab
  114  2019-04-02 16:29:03 oracle  sqlplus / as sysdba
  115  2019-04-02 16:42:11 oracle  cd /data0/oradata/YUMAO/
  116  2019-04-02 16:42:11 oracle  ls -rlt
  117  2019-04-02 16:42:12 oracle  rm -rf *
  118  2019-04-02 16:42:14 oracle  sqlplus / as sysdba
  119  2019-04-02 16:45:30 oracle  ls -lrt
  120  2019-04-02 16:45:31 oracle  rm -rf *
  121  2019-04-02 16:45:35 oracle  sqlplus / as sysdba
  122  2019-04-02 17:07:46 oracle  sqlplus
  123  2019-04-02 17:08:13 oracle  sqlplus system/
  124  2019-04-02 17:08:24 oracle  sqlplus / as sysdba
  125  2019-04-02 17:08:46 oracle  netstat -lntp
  126  2019-04-02 17:08:51 oracle  cd $ORACLE_HOME
  127  2019-04-02 17:08:53 oracle  cd network/admin/
  128  2019-04-02 17:08:55 oracle  ls -lrt
  129  2019-04-08 09:56:54 oracle  sqlplus / as sysdba
  130  2019-04-08 09:57:08 oracle  export ORACLE_SID=YUMAP
  131  2019-04-08 09:57:10 oracle  export ORACLE_SID=YUMAO
  132  2019-04-08 09:57:13 oracle  sqlplus / as sysdba
  133  2019-04-08 09:57:18 oracle  df -h
  134  2019-04-08 09:57:29 oracle  lsnrctl status
  135  2019-04-08 09:57:33 oracle  ps -ef|grep lsnr
  136  2019-04-08 09:57:35 oracle  cd $ORACLE_HOME
  137  2019-04-08 09:57:36 oracle  ls -lrt
  138  2019-04-08 09:57:38 oracle  cd network/admin/
  139  2019-04-08 09:57:39 oracle  ls -lrt
  140  2019-04-08 09:58:05 oracle  vi listener.ora
  141  2019-04-08 09:58:26 oracle  ip a
  142  2019-04-08 09:58:28 oracle  vi listener.ora
  143  2019-04-08 09:58:37 oracle  vi tnsnames.ora
  144  2019-04-08 09:59:25 oracle  lsnrctl start
  145  2019-04-08 09:59:27 oracle  lsnrctl status
  146  2019-04-08 09:59:32 oracle  sqlplus / as sysdba
  147  2019-04-08 09:59:36 oracle  cd
  148  2019-04-08 09:59:41 oracle  vi .bash_profile 
  149  2019-04-08 10:00:00 oracle  sqlplus / as sysdba
  150  2019-04-08 10:00:04 oracle  ps -ef|gre] pmon
  151  2019-04-08 10:00:08 oracle  vi .bash_profile 
  152  2019-04-08 10:00:34 oracle  sqlplus / as sysdba
  153  2019-04-08 10:00:37 oracle  ps -ef|grep pmon
  154  2019-04-08 10:04:12 oracle  sqlplus / as sysdba
  155  2019-04-08 10:04:20 oracle  vi .bash_profile 
  156  2019-04-08 10:04:32 oracle  cd $ORACLE_BASE/product/11.2.0/db_1
  157  2019-04-08 10:04:34 oracle  ls -lrt
  158  2019-04-08 10:04:37 oracle  cd
  159  2019-04-08 10:04:39 oracle  vi .bash_profile 
  160  2019-04-08 10:04:53 oracle  sqlplus / as sysdba
  161  2019-04-08 10:04:55 oracle  vi .bash_profile 
  162  2019-04-08 10:05:25 oracle  sqlplus / as sysdba
  163  2019-04-08 10:05:28 oracle  vi .bash_profile 
  164  2019-04-08 10:05:45 oracle  echo $ORACLE_HOME
  165  2019-04-08 10:05:58 oracle  cp .bash_profile .bash_profilebak
  166  2019-04-08 10:05:59 oracle  vi .bash_profile
  167  2019-04-08 10:06:30 oracle  sqlplus / as sysdba
  168  2019-04-08 10:06:40 oracle  cd $ORACLE_HOME/dbs
  169  2019-04-08 10:06:41 oracle  ls -lrt
  170  2019-04-08 10:06:45 oracle  vi initYUMAO.ora 
  171  2019-04-08 10:07:02 oracle  cd /u01/app/oracle/diag/rdbms/yumao/YUMAO/trace/
  172  2019-04-08 10:07:04 oracle  view alert_YUMAO.log 
  173  2019-04-08 10:07:49 oracle  view /u01/app/oracle/diag/rdbms/yumao/YUMAO/trace/YUMAO_pmon_24326.trc:
  174  2019-04-08 10:07:53 oracle  view /u01/app/oracle/diag/rdbms/yumao/YUMAO/trace/YUMAO_pmon_24326.trc
  175  2019-04-08 10:08:09 oracle  cd $ORACLE_HOME/dbs
  176  2019-04-08 10:08:09 oracle  ls -lrt
  177  2019-04-08 10:08:12 oracle  vi initYUMAO.ora 
  178  2019-04-08 10:08:23 oracle  ps -ef|grep YUMAO
  179  2019-04-08 10:08:33 oracle  ps -ef|grep YUMAO|awk '{print $2}'
  180  2019-04-08 10:08:35 oracle  ps -ef|grep YUMAO|awk '{print $2}'|xargs kill -9
  181  2019-04-08 10:08:39 oracle  ps -ef|grep pmon
  182  2019-04-08 10:08:41 oracle  sqlplus / as sysdba
  183  2019-04-08 10:09:06 oracle  cd
  184  2019-04-08 10:09:09 oracle  rm .bash_profile
  185  2019-04-08 10:09:15 oracle  mv .bash_profilebak .bash_profile
  186  2019-04-08 10:09:19 oracle  sqlplus / as sysdba
  187  2019-04-08 10:09:23 oracle  ps -ef|grep pmon
  188  2019-04-08 10:09:26 oracle  ps -ef|grep lsnr
  189  2019-04-08 10:09:30 oracle  lsnrctl status
  190  2019-04-08 10:09:34 oracle  cd $ORACLE_HOME/network 
  191  2019-04-08 10:09:35 oracle  cd admin/
  192  2019-04-08 10:09:36 oracle  ls -lrt
  193  2019-04-08 10:09:38 oracle  cat listener.ora 
  194  2019-04-08 10:23:50 oracle  lsnrctl status
  195  2019-04-08 10:23:53 oracle  ps -ef|grep pmon
  196  2019-04-08 10:23:55 oracle  sqlplus / as sysdba
  197  2019-04-08 10:25:25 oracle  cd $0RACLE_HOME
  198  2019-04-08 10:25:29 oracle  cd $ORACLE_HOME
  199  2019-04-08 10:25:31 oracle  cd network/admin/
  200  2019-04-08 10:25:33 oracle  cat listener.ora 
  201  2019-04-08 10:26:04 oracle  cat tnsnames.ora 
  202  2019-04-08 10:26:29 oracle  sqlplus / as sysdba
  203  2019-04-08 10:26:46 oracle  cd ../../dbs/
  204  2019-04-08 10:26:47 oracle  ls -lrt
  205  2019-04-08 10:26:57 oracle  orapw --help
  206  2019-04-08 10:27:04 oracle  orapwd --help
  207  2019-04-08 10:27:39 oracle  orapwd file=orapwYUMAO password=0a8s5IgkQY3gWiaj
  208  2019-04-08 10:27:41 oracle  ls -lrt
  209  2019-04-08 10:27:53 oracle  scp orapwYUMAO 600393@10.106.132.160
  210  2019-04-08 10:28:30 oracle  scp orapwYUMAO 600393@10.106.132.160:/tmp
  211  2019-04-08 10:36:17 oracle  lsnrctl status
  212  2019-04-08 10:41:32 oracle  cd
  213  2019-04-08 10:41:34 oracle  vi .bash_profile 
  214  2019-04-08 10:41:50 oracle  sqlplus / as sysdba
  215  2019-04-08 10:41:55 oracle  ps -ef|grep pmon
  216  2019-04-08 10:42:06 oracle  cd /u01/app/oracle/diag/rdbms/yumao/YUMAO/trace/
  217  2019-04-08 10:42:08 oracle  view alert_YUMAO.log 
  218  2019-04-08 10:42:29 oracle  ps -ef|grep pmo
  219  2019-04-08 10:42:31 oracle  ps -ef|grep pmon
  220  2019-04-08 10:42:35 oracle  sqlplus / as sysdba
  221  2019-04-08 10:42:38 oracle  cd
  222  2019-04-08 10:42:40 oracle  vi .bash_profile 
  223  2019-04-08 10:42:48 oracle  ps -ef|grep pmon
  224  2019-04-08 10:42:52 oracle  ps -ef|grep YUMAO
  225  2019-04-08 10:43:02 oracle  ps -ef|grep YUMAO|awk '{print $2}'
  226  2019-04-08 10:43:07 oracle  ps -ef|grep YUMAO|awk '{print $2}'|xargs kill -9
  227  2019-04-08 10:43:10 oracle  ps -ef|grep pmon
  228  2019-04-08 10:43:13 oracle  sqlplus / as sysdba
  229  2019-04-08 10:43:30 oracle  cd /dev/shm/
  230  2019-04-08 10:43:31 oracle  ls -lrt
  231  2019-04-08 10:43:32 oracle  df -h
  232  2019-04-08 10:43:34 oracle  ls -lrt
  233  2019-04-08 10:43:38 oracle  ls -l |more
  234  2019-04-08 10:43:41 oracle  rm -rf *
  235  2019-04-08 10:43:43 oracle  df -h
  236  2019-04-08 10:43:47 oracle  sqlplus / as sysdba
  237  2019-04-08 10:45:51 oracle  cd $ORACLE_HOME
  238  2019-04-08 10:45:53 oracle  cd network/admin/
  239  2019-04-08 10:45:54 oracle  vi tnsnames.ora 
  240  2019-04-08 10:48:40 oracle  sqlplus / as sysdba
  241  2019-04-08 10:49:01 oracle  cd /data0/oradata/YUMAO/arch
  242  2019-04-08 10:49:03 oracle  cd /data0/oradata/YUMAO/
  243  2019-04-08 10:49:05 oracle  mkdir arch
  244  2019-04-08 10:49:07 oracle  sqlplus / as sysdba
  245  2019-04-08 14:46:07 oracle  cd $ORACLE_HOME/network 
  246  2019-04-08 14:46:09 oracle  cd admin/
  247  2019-04-08 14:46:10 oracle  vi listener.ora 
  248  2019-04-08 14:46:20 oracle  ls -lrt
  249  2019-04-08 14:46:21 oracle  vi tnsnames.ora 
  250  2019-04-08 14:46:32 oracle  lsnrctl stop
  251  2019-04-08 14:46:38 oracle  lsnrctl start
  252  2019-04-08 14:46:40 oracle  lsnrctl status
  253* 2019-04-08 16:48:50 oracle   192.168.30.200