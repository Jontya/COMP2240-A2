import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Parlour {
    private PriorityQueue<TimeEvent> eventQueue;
    private static final int MAXSEATS = 5;
    private Semaphore timeSemaphore;
    private Semaphore semaphore;
    private boolean complete;
    private int currTime;

    public Parlour(){
        currTime = 0;
        complete = false;
        timeSemaphore = new Semaphore(1, true);
        semaphore = new Semaphore(MAXSEATS, true);
        eventQueue = new PriorityQueue<>();
    }

    public int getCurrTime(){
        return currTime;
    }

    public void setCurrTime(int _currTime){
        currTime = _currTime;
    }

    public Semaphore getSemaphore(){
        return semaphore;
    }

    public Semaphore getTimeSemaphore(){
        return timeSemaphore;
    }

    public void swapCompleteStatus(){
        complete = !complete;
    }

    public void runParlour(ArrayList<Customer> customers){
        Customer tempCustomer;
        int size = customers.size();
        for(int i = 0 ; i < size; i++){
            tempCustomer = customers.get(i);
            if(customers.get(i).getArriveTime() == 0){
                tempCustomer.setSitDownTime(0);
                eventQueue.add(new TimeEvent(tempCustomer, true, tempCustomer.getEatingTime()));
                new Thread(customers.get(i)).start();
            }
            else{
                //eventQueue.add(new TimeEvent(tempCustomer, false, tempCustomer.getArriveTime()));
            }
        }
    }

    public void nextEvent(){
        currTime = eventQueue.remove().getTime();
    }

}
