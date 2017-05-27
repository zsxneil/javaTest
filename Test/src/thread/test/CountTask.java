package thread.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 测试结果：确实会自动分解任务，有点像二分法。但是这个例子的计算速度还没有单线程速度快。。。
 * 
 * Created by rhwayfun on 16-4-6.
 */
public class CountTask extends RecursiveTask<Integer>{

    //阈值
    private static final int THRESHOLD = 100;
    //起始值
    private int start;
    //结束值
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
    	System.out.println(Thread.currentThread().getName());
        boolean compute = (end - start) <= THRESHOLD;
        int res = 0;
        if (compute){
            for (int i = start; i <= end; i++){
                res += i;
            }
        }else {
            //如果长度大于阈值，则分割为小任务
            int mid = (start + end) / 2;
            CountTask task1 = new CountTask(start,mid);
            CountTask task2 = new CountTask(mid + 1, end);
            //计算小任务的值
            task1.fork();
            task2.fork();
            //得到两个小任务的值
            int task1Res = task1.join();
            int task2Res = task2.join();
            res = task1Res + task2Res;
        }
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
    	long currentTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1,100000);
        ForkJoinTask<Integer> submit = pool.submit(task);
        System.out.println("Final result:" + submit.get());
        System.out.println(System.currentTimeMillis()-currentTime);
    }
}
