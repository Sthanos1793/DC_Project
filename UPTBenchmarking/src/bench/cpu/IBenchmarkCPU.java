package bench.cpu;

public interface IBenchmarkCPU {

    void warmup();
    
    void run(Object ... options);

    void initialize(Object ... params);

    void clean();


}
