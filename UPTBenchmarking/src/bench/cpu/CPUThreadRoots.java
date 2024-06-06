package bench.cpu;

public class CPUThreadRoots {
    private int workload;
    private int numberOfThreads;

    public void initialize(int workload, int numberOfThreads) {
        this.workload = workload;
        this.numberOfThreads = numberOfThreads;
    }

    public void warmup() {
        // Optional: Implement warmup logic if needed
    }

    public void run() {
        Thread[] threads = new Thread[numberOfThreads];
        int range = workload / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * range + 1;
            int end = (i + 1) * range;
            if (i == numberOfThreads - 1) {
                end = workload; // Make sure the last thread handles any remainder
            }
            threads[i] = new Thread(new SquareRootTask(start, end));
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
