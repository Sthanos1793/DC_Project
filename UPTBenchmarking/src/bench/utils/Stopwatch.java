package bench.utils;

public class Stopwatch {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long getTime() {
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }
}
