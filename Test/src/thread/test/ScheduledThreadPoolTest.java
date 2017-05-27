package thread.test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleWithFixedDelay(new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date());
            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
    }
    
}