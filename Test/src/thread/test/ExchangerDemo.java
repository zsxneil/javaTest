package thread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;


/**
* 两个线程间的数据交换
* 
* 以下这个程序demo要做的事情就是生产者在交换前生产5个"生产者"，然后再与消费者交换5个数据，然后再生产5个"交换后生产者"，
* 而消费者要在交换前消费5个"消费者"，然后再与生产者交换5个数据，然后再消费5个"交换后消费者"
*/
public class ExchangerDemo {
    private static final Exchanger<List<String>> ex = new Exchanger<List<String>>();
    private static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
    * 内部类，数据生成者
    */
    class DataProducer implements Runnable {
        private List<String> list = new ArrayList<String>();

        public void run() {
            System.out.println("生产者开始生产数据");
            for (int i = 1; i <= 5; i++) {
                System.out.println("生产了第" + i + "个数据，耗时1秒");
                list.add("生产者" + i);
                sleep(1000);
            }
            System.out.println("生产数据结束");
            System.out.println("开始与消费者交换数据");

            try {
                //将数据准备用于交换，并返回消费者的数据
                list = (List<String>) ex.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("结束与消费者交换数据");
            System.out.println("生产者与消费者交换数据后，再生产数据");
            for (int i = 6; i < 10; i++) {
                System.out.println("交换后生产了第" + i + "个数据，耗时1秒");
                list.add("交换后生产者" + i);
                sleep(1000);
            }

            System.out.println("遍历生产者交换后的数据");
            for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
                System.out.println(iterator.next());
            }
        }

    }


    /**
    * 内部类，数据消费者
    */
    class DataConsumer implements Runnable {
        private List<String> list = new ArrayList<String>();

        public void run() {
            System.out.println("消费者开始消费数据");
            for (int i = 1; i <= 4; i++) {
                System.out.println("消费了第" + i + "个数据");
                // 消费者产生数据，后面交换的时候给生产者
                list.add("消费者" + i);
             }

            System.out.println("消费数据结束");
            System.out.println("开始与生产者交换数据");
            try {
                // 进行数据交换，返回生产者的数据
                list = (List<String>) ex.exchange(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("消费者与生产者交换数据后，再消费数据");
            for (int i = 6; i < 10; i++) {
                System.out.println("交换后消费了第" + i + "个数据");
                list.add("交换后消费者" + i);
                sleep(1000);
            }
            sleep(1000);
            System.out.println("开始遍历消费者交换后的数据");

            for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
                System.out.println(iterator.next());
            }
        }
    }

    // 主方法
    public static void main(String args[]) {
        ExchangerDemo et = new ExchangerDemo();
        new Thread(et.new DataProducer()).start();
        new Thread(et.new DataConsumer()).start();
    }
}
