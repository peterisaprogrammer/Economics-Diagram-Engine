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

public class SandDdiagram {
    protected double SupplyGradient;
    protected double Demand_Y_Intercept;
    protected double DemandGradient;

    public SandDdiagram(double SupplyGradient, double Demand_Y_Intercept, double DemandGradient) {
        this.SupplyGradient = SupplyGradient;
        this.Demand_Y_Intercept = Demand_Y_Intercept;
        this.DemandGradient = DemandGradient;
    }

    public JFreeChart createChart() {
        double eqPrice = Demand_Y_Intercept / (SupplyGradient + DemandGradient);
        double eqQty = SupplyGradient * eqPrice;

        XYSeries supply = new XYSeries("Supply");
        for (int p = 0; p <= 50; p++) {
            supply.add(p, SupplyGradient * p);
        }

        XYSeries demand = new XYSeries("Demand");
        for (int p = 0; p <= 50; p++) {
            demand.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeries eqQtyLine = new XYSeries("Equilibrium quantity line");
        eqQtyLine.add(0, eqQty);
        eqQtyLine.add(eqPrice, eqQty);

        XYSeries eqPriceLine = new XYSeries("Equilibrium price line");
        eqPriceLine.add(eqPrice, 0);
        eqPriceLine.add(eqPrice, eqQty);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(supply);
        dataset.addSeries(demand);
        dataset.addSeries(eqQtyLine);
        dataset.addSeries(eqPriceLine);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Supply and Demand",
                "Quantity (Q)",
                "Price (P)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

            //equilibrium occurs at Qs = Qd
            // Qs = (SupplyGradient)*p, Qd =(Demand_Y_Intercept)-(DemandGradient)*p
            //(SupplyGradient)*(DemandGradient)+(DemandGradient)*p =Demand_Y_Intercept
            //(SupplyGradoent+p)(DemandGradient) =Demand_Y_Intercept
            //SupplyGradient+p =(Demand_Y_Intercept)/(DemandGradient)
            //p = (Demand_Y_Intercept/DemandGradient)-SupplyGradient

        XYPlot plot = chart.getXYPlot();
        XYSeries eqPoint = new XYSeries("Equilibrium");
        eqPoint.add(eqPrice, eqQty);

        //boundaries
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0.0, 55.0);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0.0, 55.0);

        XYSeriesCollection eqDataset = new XYSeriesCollection(eqPoint);
        plot.setDataset(1, eqDataset);

        XYLineAndShapeRenderer mainRenderer = new XYLineAndShapeRenderer(true, false);
        mainRenderer.setSeriesPaint(0, Color.BLUE);
        mainRenderer.setSeriesPaint(1, Color.RED);
        mainRenderer.setSeriesPaint(2, Color.GREEN);
        mainRenderer.setSeriesPaint(3, Color.GREEN);
        plot.setRenderer(0, mainRenderer);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10));
        plot.setRenderer(1, renderer);

        return chart;
    }
}