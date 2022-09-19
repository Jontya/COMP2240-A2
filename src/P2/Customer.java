import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

public class Customer implements Runnable,Comparable<Customer>{
    private int arriveTime;
    private Parlour parlour;
    private String customerID;
    private int customerTime;

    public Customer(int _arriveTime, String _customerID, int _customerTime, Parlour _parlour){
        arriveTime = _arriveTime;
        customerID = _customerID;
        customerTime = _customerTime;
        parlour = _parlour;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public String getCustomerID(){
        return customerID;
    }

    public int getCustomerTime(){
        return customerTime;
    }

    @Override
    public void run() {
        while(!parlour.checkEvent()){
            try{
                parlour.getSemaphore().acquire();
            }
            catch (Exception e) {
                return;
            }
    
            if(parlour.getCurrentTime() == customerTime){
                System.out.println(customerID + " " + arriveTime + " " + parlour.getCurrentTime());
                parlour.getSemaphore().release();
                parlour.incOpnSeats();
            }
        }
    }

    @Override
    public int compareTo(Customer o) {
        if(arriveTime > o.getArriveTime()){
            return 1;
        }
        return 0;
    }
}
