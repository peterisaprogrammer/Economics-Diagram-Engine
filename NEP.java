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

public class NEP extends SandDdiagram {
    private static int MPC_Y_Intercept;
    private static int MSCVerticalDis;

    public NEP(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient, int MPC_Y_Intercept,
               int MSCVerticalDis){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
        NEP.MPC_Y_Intercept = MPC_Y_Intercept;
        NEP.MSCVerticalDis = MSCVerticalDis;
    }

    @Override
    public JFreeChart createChart(){
        XYSeries MSC = new XYSeries("MSC");
        for (int p = 0; p <= 50; p++) {
            MSC.add(p, SupplyGradient * p + MSCVerticalDis);
        }

        XYSeries SupplyMPC = new XYSeries("Supply=MPC");
        for (int p = 0; p <= 50; p++) {
            SupplyMPC.add(p, (SupplyGradient * p) - MPC_Y_Intercept);
        }

        XYSeries demand = new XYSeries("Demand");
        for (int p = 0; p <= 50; p++) {
            demand.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(SupplyMPC);
        dataset.addSeries(MSC);
        dataset.addSeries(demand);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Negative externalities of Production",
                "Quantity (Q)",
                "Price (P)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        double eqPriceM = (double) (Demand_Y_Intercept-MSCVerticalDis)/(SupplyGradient+DemandGradient);
        double eqQtyM = (SupplyGradient * eqPriceM) + MSCVerticalDis;

        double eqPriceOPT = (double) (Demand_Y_Intercept+MPC_Y_Intercept)/(SupplyGradient+DemandGradient);
        double eqQtyOPT = (SupplyGradient * eqPriceOPT) - MPC_Y_Intercept;

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
        plot.setDataset(1, eqDatasetM);
        XYSeriesCollection eqDatasetOPT = new XYSeriesCollection(eqPointOPT);
        plot.setDataset(2, eqDatasetOPT);

        XYLineAndShapeRenderer mainRenderer = new XYLineAndShapeRenderer(true, false);
        mainRenderer.setSeriesPaint(0, Color.BLUE);
        mainRenderer.setSeriesPaint(1, Color.RED);
        mainRenderer.setSeriesPaint(2, Color.BLACK);
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
