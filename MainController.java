package com.example.CSIA;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;

@RestController
public class MainController {
    @GetMapping("/SandD")
    public Mono<byte[]> SandD(
            @RequestParam(defaultValue = "1") double SandDSupplyGradient,
            @RequestParam(defaultValue = "50") double SandDDemand_Y_Intercept,
            @RequestParam(defaultValue = "1") double SandDDemandGradient

    ){
        try {
            SandDdiagram SandDchart = new SandDdiagram(SandDSupplyGradient, SandDDemand_Y_Intercept,
                    SandDDemandGradient);
            JFreeChart chart = SandDchart.createChart();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(out, chart, 600, 400);
            return Mono.just(out.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/NEC")
    public Mono<byte[]> NEC(){
        try {
            NEC NECchart = new NEC(1, 45, 1,
                    65);
            JFreeChart chartNEC = NECchart.createChart();
            ByteArrayOutputStream outNEC = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outNEC, chartNEC, 600, 400);
            return Mono.just(outNEC.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/PEC")
    public Mono<byte[]> PEC(){
        try {
            PEC PECchart = new PEC(1, 45, 1,
                    65);
            JFreeChart chartPEC = PECchart.createChart();
            ByteArrayOutputStream outPEC = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outPEC, chartPEC, 600, 400);
            return Mono.just(outPEC.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/NEP")
    public Mono<byte[]> NEP(){
        try {
            NEP NEPchart = new NEP(1, 50, 1,
                    10, 10);
            JFreeChart chartNEP = NEPchart.createChart();
            ByteArrayOutputStream outNEP = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outNEP, chartNEP, 600, 400);
            return Mono.just(outNEP.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/PEP")
    public Mono<byte[]> PEP(){
        try {
            PEP PEPchart = new PEP(1, 50, 1,
                    10, 10);
            JFreeChart chartPEP = PEPchart.createChart();
            ByteArrayOutputStream outPEP = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outPEP, chartPEP, 600, 400);
            return Mono.just(outPEP.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
    @GetMapping("/PC")
    public Mono<byte[]> PC(){
        try {
            PC PCchart = new PC(25, 15, 6,
                    0.15f, 24, 25,
                    0.25f);
            JFreeChart chartPC = PCchart.createChart();
            ByteArrayOutputStream outPC = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outPC, chartPC, 600, 400);
            return Mono.just(outPC.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/MonC")
    public Mono<byte[]> MonC(){
        try {
            MonC MonCchart = new MonC(51.4f, 1.95f, 51.4f,
                    1.2f, 15, 6, 0.15f,
                    24, 25, 0.25f);
            JFreeChart chartMonC = MonCchart.createChart();
            ByteArrayOutputStream outMonC = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outMonC, chartMonC, 600, 400);
            return Mono.just(outMonC.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/OLI")
    public Mono<byte[]> OLI(){
        try {
            OLI OLICchart = new OLI(51.4f, 1.95f, 51.4f,
                    1.2f, 15, 6, 0.15f,
                    24, 25, 0.25f);
            JFreeChart chartOLI = OLICchart.createChart();
            ByteArrayOutputStream outOLI = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outOLI, chartOLI, 600, 400);
            return Mono.just(outOLI.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/Mon")
    public Mono<byte[]> Mon(){
        try {
            Mon Monchart = new Mon(51.4f, 1.95f, 51.4f,
                    1.2f, 15, 6, 0.15f,
                    24, 25, 0.25f);
            JFreeChart chartMon = Monchart.createChart();
            ByteArrayOutputStream outMon = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outMon, chartMon, 600, 400);
            return Mono.just(outMon.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/ADAS")
    public Mono<byte[]> ADAS(){
        try {
            ADAS ADASchart = new ADAS(1, 50, 1);
            JFreeChart chartADAS = ADASchart.createChart();
            ByteArrayOutputStream outADAS = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outADAS, chartADAS, 600, 400);
            return Mono.just(outADAS.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/MONE")
    public Mono<byte[]> MONE(){
        try {
            MONE MONEchart = new MONE(1, 50, 1, 25);
            JFreeChart chartMONE = MONEchart.createChart();
            ByteArrayOutputStream outMONE = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outMONE, chartMONE, 600, 400);
            return Mono.just(outMONE.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/KEYNES")
    public Mono<byte[]> KEYNES(){
        try {
            KEYNES KEYNESchart = new KEYNES(25, 67, 1,
                    25, 40);
            JFreeChart chartKEYNES = KEYNESchart.createChart();
            ByteArrayOutputStream outKEYNES = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outKEYNES, chartKEYNES, 600, 400);
            return Mono.just(outKEYNES.toByteArray());
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
