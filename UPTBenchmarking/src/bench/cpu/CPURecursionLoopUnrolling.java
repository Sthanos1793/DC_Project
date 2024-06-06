package bench.cpu;

public class CPURecursionLoopUnrolling {
    // Method to compute the sum of primes using recursion without unrolling
    private long recursive(long start, long size, int counter) {
        try {
            return recursiveHelper(start, size, counter);
        } catch (StackOverflowError e) {
            System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0; // Return 0 or a partial result if desired
        }
    }

    private long recursiveHelper(long start, long size, int counter) {
        if (start > size) {
            return 0;
        }

        long sum = 0;
        if (isPrime(start)) {
            sum += start;
        }

        return sum + recursiveHelper(start + 1, size, counter + 1);
    }

    // Method to compute the sum of primes using recursion with loop unrolling
    private long recursiveUnrolled(long start, int unrollLevel, long size, int counter) {
        long sum = 0;
        for (int i = 0; i < unrollLevel; i++) {
            if (start > size) {
                return sum;
            }
            if (isPrime(start)) {
                sum += start;
            }
            start++;
        }

        try {
            return sum + recursiveUnrolled(start, unrollLevel, size, counter + 1);
        } catch (StackOverflowError e) {
            System.out.println("Reached nr " + start + "/" + size + " at " + unrollLevel + " levels after " + counter + " calls.");
            return sum;
        }
    }

    // Helper method to check if a number is prime
    private boolean isPrime(long x) {
        if (x <= 1) {
            return false;
        }
        if (x == 2) {
            return true;
        }
        for (long i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Benchmark method to run the prime finding with or without unrolling
    public void run(Object[] params) {
        boolean useUnrolling = (boolean) params[0];
        long size = 2000000; // Example workload
        long start = 1;
        long sum;

        if (useUnrolling) {
            int unrollLevel = (int) params[1];
            sum = recursiveUnrolled(start, unrollLevel, size, 0);
            System.out.println("Finished with unrolling level " + unrollLevel + ", sum: " + sum);
        } else {
            sum = recursive(start, size, 0);
            System.out.println("Finished without unrolling, sum: " + sum);
        }
    }

}
