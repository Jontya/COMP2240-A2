public class Customer implements Runnable{
    private Parlour parlour;
    private String customerID;
    private boolean finished;
    private boolean waiting;
    private int arriveTime;
    private int seatedTime;
    private int eatingTime;
    private int finishedTime;

    public Customer(int _arriveTime, String _customerID, int _eatingTime, Parlour _parlour){
        finished = false;
        waiting = true;
        parlour = _parlour;
        arriveTime = _arriveTime;
        eatingTime = _eatingTime;
        customerID = _customerID;
    }

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

    @Override
    public void run() {
        while(!finished){
            if(!waiting){
                parlour.acquireMutex();
                    if(parlour.leave(this)){
                        finished = true;
                    }
                parlour.releaseMutex();
            }
            else{
                parlour.acquireMutex();
                    if(parlour.getCurrTime() >= arriveTime){
                        if(parlour.sitDown(eatingTime)){
                            waiting = false;
                            seatedTime = parlour.getCurrTime();
                        }
                    }
                parlour.releaseMutex();
            }
        }
    }
}
