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

public class MonC {
    double MR_Y_Intercept;
    double MRGradient;
    double DARP_Y_Intercept;
    double DARPGradient;
    double MC_X_coordinate_of_Vertex;
    double MCVerticalDis;
    double ACWidthANDDirection;
    double AC_X_coordinate_of_Vertex;
    double ACVerticalDis;
    double MCWidthANDDirection;

    public MonC(double MR_Y_Intercept, double MRGradient, double DARP_Y_Intercept, double DARPGradient,
                double MC_X_coordinate_of_Vertex, double MCVerticalDis, double ACWidthANDDirection,
                double AC_X_coordinate_of_Vertex, double ACVerticalDis, double MCWidthANDDirection) {
        this.MR_Y_Intercept = MR_Y_Intercept;
        this.MRGradient = MRGradient;
        this.DARP_Y_Intercept = DARP_Y_Intercept;
        this.DARPGradient = DARPGradient;
        this.MC_X_coordinate_of_Vertex = MC_X_coordinate_of_Vertex;
        this.MCVerticalDis = MCVerticalDis;
        this.ACWidthANDDirection = ACWidthANDDirection;
        this.AC_X_coordinate_of_Vertex = AC_X_coordinate_of_Vertex;
        this.ACVerticalDis = ACVerticalDis;
        this.MCWidthANDDirection = MCWidthANDDirection;
    }

    public JFreeChart createChart() {
        XYSeries MR = new XYSeries("MR");
        for (int p = 0; p <= 50; p++) {
            double MRvalue = MR_Y_Intercept - MRGradient * p;
            if (MRvalue > 0){
                MR.add(p, MRvalue);
            }
        }

        XYSeries DARP = new XYSeries("D=AR=P");
        for (int p = 0; p <= 50; p++) {
            double DARPvalue = DARP_Y_Intercept - DARPGradient * p;
            if (DARPvalue > 0) {
                DARP.add(p, DARPvalue);
            }

        }

        XYSeries MC = new XYSeries("MC");
        for (int p = 10; p <= 50; p++) {
            double MCvalue = MCWidthANDDirection * Math.pow(p - MC_X_coordinate_of_Vertex, 2) + MCVerticalDis;
            if (MCvalue < 200) {
                MC.add(p, MCvalue);
            }
        }

        XYSeries AC = new XYSeries("AC");
        for (int p = 0; p <= 50; p++) {
            double ACvalue = ACWidthANDDirection * Math.pow(p - AC_X_coordinate_of_Vertex, 2) + ACVerticalDis;
            if (ACvalue < 200) {
                AC.add(p, ACvalue);
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(MR);
        dataset.addSeries(DARP);
        dataset.addSeries(MC);
        dataset.addSeries(AC);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Monopolistic Competition",
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
