package thread.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一种典型的场景就是火箭发射。在火箭发射前，为了保证万无一失，往往还要进行各项设备、仪器的检查。只有等所有检查完毕后，引擎才能点火。
 * 这种场景就非常适合使用CountDownLatch。它可以使得点火线程，等待所有检查线程全部完工后，再执行。
 * 
 * 例：有三个工人在为老板干活。老板有一个习惯，当三个工人把一天的活都干完了的时候，他就来检查所有工人所干的活。如下代码设计两个类，Worker代表工人，Boss代表老板。
 * @author Administrator
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(3);    // 同步倒数计数器。

        Worker w1 = new Worker(latch, "张三");
        Worker w2 = new Worker(latch, "李四");
        Worker w3 = new Worker(latch, "王五");
        Boss boss = new Boss(latch);

        executor.execute(w3);    // 工人工作。
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(boss);    // 老板工作。

        executor.shutdown();
    }

}


class Worker implements Runnable {
    private CountDownLatch downLatch;
    private String name;

    public Worker(CountDownLatch downLatch, String name) {
        this.downLatch = downLatch;
        this.name = name;
    }

    public void run() {
        this.doWork();    // 工人工作。

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));  // 工作时长。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.name + "活干完了！");
        this.downLatch.countDown();  // 计数减1。
    }

    private void doWork() {
        System.out.println(this.name + "正在干活!");
    }

}


class Boss implements Runnable {
    private CountDownLatch downLatch;

    public Boss(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    public void run() {
        System.out.println("老板正在等所有的工人干完活......");
        try {
            this.downLatch.await();    // 当计数不为0时，线程永远阻塞。为0则继续执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("工人活都干完了，老板开始检查了！");
    }

}
