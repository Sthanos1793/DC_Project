package logging;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileLogger implements ILog{

    private PrintWriter writer;
    public FileLogger(String filename){

        File fileLogger = new File("C:\\Users\\OmniOni1973\\Desktop\\Project DC\\UPTBenchmarking\\src\\logging\\filelogger.txt");

        try{
            if (fileLogger.createNewFile()){

            FileWriter fileWriter = new FileWriter(fileLogger, true);
            writer = new PrintWriter(fileWriter);
            }
        }

        catch(IOException e){

            e.printStackTrace();

        }

    }
    
    public void write(long value){

        writer.println(value);        

    }

    public void write(String message){

        writer.println(message);

    }

    public void write(Object ... values){

        for(Object value: values){

            writer.print(value + " ");

        }

        writer.println();

    }

    public void writeTime(String message, long time, TimeUnit unit){

        long convertedTimeinFile = TimeUnitConverter.convert(time, TimeUnit.Nano, unit);
        writer.println(message + " " + convertedTimeinFile + " " + unit);

    }

    public void close(){

        writer.close();

    }



}
