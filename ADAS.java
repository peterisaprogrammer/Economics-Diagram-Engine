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

public class ADAS extends SandDdiagram {
    public ADAS(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
    }

    @Override
    public JFreeChart createChart(){
        XYSeries AS = new XYSeries("Aggregate Supply (AS)");
        for (int p = 0; p <= 50; p++) {
            AS.add(p, SupplyGradient * p);
        }

        XYSeries AD = new XYSeries("Aggregate Demand (AD)");
        for (int p = 0; p <= 50; p++) {
            AD.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(AS);
        dataset.addSeries(AD);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "AD/AS Diagram",
                "rGDP (Y)",
                "Average Price Level (APL)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        double eqAPL = (double) Demand_Y_Intercept/(SupplyGradient+DemandGradient);
        double eqY = (double) SupplyGradient * eqAPL;

        XYPlot plot = chart.getXYPlot();
        XYSeries eqPoint = new XYSeries("Equilibrium");
        eqPoint.add(eqAPL, eqY);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0.0, 55.0);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0.0, 55.0);

        XYSeriesCollection eqDataset = new XYSeriesCollection(eqPoint);
        plot.setDataset(1, eqDataset);

        XYLineAndShapeRenderer mainRenderer = new XYLineAndShapeRenderer(true, false);
        mainRenderer.setSeriesPaint(0, Color.BLUE);
        mainRenderer.setSeriesPaint(1, Color.RED);
        plot.setRenderer(0, mainRenderer);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10));
        plot.setRenderer(1, renderer);

        return chart;
    }
}
