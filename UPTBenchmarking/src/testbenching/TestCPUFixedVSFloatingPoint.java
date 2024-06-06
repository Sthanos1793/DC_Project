package testbenching;

import bench.cpu.CPUFixedVsFloatingPoint;
import bench.cpu.CPUFixedVsFloatingPoint.NumberRepresentation;

public class TestCPUFixedVSFloatingPoint {
    public static void main(String[] args) {
        CPUFixedVsFloatingPoint benchmark = new CPUFixedVsFloatingPoint();
        int iterations = 1000000;

        System.out.println("Warming up...");
        benchmark.warmup(NumberRepresentation.FLOATING, iterations);
        benchmark.warmup(NumberRepresentation.FIXED, iterations);

        System.out.println("Running benchmarks...");
        benchmark.run(NumberRepresentation.FLOATING, iterations);
        benchmark.run(NumberRepresentation.FIXED, iterations);
    }
}