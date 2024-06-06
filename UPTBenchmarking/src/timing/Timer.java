package timing;


public class Timer implements ITimer {

    private long elapsedTime ;
    private long totalTime;
    TimerState state;
    TimerState stateOn = TimerState.ON;
    TimerState stateOff = TimerState.OFF;

    public void start() {
    
        this.elapsedTime = System.nanoTime();
        this.totalTime = 0;
        state = stateOn;
    }

    public long stop() {

        if(state == stateOn){

            totalTime += System.nanoTime() - elapsedTime;
            state = stateOff;

        }

        return totalTime;

    }

    public long pause() {

        if (state == stateOn){

        totalTime += System.nanoTime() - elapsedTime;
        state = stateOff;
        
        }
        return totalTime;
    }

    public void resume() {

        if (state == stateOff){
        
            elapsedTime = System.nanoTime();
            state = stateOn;
        
        }
    }


}
