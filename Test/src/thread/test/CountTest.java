package thread.test;

public class CountTest {
	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		int sum=0;
		for(int i=0; i<=10000000; i++) {
			sum += i;
		}
		System.out.println(sum);
		System.out.println(System.currentTimeMillis()-currentTime);
	}
}
