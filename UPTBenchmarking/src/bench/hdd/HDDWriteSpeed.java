package bench.hdd;

import java.io.IOException;

public class HDDWriteSpeed implements IBenchmark {

    @Override
    public void run(Object... options) {
        String mode = (String) options[0];
        boolean clean = (Boolean) options[1];

        String prefix = "C:\\Users\\OmniOni1973\\Desktop\\Project DC\\UPTbenchmarking\\bench\\write0";
        String suffix = ".dat";
        int minIndex = 0;
        int maxIndex = 8; // for example, writing 9 files
        int fileSize = 256 * 1024 * 1024; // 256 MB
        int bufferSize = 4 * 1024; // 4 KB

        FileWriter writer = new FileWriter();
        try {
            if (mode.equals("fs")) {
                writer.streamWriteFixedSize(prefix, suffix, minIndex, maxIndex, fileSize, clean);
            } else if (mode.equals("fb")) {
                writer.streamWriteFixedBuffer(prefix, suffix, minIndex, maxIndex, bufferSize, clean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}