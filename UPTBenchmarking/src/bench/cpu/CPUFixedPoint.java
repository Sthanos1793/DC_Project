package bench.cpu;

public class CPUFixedPoint {
    private long startTime, endTime;

    public void run(int size) {
        integerArithmeticTest(size);
        branchingTest(size);
        arrayAccessTest(size);
    }

    public void integerArithmeticTest(int size) {
        int j = 0, k = 1, l = 2;
        int[] num = {0, 1, 2, 3};
        int[] res = new int[size];

        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            j = num[1] * (k - j) * (l - k);
            k = num[3] * k - (l - j) * k;
            l = (l - k) * (num[2] + j);
            res[Math.abs(l % size)] = j + k + l; // Ensure index is within bounds
            res[Math.abs(k % size)] = j * k * l; // Ensure index is within bounds
        }
        endTime = System.nanoTime();
        
        System.out.println("Integer arithmetic test time: " + (endTime - startTime) / 1e6 + " ms");
    }

    public void branchingTest(int size) {
        int j = 0;
        int[] num = {0, 1, 2, 3};

        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            if (j == 1) {
                j = num[2];
            } else {
                j = num[3];
            }
            if (j > 2) {
                j = num[0];
            } else {
                j = num[1];
            }
            if (j < 1) {
                j = num[1];
            } else {
                j = num[0];
            }
        }
        endTime = System.nanoTime();
        
        System.out.println("Branching test time: " + (endTime - startTime) / 1e6 + " ms");
    }

    public void arrayAccessTest(int size) {
        int[] a = new int[size];
        int[] b = new int[size];
        int[] c = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = i;
            b[i] = size - i - 1;
        }

        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            c[i] = a[b[i]];
        }
        for (int i = 0; i < size; i++) {
            int temp = a[i];
            a[i] = b[i];
            b[i] = temp;
        }
        endTime = System.nanoTime();
        
        System.out.println("Array access test time: " + (endTime - startTime) / 1e6 + " ms");
    }
}
