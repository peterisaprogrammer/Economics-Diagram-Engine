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

public class KEYNES extends ADAS {
    int ASCurvature;
    int ASPointOfPO;//potential output
    public KEYNES(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient, int ASCurvature, int ASPointOfPO){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
        this.ASCurvature = ASCurvature;
        this.ASPointOfPO = ASPointOfPO;
    }

    @Override
    public JFreeChart createChart(){
        XYSeries AS = new XYSeries("Aggregate Supply (AS)");
        for (int p = 0; p <= 40; p++) {
            AS.add(p, SupplyGradient - ((double) (ASCurvature) /(p - ASPointOfPO)) );
        }

        XYSeries AD = new XYSeries("Aggregate Demand (AD)");
        for (int p = 0; p <= 50; p++) {
            AD.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(AS);
        dataset.addSeries(AD);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Keynes Diagram",
                "rGDP (Y)",
                "Average Price Level (APL)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        double p1 = (SupplyGradient - Demand_Y_Intercept - DemandGradient*ASPointOfPO);
        double p2 = (-ASCurvature - (SupplyGradient - Demand_Y_Intercept)*ASPointOfPO);

        double eqAPL = (-p1 - Math.sqrt(p1*p1 - 4*DemandGradient*p2)) / (2*DemandGradient);
        double eqY = (double) Demand_Y_Intercept - DemandGradient * eqAPL;

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
