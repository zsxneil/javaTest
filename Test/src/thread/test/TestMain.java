package thread.test;

public class TestMain {

    public static void main(String[] args) {
        TestSync test = new TestSync();

        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        Thread t3 = new Thread(test);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
