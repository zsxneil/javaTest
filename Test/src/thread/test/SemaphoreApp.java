package thread.test;

import java.util.Random;
import java.util.concurrent.Semaphore;

//10 个线程都在运行，可以对运行SemaphoreApp的Java进程执行jstack来验证，只有3个线程是活跃的。在一个信号计数器释放之前，其他7个线程都处于空闲状态
public class SemaphoreApp { 

    public static void main(String[] args) {

        // 匿名Runnable实例。定义线程运行程序。
        Runnable limitedCall = new Runnable() { 
            final Random rand = new Random(); 
            final Semaphore available = new Semaphore(3);     // 最多可以发出3个"许可"
            int count = 0; 

            public void run() { 
                int time = rand.nextInt(15); 
                int num = count++; 

                try {
                    available.acquire();    // 当前线程获取"许可"。若没有获取许可，则等待于此。
                    System.out.println("Executing " + "long-running action for " + time + " seconds... #" + num); 
                    Thread.sleep(time * 1000); 
                    System.out.println("Done with #" + num + "!"); 
                } catch (InterruptedException intEx) { 
                    intEx.printStackTrace(); 
                } finally {
                    available.release();     // 当前线程释放"许可"
                }
            } 
        }; 

        for (int i = 0; i < 10; i++) {
            new Thread(limitedCall).start(); 
        }
    }
    
}