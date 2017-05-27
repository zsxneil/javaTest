package jfreechart.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class DrawTest {
	private DefaultCategoryDataset createDataSet2(Map<String, Map<String, Object>> resultMap, List<String> dimNameList) {  
	       DefaultCategoryDataset dataset = new DefaultCategoryDataset();//创建默认的种类数据类型就可以了，蜘蛛图的每个维度可以看成一种类型  
	       Set<String> keySet = resultMap.keySet();  
	       for(String key : keySet){  
	    Map<String,Object> infoMap = resultMap.get(key);  
	    String vendorCode = key.split("&")[0];  
	    String vendorName = key.split("&")[1];  
	    for(String dimName : dimNameList){  
	            if(infoMap.get(dimName)==null){  
	        continue;  
	            }  
	           double score = (Double) infoMap.get(dimName);  
	          dataset.addValue(score, vendorName.trim()      +"("+vendorCode.trim()+")", dimName);  
	    }  
	        }  
	        return dataset;  
	}  
	
	public static JFreeChart createChart2(DefaultCategoryDataset dataSet) {  
	    CalibrationSpiderWebPlot plot = new CalibrationSpiderWebPlot(dataSet);  
	    JFreeChart chart = new JFreeChart("维度得分分析", plot);  
	    return chart;  
	}  
	
	public static void main(String[] args) {
		
	}
}
