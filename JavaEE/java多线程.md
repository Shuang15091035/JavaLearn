多线程产生？
多处理核心，高主频
一个线程在一个时刻只能运行在一个处理器核心上。
线程优先级：
线程的状态：new, runable, blocked, waiting, time_waiting, terminated

###并发编程BlockingQueue（放入和取出都会阻塞）取数据发现没有数据，一直阻塞知道有数据放入

###并发编程锁

###


/**
*
* 并发编程：原子性，可见性，有序性
*volatile：
Java 理论与实践：讲解synchronize(原子性和可见性)和volatile(可见性)的区别
如果严格遵循 volatile 的使用条件 —— 即变量真正独立于其他变量和自己以前的值

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


### 独立的状态变量(CopyOnWriteArrayList线程安全的链表)
private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
private final List<KeyListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();
### 两个不独立的原子类(AtomicInteger)，存在第一个数值小于或等于第二个数值
不能将线程安全性委托给它的线程安全状态变量
### 迭代操作在副本上操作，可避免ConcurrentModificationExection
### 降低锁的竞争程度
    减少锁的持有时间
    降低锁的请求频率
    使用带有协调机制的独占锁

### CompleteFuture使用详解
	> runAsync和supplyAsync方法
		不指定Executor的方法会用ForkJoinPool.commonPool()作为线程指定的代码，指定则使用指定线程池
		runAsync方法不支持返回值
		supplyAsync可以支持返回值
	> 执行完成之后的回调方法
		BiConsumer 可以处理正常或者异常结果
		whenComplete 和 whenCompleteAsync 的区别：
		whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
		whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
	> thenApply 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
		Function<? super T,? extends U>
		T：上一个任务返回结果的类型
		U：当前任务的返回值类型
	> handle 方法
		handle 是执行任务完成时对结果的处理
		handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
	> thenAccept 消费处理结果
		接收任务的处理结果，并消费处理，无返回结果。
	> thenRun 方法
		跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept 。
	> thenCombine 合并任务
		thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
	> thenAcceptBoth
		当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
	> applyToEither 方法
		两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
	> acceptEither 方法
		两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
	> runAfterEither 方法
		两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
	> runAfterBoth
		两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
	> thenCompose 方法
		thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
	> 参考： https://www.jianshu.com/p/6bac52527ca4