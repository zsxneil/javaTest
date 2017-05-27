package chardir.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ChartDirector.*;

public class RingChart 
{
    //Name of demo program
    public String toString() { return "Donut Chart"; }

    //Number of charts produced in this demo
    public int getNoOfCharts() { return 1; }

    //Main code for creating charts
    public void createChart(ChartViewer viewer, int chartIndex)
    {
    	Chart.setLicenseCode("SXZVFNRN9MZ9L8LGA0E2B1BB");
        // The data for the pie chart
        double[] data = {25, 18};

        // The labels for the pie chart
        String[] labels = {"已超过", "未超过"};

        // Create a PieChart object of size 600 x 320 pixels. Set background color to brushed
        // silver, with a 2 pixel 3D border. Use rounded corners of 20 pixels radius.
        PieChart c = new PieChart(270, 220, Chart.CColor(new java.awt.Color(255,255,255)), Chart.Transparent, 0);
        c.setRoundedFrame(0xffffff, 0);

        // Add a title using 18pt Times New Roman Bold Italic font. #Set top/bottom margins to 8
        // pixels.
        TextBox title = c.addTitle("检测", "simhei.ttf", 14);
        title.setMargin2(0, 0, 8, 8);

        // Add a 2 pixels wide separator line just under the title
        //c.addLine(10, title.getHeight(), c.getWidth() - 11, title.getHeight(), Chart.LineColor, 2);

        // Set donut center at (160, 175), and outer/inner radii as 110/55 pixels
        c.setDonutSize(135, 97, 55, 35);

        // Set the pie data and the pie labels
        c.setData(data, labels);
        
        //给圆环设置特定的颜色
        int[] colors = {Chart.CColor(new java.awt.Color(75,172,197)),Chart.CColor(new java.awt.Color(155,187,88))};
        c.setColors2(Chart.DataColor, colors);
        // Use ring shading effect for the sectors
        c.setSectorStyle(Chart.RadialShading);
        
        // Use the side label layout method, with the labels positioned 16 pixels from the donut
        // bounding box
        c.setLabelLayout(Chart.SideLayout, 20);

        // Show only the sector number as the sector label
        c.setLabelFormat("{percent}%");

        // Set the sector label style to Arial Bold 10pt, with a dark grey (444444) border
        TextBox t = c.setLabelStyle("Arial Bold", 10, 0x000000);
        t.setBackground(Chart.SameAsMainColor, Chart.Transparent, Chart.softLighting(Chart.Right, 0)
            );
        t.setRoundedCorners(4);

        // Add a legend box, with the center of the left side anchored at (330, 175), and using 10pt
        // Arial Bold Italic font
        LegendBox b = c.addLegend(135, 195, true, "simhei.ttf", 10);
        b.setAlignment(Chart.BottomCenter);

        // Set the legend box border to dark grey (444444), and with rounded conerns
        b.setBackground(Chart.Transparent, 0x444444);
        b.setRoundedCorners();

        // Set the legend box margin to 16 pixels, and the extra line spacing between the legend
        // entries as 5 pixels
        b.setMargin(8);
        b.setKeySpacing(0, 5);
        //设置图例的列数
        b.setCols(2);
        // Set the legend text to show the sector number, followed by a 120 pixels wide block
        // showing the sector label, and a 40 pixels wide block showing the percentage
        //b.setText(
         //   "<*block,valign=top*>{={sector}+1}.<*advanceTo=22*><*block,width=120*>{label}<*/*>" +
         //   "<*block,width=40,halign=right*>{percent}<*/*>%");
        b.setText(
           "<*block,valign=top*><*advanceTo=11*><*block,width=50*>{label}<*/*>");
        c.makeChart("E:\\MyPicture\\ring.jpg"); 

        // Output the chart
        viewer.setChart(c);

        //include tool tip for the chart
        viewer.setImageMap(c.getHTMLImageMap("clickable", "",
            "title='{label}: US${value}K ({percent}%)'"));
    }

    //Allow this module to run as standalone program for easy testing
    public static void main(String[] args)
    {
        //Instantiate an instance of this demo module
    	RingChart demo = new RingChart();

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
        frame.setVisible(true);
    }
}

