package some.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {
	
	public static void main(String[] args) {
		Map<String, Object> orgMap = new HashMap<>();
		for(int i=0; i<100;i++) {
			orgMap.put(i + "", null);
		}
		
		Map<String, Object> colMap = new HashMap<>();
		
		int processNum = Runtime.getRuntime().availableProcessors();
		for(int i=0; i<processNum; i++) {
			Map<String, Object> subMap = new HashMap<>();
			colMap.put(i + "", subMap);
		}
		
		int mapIndex = 0;
		int subIndex = 0;
		for (Entry<String, Object> orgEntry : orgMap.entrySet()) {
			subIndex = mapIndex % processNum;
			((Map<String, Object>)colMap.get(subIndex + "")).put(orgEntry.getKey(),orgEntry.getValue());
			mapIndex ++;
		}
		
		for (Entry<String, Object> orgEntry : colMap.entrySet()) {
			Map<String, Object> subMap = (Map<String, Object>) orgEntry.getValue();
			for (Entry<String, Object> subEntry : subMap.entrySet()) {
				System.out.println(subEntry.getKey());
			}
			System.out.println("*********************");
		}
	}
}
