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

public class PEP extends SandDdiagram {
    private static int MSC_Y_Intercept;
    private static int MPCVerticalDis;

    public PEP(int SupplyGradient, int Demand_Y_Intercept, int DemandGradient, int MSC_Y_Intercept,
               int MPCVerticalDis){
        super(SupplyGradient, Demand_Y_Intercept, DemandGradient);
        PEP.MSC_Y_Intercept = MSC_Y_Intercept;
        PEP.MPCVerticalDis = MPCVerticalDis;
    }

    @Override
    public JFreeChart createChart(){
        XYSeries supplyMPC = new XYSeries("Supply=MPC");
        for (int p = 0; p <= 50; p++) {
            supplyMPC.add(p, SupplyGradient * p + MPCVerticalDis);
        }

        XYSeries MSC = new XYSeries("MSC");
        for (int p = 0; p <= 50; p++) {
            MSC.add(p, (SupplyGradient * p) - MSC_Y_Intercept);
        }

        XYSeries demand = new XYSeries("Demand");
        for (int p = 0; p <= 50; p++) {
            demand.add(p, Demand_Y_Intercept - DemandGradient * p);
        }

        //ap + e = b - cp
        //ap + cp = b - e

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(supplyMPC);
        dataset.addSeries(MSC);
        dataset.addSeries(demand);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Positive externalities of Production",
                "Quantity (Q)",
                "Price (P)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        //equilibrium occurs at Qs = Qd
        // Supply = ap - e, Qd = b-cp, MSC = ap - d
        // ap - e = b - cp
        // ap + cp = b + c
        // p(a+c) = b+c
        //p = (b+c)/(a+c)
        //(a+p)c = b
        //a+p = b/c
        //p = (b/c)-a

        //b - cp = ap - d
        // b + d = ap + cp
        // b + d = p(a+c)
        //

        //ap + e = b - cp
        //ap + cp = b - e
        //p(a+c) = b+e
        //p = (b+e)/(a+c)
        double eqPriceM = (double) (Demand_Y_Intercept-MPCVerticalDis)/(SupplyGradient+DemandGradient);
        double eqQtyM = (SupplyGradient * eqPriceM) + MPCVerticalDis;

        double eqPriceOPT = (double) (Demand_Y_Intercept+MSC_Y_Intercept)/(SupplyGradient+DemandGradient);
        double eqQtyOPT = (SupplyGradient * eqPriceOPT) - MSC_Y_Intercept;

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
