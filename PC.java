package com.example.CSIA;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class PC {
    int MRDARP_Y_Intercept;
    int MC_X_coordinate_of_Vertex;
    int MCVerticalDis;
    double ACWidthANDDirection;
    int AC_X_coordinate_of_Vertex;
    int ACVerticalDis;
    double MCWidthANDDirection;

    public PC(int MRDARP_Y_Intercept, int MC_X_coordinate_of_Vertex, int MCVerticalDis,
              double ACWidthANDDirection, int AC_X_coordinate_of_Vertex, int ACVerticalDis,
              double MCWidthANDDirection){
        this.MRDARP_Y_Intercept = MRDARP_Y_Intercept;
        this.MC_X_coordinate_of_Vertex = MC_X_coordinate_of_Vertex;
        this.MCVerticalDis = MCVerticalDis;
        this.ACWidthANDDirection = ACWidthANDDirection;
        this.AC_X_coordinate_of_Vertex = AC_X_coordinate_of_Vertex;
        this.ACVerticalDis = ACVerticalDis;
        this.MCWidthANDDirection = MCWidthANDDirection;
    }

    public JFreeChart createChart(){
        XYSeries MRDARP = new XYSeries("MR=D=AR=P");
        for (int p = 0; p <= 50; p++) {
            MRDARP.add(p, MRDARP_Y_Intercept);
        }

        XYSeries MC = new XYSeries("MC");
        for (int p = 10; p <= 50; p++) {
            MC.add(p, MCWidthANDDirection * Math.pow(p-MC_X_coordinate_of_Vertex, 2) + MCVerticalDis);
        }

        XYSeries AC = new XYSeries("AC");
        for (int p = 0; p <= 50; p++){
            AC.add(p, ACWidthANDDirection * Math.pow(p-AC_X_coordinate_of_Vertex, 2) + ACVerticalDis);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(MRDARP);
        dataset.addSeries(MC);
        dataset.addSeries(AC);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Perfect Competition",
                "Quantity (Q)",
                "Price (P)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0.0, 55.0);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0.0, 55.0);

        XYLineAndShapeRenderer mainRenderer = new XYLineAndShapeRenderer(true, false);
        mainRenderer.setSeriesPaint(0, Color.BLUE);
        mainRenderer.setSeriesPaint(1, Color.BLACK);
        mainRenderer.setSeriesPaint(2, Color.RED);
        plot.setRenderer(0, mainRenderer);

        return chart;
    }
}
