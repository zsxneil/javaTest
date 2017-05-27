package thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPoolTest {
    
    
    
    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService es = new ThreadPoolExecutor(5,5,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()){

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:" + ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("执行退出");
            }
            
        };
        
        
        for(int i=0;i<100;i++){
            MyTask task = new MyTask("Task-"+i);
            es.execute(task);
        }
        
        Thread.sleep(10);    // 等待terminated()执行
        
        es.shutdown();    // 若无该方法，主线程不会结束。
    }
    
}
