public class Customer implements Runnable{
    private String customerID;
    private Parlour parlour;
    private int arriveTime;
    private int eatingTime;
    private int seatedTime;
    private int leaveTime;
    private boolean waiting;
    private boolean finished;

    public Customer(int _arriveTime, String _customerID, int _customerTime, Parlour _parlour){
        arriveTime = _arriveTime;
        customerID = _customerID;
        eatingTime = _customerTime;
        parlour = _parlour;

        waiting = false;
        finished = false;
    }

    public String getCustomerID(){
        return customerID;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getEatingTime(){
        return eatingTime;
    }

    public int getLeaveTime(){
        return leaveTime;
    }

    public int getSeatedTime(){
        return seatedTime;
    }

    @Override
    public void run() {
        parlour.acquireMutex();
        if(arriveTime == 0){
            if(parlour.sitDown()){
                seatedTime = 0;
            }
            else{
                waiting = true;
            }
        }
        else{
            waiting = true;
        }
        parlour.releaseMutex();

    
        while(!finished){
            parlour.acquireMutex();
            if(waiting){
                if(parlour.getCurrTime() >= arriveTime){
                    if(parlour.sitDown()){
                        waiting = false;
                        seatedTime = parlour.getCurrTime();
                        parlour.addNewEvent(seatedTime + eatingTime);
                        parlour.incTime();
                    }
                    else{
                       parlour.insertIntoList();
                    }
                }
            }
            else{
                if(parlour.getCurrTime() == (eatingTime + seatedTime)){
                    finished = true;
                    //System.out.println(customerID + " " + parlour.getCurrTime());
                    leaveTime = parlour.getCurrTime();
                    parlour.addToFinishedList(this);
                    parlour.leave();
                    parlour.incTime();
                }
            }
            parlour.releaseMutex();
        }
    }
}
