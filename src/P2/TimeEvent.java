public class TimeEvent implements Comparable<TimeEvent>{
    private Customer customerRef;
    private boolean seatedStatus;
    private int time;

    public TimeEvent(Customer _customerRef, boolean _seatedStatus, int _time){
        customerRef = _customerRef;
        seatedStatus = _seatedStatus;
        time = _time;
    }

    public Customer getCustomerRef(){
        return customerRef;
    }

    public boolean getSeatedStatus(){
        return seatedStatus;
    }

    public int getTime(){
        return time;
    }

    @Override
    public int compareTo(TimeEvent timeEvent) {
        if(time > timeEvent.getTime()){
            return 1;
        }
        return -1;
    }
    
}
