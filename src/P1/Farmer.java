public class Farmer implements Runnable{
    private String farmerID;
    private String direction;
    private Bridge bridge;
    private int numSteps;
    

    public Farmer(String _farmerID, Bridge _bridge, String _direction){
        farmerID = _farmerID;
        bridge = _bridge;
        direction = _direction;
        numSteps = 0;
    }

    public String getFarmerID(){
        return farmerID;
    }

    public String getFarmerDirection(){
        return direction;
    }

    public void changeFarmerDirection(){
        if(direction.equals("North")){
            direction = "South";
        }
        else{
            direction = "North";
        }
    }

    @Override
    public void run() {
        while(bridge.getNEON() < 100){
            
            try{
                System.out.println(farmerID + ": Waiting for bridge. Going towards " + direction);
                bridge.getBlock().acquire();
            }
            catch (Exception e) {
                break;
            }

            if(bridge.getNEON() >= 100){
                bridge.getBlock().release();
                return;
            }

            while(numSteps != 20){
                numSteps += 5;
                System.out.println(farmerID + ": Crossing bridge step " + numSteps + ".");
                try{
                    Thread.sleep(20);
                }
                catch (Exception e) {
                    break;
                }
            }
    
            numSteps = 0;
            bridge.incrementNEON();
            changeFarmerDirection();
            System.out.println(farmerID + ": Across the bridge.");
            System.out.println("NEON = " + bridge.getNEON() + "");
            bridge.getBlock().release();
            
        }
    }
}
