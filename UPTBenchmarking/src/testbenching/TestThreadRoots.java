package testbenching;

import bench.cpu.CPUThreadRoots;

public class TestThreadRoots {
    public static void main(String[] args) {
        int workload = 10000; // Example workload
        int[] threadCounts = {1, 2, 4, 8}; // Example thread counts

        for (int numThreads : threadCounts) {
            CPUThreadRoots benchmark = new CPUThreadRoots();
            benchmark.initialize(workload, numThreads);

            long startTime = System.currentTimeMillis();
            benchmark.run();
            long endTime = System.currentTimeMillis();

            long duration = endTime - startTime;
            System.out.println("Runtime with " + numThreads + " threads: " + duration + " ms");
        }
    }
}
