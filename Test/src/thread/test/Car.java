package thread.test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private final Semaphore parkingSlot;
    private int carNo;

    public Car(Semaphore parkingSlot, int carNo) {
        this.parkingSlot = parkingSlot;
        this.carNo = carNo;
    }

    public void run() {
        try {
            parkingSlot.acquire();    // 车尝试获取"车位"
            parking();
            sleep(new Random().nextInt(10));
            leaving();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parkingSlot.release();    // 释放"车位"
        }
    }

    private void parking() {
        System.out.println(String.format("%d号车泊车", carNo));
    }

    private void leaving() {
        System.out.println(String.format("%d号车离开车位", carNo));
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
