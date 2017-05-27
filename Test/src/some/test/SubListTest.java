package some.test;

import java.util.ArrayList;
import java.util.List;

public class SubListTest {
	public static void main(String[] args) {
		List<Integer> indexArray = new ArrayList<>();
		for(int i=0; i<10; i++) {
			indexArray.add(i);
		}
		System.out.println(indexArray.subList(5, 6));
	}
}
