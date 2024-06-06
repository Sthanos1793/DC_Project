package logging;



public interface ILog {

    void write (long value);

    void write (String message);

    void write (Object ... values);

    void writeTime (String message, long time, TimeUnit unit);
    
    void close();


}
