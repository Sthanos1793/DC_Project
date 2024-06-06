package testbenching;

import bench.cpu.CPUFixedPoint;

public class TestCPUFixedPoint {
    public static void main(String[] args) {
        CPUFixedPoint benchmark = new CPUFixedPoint();
        int size = 100000;

        System.out.println("Running integer arithmetic test...");
        benchmark.integerArithmeticTest(size);

        System.out.println("Running branching test...");
        benchmark.branchingTest(size);

        System.out.println("Running array access test...");
        benchmark.arrayAccessTest(size);
    }
}
