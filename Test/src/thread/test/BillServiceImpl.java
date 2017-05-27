package thread.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class BillServiceImpl implements BillService{

    @Override
    public void bill(String code) {
    	try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
