package testbenching;

import java.util.concurrent.TimeUnit;

public class Log {
    public void writeTime(String message, long time, TimeUnit unit) {
        System.out.println(message + " " + time + " " + unit.toString().toLowerCase());
    }

    public void write(String message, String result) {
        System.out.println(message + " " + result);
    }
}
