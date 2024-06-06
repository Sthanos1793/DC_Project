package logging;

public class TimeUnitConverter {

    public static long convert(long time, TimeUnit from, TimeUnit to){

        switch (from){
    
            case Nano:

                    if (to == TimeUnit.Micro) return time / 1_000;
                    if (to == TimeUnit.Milli) return time / 1_000_000;
                    if (to == TimeUnit.Sec) return time / 1_000_000_000;
                    break;
                
            case Micro:
    
                    if (to == TimeUnit.Nano) return time * 1_000;
                    if (to == TimeUnit.Milli) return time / 1_000;
                    if (to == TimeUnit.Sec) return time / 1_000_000;
                    break;
                
            case Milli:
    
                    if (to == TimeUnit.Nano) return time * 1_000_000;
                    if (to == TimeUnit.Micro) return time * 1_000;
                    if (to == TimeUnit.Sec) return time / 1_000;
                    break;
    
            case Sec:
    
                    if (to == TimeUnit.Nano) return time * 1_000_000_000;
                    if (to == TimeUnit.Micro) return time * 1_000_000;
                    if (to == TimeUnit.Milli) return time * 1_000;
                    break;
                
        }
    
        return time;
    
    }
    

}
