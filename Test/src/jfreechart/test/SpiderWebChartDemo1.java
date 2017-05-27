package jfreechart.test;


import java.awt.Dimension;   
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;   

import org.jfree.chart.ChartPanel;   
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.title.LegendTitle;   
import org.jfree.chart.title.TextTitle;   
import org.jfree.data.category.CategoryDataset;   
import org.jfree.data.category.DefaultCategoryDataset;   
import org.jfree.ui.*;   
   
public class SpiderWebChartDemo1 extends ApplicationFrame   
{   
   
    public SpiderWebChartDemo1(String s)   
    {   
        super(s);   
        CategoryDataset categorydataset = createDataset();   
        JFreeChart jfreechart = createChart(categorydataset);   
        ChartPanel chartpanel = new ChartPanel(jfreechart);   
        chartpanel.setPreferredSize(new Dimension(500, 270));   
        setContentPane(chartpanel);   
    }   
   
    private static CategoryDataset createDataset()   
    {   
        String s = "deff";   
        String s1 = "Second";   
        String s2 = "Third";   
        String s3 = "Category 1";   
        String s4 = "Category 2";   
        String s5 = "Category 3";   
        String s6 = "Category 4";   
        String s7 = "Category 5";   
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();   
        defaultcategorydataset.addValue(1.0D, s, s3);   
        defaultcategorydataset.addValue(4D, s, s4);   
        defaultcategorydataset.addValue(3D, s, s5);   
        defaultcategorydataset.addValue(5D, s, s6);   
        defaultcategorydataset.addValue(5D, s, s7);   
        defaultcategorydataset.addValue(5D, s1, s3);   
        defaultcategorydataset.addValue(7D, s1, s4);   
        defaultcategorydataset.addValue(6D, s1, s5);   
        defaultcategorydataset.addValue(8D, s1, s6);   
        defaultcategorydataset.addValue(4D, s1, s7);   
        defaultcategorydataset.addValue(4D, s2, s3);   
        defaultcategorydataset.addValue(3D, s2, s4);   
        defaultcategorydataset.addValue(2D, s2, s5);   
        defaultcategorydataset.addValue(3D, s2, s6);   
        defaultcategorydataset.addValue(6D, s2, s7);   
        return defaultcategorydataset;   
    } 
    
    
    private static CategoryDataset createDataset2()   
    {   
    	String s = "得分";   
    	 
    	String s3 = "操作系统";   
    	String s4 = "EAS";   
    	String s5 = "数据库";   
    	String s6 = "中间件";   
    	String s7 = "网络";   
    	String s8 = "安全";   
    	DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();   
    	defaultcategorydataset.addValue(4D, s, s3);   
    	defaultcategorydataset.addValue(8D, s, s4);   
    	defaultcategorydataset.addValue(7D, s, s5);   
    	defaultcategorydataset.addValue(9D, s, s6);   
    	defaultcategorydataset.addValue(9D, s, s7);   
    	defaultcategorydataset.addValue(5D, s, s8);   
    	return defaultcategorydataset;   
    }   
   
    private static JFreeChart createChart(CategoryDataset categorydataset)   
    {   
        //SpiderWebPlot spiderwebplot = new SpiderWebPlot(categorydataset);   
    	CalibrationSpiderWebPlot spiderwebplot = new CalibrationSpiderWebPlot(categorydataset);   
        JFreeChart jfreechart = new JFreeChart("总体评分", TextTitle.DEFAULT_FONT, spiderwebplot, false);   
        LegendTitle legendtitle = new LegendTitle(spiderwebplot);   
        legendtitle.setPosition(RectangleEdge.BOTTOM);   
        jfreechart.addSubtitle(legendtitle); 
        return jfreechart;   
    }   
   
    public static JPanel createDemoPanel()   
    {   
        JFreeChart jfreechart = createChart(createDataset());   
        return new ChartPanel(jfreechart);   
    }   
   
    public static void main(String args[])   
    {   
        /*SpiderWebChartDemo1 spiderwebchartdemo1 = new SpiderWebChartDemo1("Spider Chart Demo");   
        spiderwebchartdemo1.pack();   
        RefineryUtilities.centerFrameOnScreen(spiderwebchartdemo1);   
        spiderwebchartdemo1.setVisible(true); */
        
        
        CategoryDataset categorydataset = createDataset2();   
        JFreeChart jfreechart = createChart(categorydataset);
        File dir = new File("E:\\MyPicture\\");   
        if (!dir.exists()) {  
            dir.mkdir();  
        }  
        String fName = String.valueOf(System.currentTimeMillis())+"SprideChart.png";  
        File file = new File("E:\\MyPicture\\", fName);  
        try {
            ChartUtilities.saveChartAsPNG(file, jfreechart, 400, 250);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//生成一个png图片 
        //ChartUtilities.saveChartAsJPEG(file, chart, width, height);
    }   
}   
