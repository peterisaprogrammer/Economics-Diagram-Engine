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

public class OLI extends MonC{
    public OLI(double MR_Y_Intercept, double MRGradient, double DARP_Y_Intercept, double DARPGradient,
               double MC_X_coordinate_of_Vertex, double MCVerticalDis, double ACWidthANDDirection,
               double AC_X_coordinate_of_Vertex, double ACVerticalDis, double MCWidthANDDirection){
        super(MR_Y_Intercept, MRGradient, DARP_Y_Intercept, DARPGradient, MC_X_coordinate_of_Vertex,
                MCVerticalDis, ACWidthANDDirection, AC_X_coordinate_of_Vertex, ACVerticalDis,
                MCWidthANDDirection);
    }

    @Override
    public JFreeChart createChart() {
        XYSeries MR = new XYSeries("MR");
        for (int p = 0; p <= 50; p++) {
            MR.add(p, MR_Y_Intercept - MRGradient * p);
        }

        XYSeries DARP = new XYSeries("D=AR=P");
        for (int p = 0; p <= 50; p++) {
            DARP.add(p, DARP_Y_Intercept - DARPGradient * p);
        }

        XYSeries MC = new XYSeries("MC");
        for (int p = 10; p <= 50; p++) {
            MC.add(p, MCWidthANDDirection * Math.pow(p - MC_X_coordinate_of_Vertex, 2) + MCVerticalDis);
        }

        XYSeries AC = new XYSeries("AC");
        for (int p = 0; p <= 50; p++) {
            AC.add(p, ACWidthANDDirection * Math.pow(p - AC_X_coordinate_of_Vertex, 2) + ACVerticalDis);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(MR);
        dataset.addSeries(DARP);
        dataset.addSeries(MC);
        dataset.addSeries(AC);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Oligopolistic Competition",
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
        mainRenderer.setSeriesPaint(3, Color.MAGENTA);
        plot.setRenderer(0, mainRenderer);

        return chart;
    }
}
