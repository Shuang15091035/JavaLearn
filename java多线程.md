多线程产生？
多处理核心，高主频
一个线程在一个时刻只能运行在一个处理器核心上。
线程优先级：
线程的状态：new, runable, blocked, waiting, time_waiting, terminated


/**
*
* 并发编程：原子性，可见性，有序性
*
* 有序性：JVM会发生指令重排序（Instruction Reorder）导致在前面的代码后执行
*
* 并发解决方案：？
* 1.缓存
* 2.异步
* 3.并发编程(主要内容)
* 4.分布式
*
* 高并发的指标？
* 1.响应时间
* 2.吞吐量
* 3.QPS/每秒响应请求树
* 4.并发用户数
*
* 解决线程安全问题？(线程安全：多线程之间修改共享数据引起)
* 1.不共享(没变量(无状态)，变量不可用(AKKA),栈封闭)
* 2.共享但加锁（CAS操作(乐观锁)，普通加锁）
* 
* 数据库乐观锁实现方案？
* 1.通过版本号实现
* update t_goods_info
* set amount = amount-#{buys},version = version+1
* where code = #{code} and version = #{version};
* 2.通过状态控制
* update t_goods_info
* set amount = amount-#{buys}
* where code = #{code} and amount-#{buys} >= 0;
*
*/


Java多线程中的一部请求方式：
//先查询交易申请
Callable<JSONArray> applylogic = new Callable<JSONArray>() {
@Override
public JSONArray call() throws Exception {
return JzInterfaceUtil.jzQueryHistoryTradeApplay(applyParamMap).getJSONArray(0);
}
};
//2.可撤单交易列表
Callable<JSONArray> cancleTradeLogic = new Callable<JSONArray>() {
@Override
public JSONArray call() throws Exception {
return JzInterfaceUtil.jzQueryWithdrawalOrderList(custno).getJSONArray(0);
}
};
FutureTask<JSONArray> taskApply = new FutureTask<JSONArray>(applylogic);
FutureTask<JSONArray> taskCancleTrade = new FutureTask<JSONArray>(cancleTradeLogic);
YmThreadUtil.executorService.submit(taskApply);
YmThreadUtil.executorService.submit(taskCancleTrade);
JSONArray applyArr = taskApply.get();  //方法阻塞
JSONArray cancleTradeArr = taskCancleTrade.get();
