package thread.test;

class TestSync implements Runnable {

    Timer timer = new Timer();

    public void run() {
        timer.add(Thread.currentThread().getName());
    }

}
