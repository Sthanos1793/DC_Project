package bench.cpu;

public class CPUFixedVsFloatingPoint {
    public enum NumberRepresentation {
        FIXED, FLOATING
    }

    private long startTime, endTime;
    
    // Warmup method to stabilize JIT compilation effects
    public void warmup(NumberRepresentation representation, int iterations) {
        run(representation, iterations);
    }

    public void run(NumberRepresentation representation, int iterations) {
        double resultFloating = 0.0;
        int resultFixed = 0;

        startTime = System.nanoTime();
        if (representation == NumberRepresentation.FLOATING) {
            for (int i = 1; i <= iterations; i++) {
                resultFloating += (double) i / 256;
            }
        } else if (representation == NumberRepresentation.FIXED) {
            for (int i = 1; i <= iterations; i++) {
                resultFixed += i >> 8; // Optimized by using bit shift instead of division
            }
        }
        endTime = System.nanoTime();
        
        System.out.println("Result: " + (representation == NumberRepresentation.FLOATING ? resultFloating : resultFixed));
        System.out.println("Time taken for " + representation + ": " + (endTime - startTime) / 1e6 + " ms");
    }

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
