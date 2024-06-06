package bench.cpu;

public class SquareRootTask implements Runnable {
    private int start;
    private int end;

    public SquareRootTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            double result = getNewtonian(i);
            System.out.println("Square root of " + i + " is " + result);
        }
    }

    private double getNewtonian(double x) {
        double s = x;
        double epsilon = 1e-4;
        while (Math.abs(s * s - x) > epsilon) {
            s = (x / s + s) / 2;
        }
        return s;
    }
}
