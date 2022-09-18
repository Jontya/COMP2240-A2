import java.util.concurrent.*;

public class Bridge{
    private Semaphore block;
    private boolean running;
    private int NEON;

    public Bridge(){
        block = new Semaphore(1, true);
        running = true;
        NEON = 0;
    }

    public Semaphore getBlock(){
        return block;
    }

    public boolean getRunning(){
        return running;
    }

    public void setRunning(boolean _running){
        running = _running;
    }

    public int getNEON(){
        return NEON;
    }

    public void incrementNEON(){
        NEON++;
    }
}