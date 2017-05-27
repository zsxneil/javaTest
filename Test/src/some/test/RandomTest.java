package some.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTest {
	public static void main(String[] args) {
		int perCount = 20;
		List<Integer> indexArray = new ArrayList<>();
		do{	
			int index =new Random().nextInt(5000);
			if (indexArray.contains(index)) {
				continue;
			}
			indexArray.add(index);
			System.out.println(index);
		}while(indexArray.size() <perCount);
	}
}
