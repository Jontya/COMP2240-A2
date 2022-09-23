//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** Farmer:
*** Runnable class for each thread that is created. Uses a semaphore to ensure mutual exclusion 
*** so only one farmer can cross the bridge at a time
**/
//---------------------------------------------------------------------------------------------------

public class Farmer implements Runnable{
    private String farmerID;
    private String direction;
    private Bridge bridge;
    private int numSteps;
    
    // Default Constructor
    public Farmer(String _farmerID, Bridge _bridge, String _direction){
        farmerID = _farmerID;
        bridge = _bridge;
        direction = _direction;
        numSteps = 0;
    }

    // Changes farmers direction
    public void changeFarmerDirection(){
        if(direction.equals("North")){
            direction = "South";
        }
        else{
            direction = "North";
        }
    }

    // Runnable method for each thread to run
    @Override
    public void run() {
        // While NEON < 100
        while(bridge.getNEON() < 100){
            // Prints previous farmer
            System.out.println(farmerID + ": Waiting for bridge. Going towards " + direction);

            // Acquires the semaphore
            bridge.acquireMutex();
            // If the NEON is >= 100
            if(bridge.getNEON() >= 100){
                // Release the semaphore
                bridge.releaseMutex();
                // End the thread
                return;
            }

            // While the number of steps a farmer has taken != 20
            while(numSteps != 20){
                // Adds 5 steps
                numSteps += 5;
                // If the farmer has crossed the bridge
                if(numSteps == 20){
                    // Increments NEON, Changes direction an prints the information
                    bridge.incrementNEON();
                    changeFarmerDirection();
                    System.out.println(farmerID + ": Across the bridge.");
                    System.out.println("NEON = " + bridge.getNEON() + "");
                    // Releases the semaphore
                    bridge.releaseMutex();
                }
                // Else it just prints the number of steps its on
                else{
                    System.out.println(farmerID + ": Crossing bridge step " + numSteps + ".");
                }

                // Sleep thread for 20ms
                try{
                    Thread.sleep(20);
                }
                catch (Exception e) {
                    break;
                }
            }
            // Resets steps
            numSteps = 0;
        }
    }
}
