package testbenching;

import bench.DummyBenchmark;
import bench.IBenchmark;

public class DemoTestbench {

    public static void main(String[] args) {
        IBenchmark benchmark = new DummyBenchmark();

        benchmark.initialize(1000);

        new Thread(() -> {

            try{

            Thread.sleep(500);
            benchmark.cancel();

            } catch (InterruptedException e){

                e.printStackTrace();

            }

        }).start();
        benchmark.run();


    }    


}
