锁：
* 多任务中需要
*  任务都需要对同一资源进行写操作
* 对资源的访问的互斥的
zookeeper实现热加载配置中心

tickTime:滴答声（服务心跳时间毫秒）最小回话超时时间为tickTime两倍
dataDir：存储数据库快照的地方，
clientPort：倾听客户端连接端口
initLimit：仲裁中Zookeeper服务器连接到lead的时间长度，
syncLimit：限制服务器与lead的过期时间
ZK数据模型：
	临时节点不允许有孩子：
	容器节点：存储leader，lock等配方。
	TTL节点：毫秒为单位通过System启动TTL节点
ZK时间：
	Zxid：标记zk状态的每次更改，具有唯一性
	版本号：对节点的更改导致节点版本号之一增加，Znode更改次数，cversion（znode子项的更改次数）aversion（znode的ACL更改次数）
	Ticks：多服务器ZK，通过滴答定义时间时间，状态上载，回话超时，对等体之间连接超时
ZKSession:
	授权失败，会话过期，连接丢失（关闭调用，失去连接，）
ZKWatches: 
	一次性触发：watch的节点如果删除客户端会接收到一次监视时间，如果再次更改节点，客户端需要重新监视读取，否则不会发送监视时间
	，发送到客户端，手表设置的数据
ACL：
	CREATE
	READ
	WRITE
	DELETE
	ADMIN