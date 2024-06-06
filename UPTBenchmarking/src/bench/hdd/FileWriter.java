package bench.hdd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileWriter {
    private static final int MIN_BUFFER_SIZE = 1024; // 1 KB
    private static final int MAX_BUFFER_SIZE = 32 * 1024 * 1024; // 32 MB
    private static final int MIN_FILE_SIZE = 1 * 1024 * 1024; // 1 MB
    private static final int MAX_FILE_SIZE = 512 * 1024 * 1024; // 512 MB

    public void streamWriteFixedSize(String prefix, String suffix, int minIndex, int maxIndex, int fileSize, boolean clean) throws IOException {
        int bufferSize = MIN_BUFFER_SIZE;
        for (int i = minIndex; i <= maxIndex; i++) {
            if (bufferSize > MAX_BUFFER_SIZE) break;
            String fileName = prefix + i + suffix;
            writeFile(fileName, bufferSize, fileSize, clean);
            bufferSize *= 2;
        }
    }

    public void streamWriteFixedBuffer(String prefix, String suffix, int minIndex, int maxIndex, int bufferSize, boolean clean) throws IOException {
        int fileSize = MIN_FILE_SIZE;
        for (int i = minIndex; i <= maxIndex; i++) {
            if (fileSize > MAX_FILE_SIZE) break;
            String fileName = prefix + i + suffix;
            writeFile(fileName, bufferSize, fileSize, clean);
            fileSize *= 2;
        }
    }

    private void writeFile(String fileName, int bufferSize, int fileSize, boolean clean) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), bufferSize)) {
            byte[] buffer = new byte[bufferSize];
            int toWrite = fileSize / bufferSize;
            long startTime = System.nanoTime();
            Random random = new Random();
            for (int i = 0; i < toWrite; i++) {
                random.nextBytes(buffer);
                bos.write(buffer);
            }
            long endTime = System.nanoTime();
            printStats(fileName, fileSize, bufferSize, startTime, endTime);
            if (clean) {
                file.delete();
            }
        }
    }

    private void printStats(String fileName, long totalBytes, int bufferSize, long startTime, long endTime) {
        double timeTaken = (endTime - startTime) / 1_000_000_000.0; // seconds
        double speed = (totalBytes / (1024.0 * 1024.0)) / timeTaken; // MB/s
        System.out.printf("File: %s, Size: %d bytes, Buffer: %d bytes, Speed: %.2f MB/s%n", fileName, totalBytes, bufferSize, speed);
    }
}
