package logging;

public class ConsoleLogger implements ILog {

    public void write(long value){

        System.out.println(value);

    }

    public void write(String message){

        System.out.println(message);

    }

    public void write(Object ... values){

        for(Object value : values){
        System.out.println(value);
        }
    }

    public void writeTime(String message, long time, TimeUnit unit){

        long convertedTime = TimeUnitConverter.convert(time, TimeUnit.Nano, unit);
        System.out.println(message + " " + convertedTime + " " + unit);

    }

    public void close(){

        

    }
    
}
