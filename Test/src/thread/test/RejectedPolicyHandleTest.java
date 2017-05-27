package thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//SynchronousQueue 没有缓存功能，只有传递数据的作用，所以线程会无法处理
public class RejectedPolicyHandleTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new RejectedExecutionHandler() {
            
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 打印并丢弃。
                System.out.println(r.toString()+" is discard");
            }
        });
        
        for(int i=0;i<100;i++){
            MyTask task = new MyTask("Task-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        
        es.shutdown();    // 若无该方法，主线程不会结束。
        
    }
}
