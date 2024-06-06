package testbenching;

import bench.DummyBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILog;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;

public class Testbench {
    public static void main(String[] args) {
        
    
    ITimer timer = new Timer();
    ILog cLog = new ConsoleLogger();
    IBenchmark bench = new DummyBenchmark();
    TimeUnit timeUnit = TimeUnit.Sec;


    int size = 100000;
    bench.initialize(size);

    timer.start();
    bench.run(new Object[] {new int[size]});
    long time = timer.stop();

    cLog.write("Benchmark completed in", time, "nanoseconds");
    
    bench.clean();
    cLog.close();

    final int workload = 100;

    bench.initialize(workload);
    for (int i = 1; i <= 12; i++){

        timer.resume();
        bench.run();
        long end = timer.pause();
        cLog.write("Run " + i + ";", end);

    }
    cLog.write("Finished in", timer.stop());

    cLog.writeTime("Finished in", timer.stop(), timeUnit);

    bench.clean();
    cLog.close();
    }
    
}
