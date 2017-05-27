package thread.test;

import java.util.concurrent.CyclicBarrier;

//各省数据独立，分库存偖。为了提高计算性能，统计时采用每个省开一个线程先计算单省结果，最后汇总
public class Total {

    public static void main(String[] args) {
        TotalService totalService = new TotalServiceImpl();
        CyclicBarrier barrier = new CyclicBarrier(5, new TotalTask(totalService));

        // 实际系统是查出所有省编码code的列表，然后循环，每个code生成一个线程。
        new BillTask(new BillServiceImpl(), barrier, "北京").start();
        new BillTask(new BillServiceImpl(), barrier, "上海").start();
        new BillTask(new BillServiceImpl(), barrier, "广西").start();
        new BillTask(new BillServiceImpl(), barrier, "四川").start();
        new BillTask(new BillServiceImpl(), barrier, "黑龙江").start();
    }

}