package thread.test;

public class MyTask implements Runnable {
    public String name;
    
    public MyTask(String name) {
        super();
        this.name = name;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("执行中:"+this.name);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
