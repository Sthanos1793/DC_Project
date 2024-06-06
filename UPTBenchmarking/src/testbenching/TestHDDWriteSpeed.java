package testbenching;

import bench.hdd.HDDWriteSpeed;

public class TestHDDWriteSpeed {
    public static void main(String[] args) {
        HDDWriteSpeed bench = new HDDWriteSpeed();
        bench.run("fs", true);
        bench.run("fb", true);
        bench.run("fs", false);
        bench.run("fb", false);
    }
}
