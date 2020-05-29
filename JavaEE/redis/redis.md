Redis内存碎片化问题：
	Redis内存碎片化的原因：每天大量数据写入，这些数据key和原来的数据的key不一致，Redis本身会通过lru策略将部分旧数据淘汰，而有效数据却没有被Redis进程释放。
	目前较好的解决方案：对机器设置内存监控，超过95%内存使用率重启Redis，回收内存。
Redis执行make时报Jemalloc/jemalloc.h没有这个文件和或目录：
	make MALLOC=libc

Redis如何做持久化的：
	bgsave做镜像全量持久化，aof做增量持久化。因为bgsave会耗费较长时间，不够实时，在停机的时候会导致大量丢失数据，所以需要aof来配合使用。在Redis实例重启是，会使用bgsave持久化文件重新构造内存，再使用aof重放近期的操作指令来实现完整恢复重启之前的状态。

Redis分布式锁：
	Redission分布式锁实现方案：
		redisson.getLock(lockkey);
		try {
			redisson.lock();
		}finally{
			redisson.unlock();
		}
		eg: redis中setnx()
		try{}finally）解决异常问题;
		设置过期时间（宕机，finally失效，key过期之后依旧可以删除）；
		设置key和超时为原子操作（设置超时之前宕机;）
		锁续命（任务执行时间长，超时时间不够用问题）；

	Redisson:主从架构，Master挂掉，从节点没有同步到加锁的线程，导致锁失效；（Zookeeper可以解决）（通过半数以上的节点加锁成功，才返回加锁成功）
	RedLock(redis中解决方案，需要对等的节点)

### Redis 读写分离Jmeter测试，仅仅是数据读取，TPS 值高于做读写分离 TPS，
另外通过配置Lettuce的Pool,配置之后影响仅仅做数据读取
同时读写情况下，适合

### Rredis读写分离：（spring-cloud-starter-data-redis）
	通过Lettuce提供ReadFrom.SLAVE_PREFERRED可实现

Lettuce运行过程中：
	Starting without optional epoll library
	or
	Starting without optional kqueue library
	Mac系统引入 brew install libevent 
		libevent:支持Linux, BSD,MacOSX,Solaris,Windows
	启动提示：Starting with kqueue library

C10K 并发连接问题：
https://blog.csdn.net/chenrui310/article/details/101685827

FreeBSD: kqueue
Linux: epoll
Windows: IOCP
Solaris: /dev/poll