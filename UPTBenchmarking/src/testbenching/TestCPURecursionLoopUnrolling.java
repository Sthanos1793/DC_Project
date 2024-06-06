package testbenching;

import bench.cpu.CPURecursionLoopUnrolling;

public class TestCPURecursionLoopUnrolling {

    public static void main(String[] args) {
        CPURecursionLoopUnrolling benchmark = new CPURecursionLoopUnrolling();

        // Example benchmark runs
        benchmark.run(new Object[]{false}); // no unrolling
        benchmark.run(new Object[]{true, 1}); // unrolling of level 1
        benchmark.run(new Object[]{true, 5}); // unrolling of level 5
        benchmark.run(new Object[]{true, 15}); // unrolling of level 15
    }

}
