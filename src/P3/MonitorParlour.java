//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** MonitorParlour:
*** Parlour class handles the current time and parlour status: seats available, capacity status and
*** the finished list.
**/
//---------------------------------------------------------------------------------------------------

import java.util.PriorityQueue;

public class MonitorParlour {
    private static final int MAXSEATS = 5;
    private PriorityQueue<MonitorCustomer> finishedList;
    private boolean capacityStatus;
    private int customersEating;
    private int currTime;

    public MonitorParlour(){
        finishedList = new PriorityQueue<>();
        customersEating = 0;
        currTime = 0;
        capacityStatus = false;
    }

    public int getCurrTime(){
        return currTime;
    }

    public boolean sitDown(int eatingTime){
        // If the parlour isn't at capacity
        if(!capacityStatus){
            // Increments the number of customers eating
            customersEating++;
            // Checks if the parlour is now at capacity
            if(customersEating == MAXSEATS){
                capacityStatus = true;
            }
            // Returns result
            return true;
        }
        // Else the parlour is at capacity
        return false;
    }

    public synchronized boolean leave(MonitorCustomer customer){
        // If the customer has finished eating
        if(customer.getEatingTime() + customer.getSeatedTime() == currTime){
            // Decrements the customers eating by one
            customersEating--;
            // Sets the finish time
            customer.setFinishTime(currTime);
            // Adds customer to the finished list
            finishedList.add(customer);
            // If the number of customers eating is 0 and the parlour was at capacity
            if(customersEating == 0 && capacityStatus){
                // Sets capacity to false
                capacityStatus = false;
            }
            // Returns result
            return true;
        }
        // Else the customer is not ready to leave
        return false;
    }
    
    
    // Runs the parlour and handles its current time
    public void runParlour(int size){
        // While the number of finished customers does not equal the number of customers
        while(finishedList.size() != size){
            try{
                // Sleep the parlour thread (main thread) so all customers and query the current time
                Thread.sleep(10);
            }
            catch (Exception e){
                return;
            } 
            // Increments the parlours current time
            synchronized (this){
                currTime++;
            }
        }
        // Outputs the report
        System.out.println(generateReport());
    }

    // Generates the final report
    public String generateReport(){
        // Headings
        String output = "Customer   Arrives     Seats    Leaves\n";
        // Loops through the list
        while(!finishedList.isEmpty()){
            // Adds the next customer stats to the string with formatting
            MonitorCustomer temp = finishedList.remove();
            output += String.format("%-11s %-10d %-8d %-1d", temp.getCustomerID(), temp.getArriveTime(), temp.getSeatedTime(), temp.getFinishedTime()) + "\n";
        }
        // Returns the output
        return output;
    }
}
