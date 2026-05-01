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

public class MONE extends ADAS {
    private int LRAS_X_coordinate;

    public MONE(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient, int LRAS_X_coordinate){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
        this.LRAS_X_coordinate = LRAS_X_coordinate;
    }

    @Override
    public JFreeChart createChart(){
        XYSeries SRAS = new XYSeries("Short-run Aggregate Supply (AS)");
        for (int p = 0; p <= 50; p++) {
            SRAS.add(p, SupplyGradient * p);
        }

        XYSeries AD = new XYSeries("Aggregate Demand (AD)");
        for (int p = 0; p <= 50; p++) {
            AD.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeries LRAS = new XYSeries("Long-run Aggregate Supply");
        for (int p = 0; p <= 200; p++){
            LRAS.add(LRAS_X_coordinate, p);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(SRAS);
        dataset.addSeries(AD);
        dataset.addSeries(LRAS);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Monetarist / New Classical",
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
        mainRenderer.setSeriesPaint(2, Color.BLACK);
        plot.setRenderer(0, mainRenderer);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10));
        plot.setRenderer(1, renderer);

        return chart;
    }
}
