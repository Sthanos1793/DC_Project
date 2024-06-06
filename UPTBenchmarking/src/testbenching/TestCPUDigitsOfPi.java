package testbenching;

import bench.cpu.CPUDigitsOfPi;

public class TestCPUDigitsOfPi {
    public static void main(String[] args) {
        try {
            CPUDigitsOfPi piBenchmark = new CPUDigitsOfPi();
            piBenchmark.initialize(100); // Number of digits for Pi computation
            piBenchmark.warmup();
            int iterations = 10; // Number of benchmark iterations
            piBenchmark.run(iterations);
            piBenchmark.clean();
        } catch (Exception e) {
            System.out.println("Error in main method: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
