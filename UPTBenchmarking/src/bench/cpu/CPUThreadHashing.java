package bench.cpu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CPUThreadHashing implements IBenchmarkHash {
    private ExecutorService executor;
    private volatile boolean running = true;
    private String result;

    @Override
    public void run(int maxLength, int nThreads, int hashCode) {
        executor = Executors.newFixedThreadPool(nThreads);
        Hashmanager hasher = new Hashmanager();
        String targetHash = Integer.toString(hashCode);
        String text = "aa";

        while (running && text.length() <= maxLength) {
            HashBreakerTask task = new HashBreakerTask(text, targetHash, hasher, this);
            executor.execute(task);

            text = hasher.getNextString(text);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public void stop() {
        running = false;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String getResult() {
        return result != null ? result : "Password not found";
    }
}
