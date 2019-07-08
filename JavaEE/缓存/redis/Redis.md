Redis : ReadMe.md文件解读
Build Redis
	compiledOn : Linux, OSX, OpenBSD, NetBSD, FreeBSD
 way:
 	make
 	make 32bit
 	做编译后测试使用：
 		make test
 FixBuildProblem
 	通过Git pull 更新源码，或者‘deps’Directory make不会自动重新编译依赖，即是资源目录的文件有修改
 		make distclean
 			：清除 jemalloc, lua, hiredis, linenoise
 FixProblemBuilding 32 Bit binaries
 	先编译32Bittarget后，需要重新编译为64Bit
 		：make distclean 在Redis根目录
 Allocator：
 	前置指定使用的分配器：
 		make MALLOC=libc/make MALLOC=jemalloc
 verbose build:
 	make V=1		
Running Redis:
	默认配置运行：
		cd src
		./redis-server
	自定义配置文件:
		cd src
		./redis-server /path/to/redis.conf
	修改Redis配置文件，通过命令行参数传递：
		./redis-server --port 9999 --replicaof 127.0.0.1 6379
		./redis-server /etc/redis/6379.conf --loglevel debug
	redis.conf中所有的选项，都可以通过通过同名的参数从命令行传入
Playing With Redis：
	cd src
	./redis-cli
	查找所有可用的命令：
		http://redis.io/commands
Installing Redis:
	安装Redis到/usr/local/bin
		make install
	指定Redis安装目录：
		make PREFIX=/some/other/directory install
	Make install :仅仅安装Redis到系统中，不会配置初始化脚本和配置文件到正确的地方。

Redis安装：
	下载Redis,解压
		wget http://download.redis.io/releases/redis-5.0.5.tar.gz && tar -zxvf redis-5.0.5.tar 
		cd redis-5.0.5 & make
	启动方式：
		前端方式：
			cd /src/ && ./redis-server
		守护进程启动方式：
			修改配置文件 ：daemonize yes
NoSQL：
		键值(Key-Value)存储数据库
	相关产品： Tokyo Cabinet/Tyrant、<Redis>、Voldemort、Berkeley DB
	典型应用： 内容缓存，主要用于处理大量数据的高访问负载。 
	数据模型： 一系列键值对
	优势： 快速查询
	劣势： 存储的数据缺少结构化

		列存储数据库
	相关产品：Cassandra, <HBase>, Riak
	典型应用：分布式的文件系统
	数据模型：以列簇式存储，将同一列数据存在一起
	优势：查找速度快，可扩展性强，更容易进行分布式扩展
	 劣势：功能相对局限
		文档型数据库
	相关产品：CouchDB、<MongoDB>
	典型应用：Web应用（与Key-Value类似，Value是结构化的）
	数据模型： 一系列键值对
	 优势：数据结构要求不严格
	 劣势： 查询性能不高，而且缺乏统一的查询语法
		图形(Graph)数据库
	相关数据库：Neo4J、<InfoGrid>、Infinite Graph
	典型应用：社交网络
	数据模型：图结构
	优势：利用图结构相关算法。
	劣势：需要对整个图做计算才能得出结果，不容易做分布式的集群方案。
 	Redis应用场景：
 		缓存（商品信息，数据查询，短连接，新闻内容）
 		分布式集群架构中的session分离
 		聊天室在线好友列表
 		任务队列：（秒杀，抢购，12306）
 		应用排行榜
 		网站访问统计
 		数据过期处理
Redis客户端：
	Java客户端：jedis
	Redis自带客户端：redis-cli
	Redis设置默认数据库数量：
		16个
		可以通过select进行选择

Redis数据类型：
	String;
		set,get,mset,mget,getset,incr,incrby,decr,decrby,strlen,append,del
		eg:
			set num 2,get num 2
	Map:
		hset,hget,hmset,hmget,hgetall,hdel,hincrby,hexists,hkeys,hvals,hlen,rpoplpush
		eg:
			hset user username zhangsan,
			hmset user age 20 username lisi
			hincrby user age 2
	List:(采用链表存储的)
		lpush,rpush,lrange（包含两端元素）,lpop,rpop,llen,lrem,lindex,lset,ltrim(指定范围和lrange一致)，linsert,
			LREM命令会删除列表中前count个值为value的元素，返回实际删除的元素个数。根据count值的不同，该命令的执行方式会有所不同： 
				当count>0时， LREM会从列表左边开始删除。 
				当count<0时， LREM会从列表后边开始删除。 
				当count=0时， LREM删除所有值为value的元素。 
			
		linsert:该命令首先会在列表中从左到右查找值为pivot的元素，然后根据第二个参数是BEFORE还是AFTER来决定将value插入到该元素的前面还是后面
			linsert list1 after 3 4
		将元素的从一个列表转移到另一个列表中：
			rpoplpush list1 newlist
	Set:（无序不可重复）
		sadd ,srem,smembers,sismember,sdiff（A-B）,sinter（A∩B）,sunion（A∪B）,smembers,scard,spop,
	SortedSet:(有序集合，唯一，和set不同的是，会给元素添加一个分数，根据分数排序)
	 常用命令：
	 	keys ,exists, del,rename,type,expire(设置key的存活时间)

Redis 持久化方案：
	Rbd默认方式：(快照)
		持久化快照时间：
			save <seconds> <changes>
			save 900 1
			save 300 1
		文件存储目录：
			dbfilename dump.rdb
			dir ./
		非法关闭Redis，会丢失最后一次持久化之后的数据，保证数据不丢失，需要使用aof方式
	Aof方式：
		Aof方式的持久化，操作一次Redis数据库，将操作的记录存储到aof持久化文件中
		开启：
			appendonly yes
			appendfilename "appendonly.aof"
Redis主从复制：(保证即使Redis重启也不会丢失数据，因为Redis重启会重新从硬盘将持久化的数据恢复到内存中，但是硬盘损坏会导致数据丢失，Redis主从复制可解决单点故障问题)
	主机无需配置
	从机配置：
		修改Redis.conf文件
			Slaveof masterip masterport
		修改从机配置文件中的端口
		删除从机持久化文件：
			rm -rf appendonly.aof dump.rdb
 	Sentinel.conf文件解析：
 		sentinel monitor mymaster 127.0.0.1 6379 2
 			监视master名称为 mymaster IP地址：127.0.0.1 端口号：6379 法定人数(quorum)：2 仲裁参数
 				quorum：quorum是哨兵的数量， 这些哨兵需要同意就无法访问主节点的事实打成一致。为了最终标记从节点失败，
 				仲裁仅用于检测故障，为了实际执行故障转移，投票竞选一个故障转移的领导者，并继续进行。
		sentinel down-after-milliseconds mymaster 60000
			一个实例不可访问的时间(无法响应ping或者回复一个错误信息)认为实例宕机了
		sentinel failover-timeout mymaster 180000
			故障转移的超时时间
		sentinel parallel-syncs mymaster 1
			在故障转移的同时，可以被配置作为一个新的主节点的从节点的个数。
		Sentinel： 配置一主三从的模式，设置三个哨兵，当M1发生故障，S2和S3将同意故障，并能够授权故障转移，是客户能继续
			当M1故障，网络隔离，R2被提升为主，位于旧主服务器的C1可能会写入数据，这个数据将会永远丢失，M1被重置为新的主机的从机，丢失数据集，使用以下配置可以缓解此问题
			min-slaves-to-write 1
			min-slaves-max-lag 10 :复制数据是异步进行的，未发送超过max-lag秒数的异步确认，M1将变为不可用，但是副作用是，如果从属设备关闭，主设备将停止接收写入。
		slave-priority: 从机故障转移过程中，可依据优先级选择，从站优先级为0，从站不会升级为主站，Sentinel首选优先级较低的从站
		Redis仲裁原理：
			如果“投票选举”的leader为自己，且状态正常的sentinels实例中，“赞同者”选举自己的sentinel个数不小于(>=) 50% + 1,且不小与<quorum>，那么此sentinel就会认为选举成功且leader为自己
		Slave选举与优先级：
			评估项：
				与master断开的次数，Slave的优先级，数据复制下标（slave有多少master数据），进程ID
				slave与master失去联系超过10次，每次都超过配置的最大失联时间，那么这个slave就会被sentinel认为不适合用来做新的master。
			优先级越小排名越靠前，
			优先级相同，复制下标，接收数据多靠前，
			其次是进程ID较小的那个

		SDown和ODown（Objectively）:
			发送ping心跳，在一定时间没有接受到响应，或者不合法响应，SDown，
			SDOWN->ODOWN ：sentinel 通过gossip协议，如果收到足够多sentinel消息表明master，down，SDOWN->ODOWN(Master),
			down-after-milliseconds:

		<Redis>和sentinel身份验证>:
			master和slave配置密码是需要配置两项：
				requirepass，masterauth
			sentinel做故障转移的时候，由于一个master可能会变成一个slave，一个slave也可能会编程一个master
Redis集群（重点）：
	节点的fail是通过集群中超过半数的节点检测失效时才失效
	redis-cluster把所有的物理节点映射到[0-16383]slot上,cluster 负责维护
	Redis 集群中内置了 16384 个哈希槽，当需要在 Redis 集群中放置一个 key-value 时，redis 先对 key 使用 crc16 算法算出一个结果，然后把结果对 16384 求余数，这样每个 key 都会对应一个编号在 0-16383 之间的哈希槽，redis 会根据节点数量大致均等的将哈希槽映射到不同的节点
	(1)集群中所有master参与投票,如果半数以上master节点与其中一个master节点通信超过(cluster-node-timeout),认为该master节点挂掉.
	(2):什么时候整个集群不可用(cluster_state:fail)? 
	➢	如果集群任意master挂掉,且当前master没有slave，则集群进入fail状态。也可以理解成集群的[0-16383]slot映射不完全时进入fail状态。
	➢	如果集群超过半数以上master挂掉，无论是否有slave，集群进入fail状态。


jedis连接Redis集群：
