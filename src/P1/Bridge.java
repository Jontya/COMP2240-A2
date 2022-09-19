import java.util.concurrent.*;

public class Bridge{
    private Semaphore block;
    private int NEON;

    public Bridge(){
        block = new Semaphore(1, true);
        NEON = 0;
    }

    public Semaphore getBlock(){
        return block;
    }

    public int getNEON(){
        return NEON;
    }

    public void incrementNEON(){
        NEON++;
    }
}