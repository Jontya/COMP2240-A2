public class Customer implements Runnable{
    private int arriveTime;
    private int eatingTime;
    private int sitDownTime;
    private Parlour parlour;
    private String customerID;

    public Customer(int _arriveTime, String _customerID, int _customerTime, Parlour _parlour){
        arriveTime = _arriveTime;
        customerID = _customerID;
        eatingTime = _customerTime;
        parlour = _parlour;
    }

    public void setSitDownTime(int t){
        sitDownTime = t;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getEatingTime(){
        return eatingTime;
    }

    @Override
    public void run() {
        try{
            parlour.getSemaphore().acquire();
        }
        catch (Exception e){
            return;
        }
        finally{
            
            parlour.getSemaphore().release();
        }
    }
}
