//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** MonitorCustomer:
*** Runnable class for each thread. Handles the basic customer information: customerID, arrival time,
*** waiting status, etc. Class is also comparable (by customerID) which is used for printing stats.
*** Mutual exclusion is managed by the parlour monitor.
**/
//---------------------------------------------------------------------------------------------------

public class MonitorCustomer implements Runnable, Comparable<MonitorCustomer>{
    private MonitorParlour parlour;
    private String customerID;
    private boolean finished;
    private boolean waiting;
    private int arriveTime;
    private int seatedTime;
    private int eatingTime;
    private int finishedTime;

    // Default constructor
    public MonitorCustomer(int _arriveTime, String _customerID, int _eatingTime, MonitorParlour _parlour){
        finished = false;
        waiting = true;
        parlour = _parlour;
        arriveTime = _arriveTime;
        eatingTime = _eatingTime;
        customerID = _customerID;
    }

    // Getters / Setters
    public void setFinishTime(int _finishedTime){
        finishedTime = _finishedTime;
    }

    public String getCustomerID(){
        return customerID;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getSeatedTime(){
        return seatedTime;
    }

    public int getFinishedTime(){
        return finishedTime;
    }

    public int getEatingTime(){
        return eatingTime;
    }

    // Run method for each thread
    @Override
    public void run() {
        // While the customer has not finished eating
        while(!finished){
            // If the customer is not waiting
            if(!waiting){
                // Acquires the monitor
                synchronized (parlour){
                    if(parlour.leave(this)){
                        finished = true; // Updates finished status if the customer is ready to leave
                    }
                }
                // Releases the monitor
            }
            else{
                // Acquires the monitor
                synchronized (parlour){
                    if(parlour.getCurrTime() >= arriveTime){
                        if(parlour.getCurrTime() >= arriveTime){ // If the customer is able to be seated
                            if(parlour.sitDown(eatingTime)){ // If the customer can be seated
                                waiting = false; // Updates waiting status and seated time
                                seatedTime = parlour.getCurrTime();
                            }
                        }
                    }
                }
                // Releases the monitor
            }
        }
    }

    // Compares two customer ID's (Used for printing stats)
    @Override
    public int compareTo(MonitorCustomer customer) {
        int n1 = Integer.parseInt(customerID.substring(1));
        int n2 = Integer.parseInt(customer.getCustomerID().substring(1));
        
        if(n1 > n2){
            return 1;
        }
        
        return -1;
    }
}
