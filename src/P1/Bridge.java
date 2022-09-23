//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** Bridge:
*** Bridge class. Maintains the semaphore and NEON counts
**/
//---------------------------------------------------------------------------------------------------

import java.util.concurrent.*;

public class Bridge{
    private Semaphore mutex;
    private int NEON;

    // Default Constructor
    public Bridge(){
        mutex = new Semaphore(1, true);
        NEON = 0;
    }

    // Acquire the semaphore
    public void acquireMutex(){
        try{
            mutex.acquire();
        }
        catch (Exception e){
            return;
        }
    }

    // Release the semaphore
    public void releaseMutex(){
        mutex.release();
    }

    // Gets the neon value
    public int getNEON(){
        return NEON;
    }

    // Increments the neon counter
    public void incrementNEON(){
        NEON++;
    }
}