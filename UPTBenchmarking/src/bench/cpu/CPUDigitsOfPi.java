package bench.cpu;

import java.math.BigDecimal;
import java.math.MathContext;

public class CPUDigitsOfPi implements IBenchmarkCPU {
    private static final BigDecimal FOUR = new BigDecimal("4");
    private static final BigDecimal ONE = new BigDecimal("1");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");
    private MathContext mathContext;

    @Override
    public void initialize(Object... params) {
        int digits = (int) params[0];
        mathContext = new MathContext(digits);
        System.out.println("Initialization complete with precision: " + digits);
    }

    @Override
    public void warmup() {
        try {
            System.out.println("Warming up...");
            calculatePiMachin();
            calculatePiChudnovsky(5);
            System.out.println("Warmup complete.");
        } catch (Exception e) {
            System.out.println("Error during warmup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run(Object... options) {
        try {
            int iterations = (int) options[0];
            System.out.println("Running benchmark with " + iterations + " iterations...");
            for (int i = 0; i < iterations; i++) {
                System.out.println("Starting run " + (i + 1));

                long startTime = System.nanoTime();
                BigDecimal pi = calculatePiMachin();
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Run " + (i + 1) + " using Machin's formula: " + duration + " nanoseconds, Pi: " + pi);

                startTime = System.nanoTime();
                pi = calculatePiChudnovsky(5);
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                System.out.println("Run " + (i + 1) + " using Chudnovsky algorithm: " + duration + " nanoseconds, Pi: " + pi);
            }
        } catch (Exception e) {
            System.out.println("Error during run: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void clean() {
        System.out.println("Cleanup complete.");
    }

    private BigDecimal calculatePiMachin() {
        try {
            System.out.println("Calculating Pi using Machin's formula...");
            BigDecimal arctan1_5 = arctan(ONE.divide(FIVE, mathContext), mathContext);
            BigDecimal arctan1_239 = arctan(ONE.divide(TWO_THIRTY_NINE, mathContext), mathContext);
            BigDecimal pi = FOUR.multiply(FOUR.multiply(arctan1_5).subtract(arctan1_239));
            System.out.println("Pi using Machin's formula: " + pi);
            return pi;
        } catch (Exception e) {
            System.out.println("Error calculating Pi using Machin's formula: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal arctan(BigDecimal x, MathContext mc) {
        try {
            BigDecimal result = BigDecimal.ZERO;
            BigDecimal term;
            BigDecimal xSquared = x.multiply(x, mc);
            BigDecimal numerator = x;
            int i = 1;
            int maxIterations = 10000; // Set a maximum number of iterations to prevent infinite loop
            int count = 0;

            do {
                numerator = numerator.multiply(xSquared, mc);
                int denom = 2 * i + 1;
                term = numerator.divide(new BigDecimal(denom), mc);
                if (i % 2 != 0) {
                    result = result.subtract(term);
                } else {
                    result = result.add(term);
                }
                i++;
                count++;
                if (count >= maxIterations) {
                    System.out.println("arctan() reached max iterations");
                    break;
                }
            } while (term.abs().compareTo(BigDecimal.ZERO) != 0);

            return result;
        } catch (Exception e) {
            System.out.println("Error in arctan calculation: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal calculatePiChudnovsky(int iterations) {
        try {
            System.out.println("Calculating Pi using Chudnovsky algorithm...");
            BigDecimal C = new BigDecimal("426880").multiply(sqrt(new BigDecimal("10005"), mathContext));
            BigDecimal sum = BigDecimal.ZERO;
            for (int k = 0; k < iterations; k++) {
                BigDecimal numerator = factorial(6 * k).multiply(new BigDecimal(13591409).add(new BigDecimal(545140134).multiply(new BigDecimal(k))));
                BigDecimal denominator = factorial(3 * k).multiply(factorial(k).pow(3)).multiply(BigDecimal.valueOf(-640320).pow(3 * k));
                sum = sum.add(numerator.divide(denominator, mathContext));
            }
            BigDecimal pi = C.divide(sum, mathContext);
            System.out.println("Pi using Chudnovsky algorithm: " + pi);
            return pi;
        } catch (Exception e) {
            System.out.println("Error calculating Pi using Chudnovsky algorithm: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal sqrt(BigDecimal A, MathContext mc) {
        try {
            BigDecimal x0 = BigDecimal.ZERO;
            BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
            int maxIterations = 1000; // Set a maximum number of iterations to prevent infinite loop
            int count = 0;

            while (!x0.equals(x1) && count < maxIterations) {
                x0 = x1;
                x1 = A.divide(x0, mc);
                x1 = x1.add(x0);
                x1 = x1.divide(BigDecimal.valueOf(2), mc);
                count++;
            }

            if (count >= maxIterations) {
                System.out.println("sqrt() reached max iterations");
            }

            return x1;
        } catch (Exception e) {
            System.out.println("Error calculating square root: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal factorial(int n) {
        try {
            BigDecimal result = BigDecimal.ONE;
            for (int i = 2; i <= n; i++) {
                result = result.multiply(BigDecimal.valueOf(i));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error calculating factorial: " + e.getMessage());
            e.printStackTrace();
            return BigDecimal.ONE;
        }
    }
}