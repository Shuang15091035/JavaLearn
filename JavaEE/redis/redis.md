Redis内存碎片化问题：
	Redis内存碎片化的原因：每天大量数据写入，这些数据key和原来的数据的key不一致，Redis本身会通过lru策略将部分旧数据淘汰，而有效数据却没有被Redis进程释放。
	目前较好的解决方案：对机器设置内存监控，超过95%内存使用率重启Redis，回收内存。
Redis执行make时报Jemalloc/jemalloc.h没有这个文件和或目录：
	make MALLOC=libc

Redis如何做持久化的：
	bgsave做镜像全量持久化，aof做增量持久化。因为bgsave会耗费较长时间，不够实时，在停机的时候会导致大量丢失数据，所以需要aof来配合使用。在Redis实例重启是，会使用bgsave持久化文件重新构造内存，再使用aof重放近期的操作指令来实现完整恢复重启之前的状态。