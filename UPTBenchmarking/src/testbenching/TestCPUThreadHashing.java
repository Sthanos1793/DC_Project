package testbenching;

import bench.cpu.CPUThreadHashing;
import bench.cpu.IBenchmarkHash;
import java.util.concurrent.TimeUnit;

public class TestCPUThreadHashing {
    public static void main(String[] args) {
        IBenchmarkHash bench = new CPUThreadHashing();
        int maxLength = 10;
        int nThreads = 8;
        int hashCode = 524381996;

        Timer timer = new Timer();
        Log log = new Log();

        timer.start();
        bench.run(maxLength, nThreads, hashCode);
        long time = timer.stop();

        log.writeTime("Finished in", time, TimeUnit.MILLISECONDS);
        log.write("Result is", bench.getResult());
    }
}
