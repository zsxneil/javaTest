package chardir.test;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;

import ChartDirector.*;

public class Simpleradar 
{
    //Name of demo program
    public String toString() { return "Simple Radar Chart"; }

    //Number of charts produced in this demo
    public int getNoOfCharts() { return 1; }

    //Main code for creating charts
    public void createChart(ChartViewer viewer, int chartIndex)
    {
        // The data for the chart
        double[] data = {8, 6, 6, 5, 4};

        // The labels for the chart
        String[] labels = {"操作系统", "EAS", "数据库", "中间件", "安全"};
        Chart.setLicenseCode("SXZVFNRN9MZ9L8LGA0E2B1BB");
        // Create a PolarChart object of size 450 x 350 pixels
        PolarChart c = new PolarChart(360, 240);

        // Set center of plot area at (225, 185) with radius 150 pixels
        c.setPlotArea(180, 130, 100);

        // Add an area layer to the polar chart
        c.addAreaLayer(data, 0x9999ff);

        // Set the labels to the angular axis as spokes
        c.angularAxis().setLabelStyle("simhei.ttf");
        c.angularAxis().setLabels(labels);
        
        c.radialAxis().setLinearScale(0, 10);
        
        c.makeChart("E:\\MyPicture\\test.png");
        // Output the chart
        viewer.setChart(c);

        //include tool tip for the chart
        viewer.setImageMap(c.getHTMLImageMap("clickable", "", "title='{label}: score = {value}'"));
        
    }

    //Allow this module to run as standalone program for easy testing
    public static void main(String[] args)
    {
       /* //Instantiate an instance of this demo module
    	Simpleradar demo = new Simpleradar();

        //Create and set up the main window
        JFrame frame = new JFrame(demo.toString());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);} });
        frame.getContentPane().setBackground(Color.white);

        // Create the chart and put them in the content pane
        ChartViewer viewer = new ChartViewer();
        demo.createChart(viewer, 0);
        frame.getContentPane().add(viewer);

        // Display the window
        frame.pack();
        frame.setVisible(true);*/
        
        String filePath = "E:\\MyPicture\\test.jpg";
        /*double[] datas = {5, 9, 8, 6, 8};
        createRadarChart(filePath, datas);*/
        LinkedHashMap<String, Double> infoMap = new LinkedHashMap<String, Double>();
        infoMap.put("操作系统", 8.0);
        infoMap.put("EAS", 7.0);
        infoMap.put("数据库", 8.0);
        infoMap.put("中间件", 5.0);
        infoMap.put("安全", 9.0);
        createRadarChart(filePath, infoMap);
    }
    
    public static void createRadarChart(String filePath, double[] datas) {
    	Chart.setLicenseCode("SXZVFNRN9MZ9L8LGA0E2B1BB");
    	String[] labels = {"操作系统", "EAS", "数据库", "中间件", "安全"};
    	
    	// Create a PolarChart object of size 360 x 240 pixels
        PolarChart c = new PolarChart(360, 240);

        // Set center of plot area at (180, 130) with radius 100 pixels
        c.setPlotArea(180, 130, 100);

        // Add an area layer to the polar chart
        c.addAreaLayer(datas, 0x9999ff);

        //设置中文字体，否则会产生乱码
        c.angularAxis().setLabelStyle("simhei.ttf");
        
        // Set the labels to the angular axis as spokes
        c.angularAxis().setLabels(labels);
        
        //设置径向的刻度范围0-10
        c.radialAxis().setLinearScale(0, 10);
        c.radialAxis().setLabelStep(2);
        c.makeChart(filePath);
    }
    
    public static void createRadarChart(String filePath, LinkedHashMap<String, Double> infoMap) {
    	Chart.setLicenseCode("SXZVFNRN9MZ9L8LGA0E2B1BB");
    	//组装label和标签数据
    	List<String> labelList = new ArrayList<>();
    	List<Double> dataList = new ArrayList<>();
    	
    	Set<String> keySet = infoMap.keySet();
    	for (String label : keySet) {
			labelList.add(label);
			dataList.add(infoMap.get(label));
		}
    	
    	double[] datas = new double[dataList.size()];
    	for (int i = 0; i < dataList.size(); i++) {
			datas[i] = dataList.get(i);
		}
    	
    	String[] labels = new String[labelList.size()];
    	for (int i = 0; i < labelList.size(); i++) {
			labels[i] = labelList.get(i);
		}
    	// Create a PolarChart object of size 360 x 240 pixels
    	PolarChart c = new PolarChart(720, 480);
    	
    	// Set center of plot area at (180, 130) with radius 100 pixels
    	c.setPlotArea(360, 260, 200);
    	
    	// Add an area layer to the polar chart
    	c.addAreaLayer(datas, 0x9999ff);
    	
    	//设置中文字体，否则会产生乱码
    	c.angularAxis().setLabelStyle("simhei.ttf");
    	
    	// Set the labels to the angular axis as spokes
    	c.angularAxis().setLabels(labels);
    	
    	//设置径向的刻度范围0-10
    	c.radialAxis().setLinearScale(0, 10, 2);
    	c.radialAxis().setLabelGap(-4);
    	c.makeChart(filePath);
    }
    
    
}

