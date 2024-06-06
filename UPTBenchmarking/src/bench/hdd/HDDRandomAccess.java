package bench.hdd;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class HDDRandomAccess {

    
    public void run(String operationType, String mode, int bufferSize, int numberOfJumps, long durationInMillis) throws IOException {
        String filePath = "temporaryFile.dat"; // Path to the temporary file

        // Initialize temporary file with a size to accommodate the buffer and number of jumps
        initializeTemporaryFile(filePath, bufferSize * numberOfJumps);

        switch (operationType) {
            case "r":
                if (mode.equals("fs")) {
                    long readTime = randomReadFixedSize(filePath, bufferSize, numberOfJumps);
                    System.out.println("Read fixed size time: " + readTime + "ms");
                } else if (mode.equals("ft")) {
                    int readJumps = randomReadFixedTime(filePath, bufferSize, durationInMillis);
                    System.out.println("Read fixed time jumps: " + readJumps);
                }
                break;

            case "w":
                byte[] data = new byte[bufferSize];
                Arrays.fill(data, (byte) 0); // Example data, could be parameterized
                if (mode.equals("fs")) {
                    long writeTime = randomWriteFixedSize(filePath, data, numberOfJumps);
                    System.out.println("Write fixed size time: " + writeTime + "ms");
                } else if (mode.equals("ft")) {
                    int writeJumps = randomWriteFixedTime(filePath, data, durationInMillis);
                    System.out.println("Write fixed time jumps: " + writeJumps);
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }
    }

    private long randomReadFixedSize(String filePath, int bufferSize, int numberOfJumps) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        byte[] buffer = new byte[bufferSize];
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfJumps; i++) {
            int position = (int) (Math.random() * (file.length() - bufferSize));
            file.seek(position);
            file.read(buffer);
        }

        long endTime = System.currentTimeMillis();
        file.close();
        return endTime - startTime; // Total time in milliseconds
    }

    private int randomReadFixedTime(String filePath, int bufferSize, long durationInMillis) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        byte[] buffer = new byte[bufferSize];
        long startTime = System.currentTimeMillis();
        int jumps = 0;

        while (System.currentTimeMillis() - startTime < durationInMillis) {
            int position = (int) (Math.random() * (file.length() - bufferSize));
            file.seek(position);
            file.read(buffer);
            jumps++;
        }

        file.close();
        return jumps; // Total number of jumps
    }

    private long randomWriteFixedSize(String filePath, byte[] data, int numberOfJumps) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfJumps; i++) {
            int position = (int) (Math.random() * (file.length() - data.length));
            file.seek(position);
            file.write(data);
        }

        long endTime = System.currentTimeMillis();
        file.close();
        return endTime - startTime; // Total time in milliseconds
    }

    private int randomWriteFixedTime(String filePath, byte[] data, long durationInMillis) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        long startTime = System.currentTimeMillis();
        int jumps = 0;

        while (System.currentTimeMillis() - startTime < durationInMillis) {
            int position = (int) (Math.random() * (file.length() - data.length));
            file.seek(position);
            file.write(data);
            jumps++;
        }

        file.close();
        return jumps; // Total number of jumps
    }

    private void initializeTemporaryFile(String filePath, int fileSize) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.setLength(fileSize);
        file.close();
    }
}
    
