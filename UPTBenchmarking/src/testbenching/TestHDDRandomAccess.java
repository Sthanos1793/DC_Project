package testbenching;

import java.io.IOException;

import bench.hdd.HDDRandomAccess;

public class TestHDDRandomAccess {

    public static void main(String[] args) {
        try {
            if (args.length < 5) {
                System.out.println("Usage: java HDDRandomAccess <operationType> <mode> <bufferSize> <numberOfJumps> <durationInMillis>");
                return;
            }
            String operationType = args[0]; // "r" for read, "w" for write
            String mode = args[1]; // "fs" for fixed size, "ft" for fixed time
            int bufferSize = Integer.parseInt(args[2]); // Buffer size in bytes
            int numberOfJumps = Integer.parseInt(args[3]); // Number of jumps for fixed size mode
            long durationInMillis = Long.parseLong(args[4]); // Duration in milliseconds for fixed time mode

            HDDRandomAccess benchmark = new HDDRandomAccess();
            System.out.println("Running HDD Random Access Benchmark with parameters:");
            System.out.println("Operation Type: " + operationType);
            System.out.println("Mode: " + mode);
            System.out.println("Buffer Size: " + bufferSize + " bytes");
            System.out.println("Number of Jumps: " + numberOfJumps);
            System.out.println("Duration: " + durationInMillis + " milliseconds");

            benchmark.run(operationType, mode, bufferSize, numberOfJumps, durationInMillis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in arguments.");
        }
    }  
    
}