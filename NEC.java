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

public class NEC extends SandDdiagram {
    static int MPB_Y_Intercept;

    public NEC(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient, int MPB_Y_Intercept){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
        NEC.MPB_Y_Intercept = MPB_Y_Intercept;
    }

    @Override
    public JFreeChart createChart(){
        XYSeries supply = new XYSeries("Supply");
        for (int p = 0; p <= 50; p++) {
            supply.add(p, SupplyGradient * p);
        }

        XYSeries demandMSB = new XYSeries("MSB");
        for (int p = 0; p <= 50; p++) {
            demandMSB.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeries demandMPB = new XYSeries("D=MPB");
        for (int p = 0; p <= 50; p++) {
            demandMPB.add(p, MPB_Y_Intercept - DemandGradient * p);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(supply);
        dataset.addSeries(demandMSB);
        dataset.addSeries(demandMPB);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Negative Externalities of Consumption",
                "Quantity (Q)",
                "Price (P)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        //equilibrium occurs at Qs = Qd
        // Qs = ap, Qd = b-cp
        //ac+cp = b
        //(a+p)c = b
        //a+p = b/c
        //p = (b/c)-a
        double eqPriceM = (double) Demand_Y_Intercept/(SupplyGradient+DemandGradient);
        double eqQtyM = (double) SupplyGradient * eqPriceM;

        double eqPriceOPT = (double) MPB_Y_Intercept/(SupplyGradient+DemandGradient);
        double eqQtyOPT = (double) SupplyGradient * eqPriceOPT;

        XYPlot plot = chart.getXYPlot();
        XYSeries eqPointM = new XYSeries("Equilibrium Market");
        eqPointM.add(eqPriceM, eqQtyM);
        XYSeries eqPointOPT = new XYSeries("Equilibrium Optimum");
        eqPointOPT.add(eqPriceOPT, eqQtyOPT);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(0.0, 55.0);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0.0, 55.0);

        XYSeriesCollection eqDatasetM = new XYSeriesCollection(eqPointM);
        XYSeriesCollection eqDatasetOPT = new XYSeriesCollection(eqPointOPT);
        plot.setDataset(1, eqDatasetM);
        plot.setDataset(2, eqDatasetOPT);

        XYLineAndShapeRenderer mainRenderer = new XYLineAndShapeRenderer(true, false);
        mainRenderer.setSeriesPaint(0, Color.BLUE);
        mainRenderer.setSeriesPaint(1, Color.BLACK);
        mainRenderer.setSeriesPaint(2, Color.RED);
        plot.setRenderer(0, mainRenderer);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10));
        plot.setRenderer(1, renderer);

        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false, true);
        renderer1.setSeriesPaint(0, Color.BLACK);
        renderer1.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5, -5, 10, 10));
        plot.setRenderer(2, renderer1);

        return chart;
    }
}
