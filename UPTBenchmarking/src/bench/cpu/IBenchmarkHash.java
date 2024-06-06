package bench.cpu;

public interface IBenchmarkHash {
    void run(int maxLength, int nThreads, int hashCode);
    String getResult();
}
