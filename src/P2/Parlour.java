import java.util.concurrent.*;
import java.util.ArrayList;
import java.lang.Math;
import java.util.PriorityQueue;

public class Parlour {
    private PriorityQueue<TimeEvent> eventQueue;
    private static final int MAXSEATS = 5;
    private Semaphore timeSemaphore;
    private Semaphore semaphore;
    private boolean complete;
    private int currTime;

    private int checkCount;

    public Parlour(){
        currTime = 0;
        complete = false;
        timeSemaphore = new Semaphore(1, true);
        semaphore = new Semaphore(MAXSEATS, true);
        eventQueue = new PriorityQueue<>();

        checkCount = 0;
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

    public boolean getCompletedStatus(){
        return complete;
    }

    public void swapCompletedStatus(){
        complete = !complete;
    }

    public void incCheckCount(){
        checkCount++;
    }
    
    public boolean allChecked(){
        if(checkCount == Math.abs(semaphore.availablePermits() - MAXSEATS)){
            return true;
        }
        return false;
    }

    public void resetCheckCount(){
        checkCount = 0;
    }

    public void nextEvent(){
        if(!eventQueue.isEmpty()){
            currTime = eventQueue.remove().getTime();
            return;
        }
        System.exit(0);
    }

    public void runParlour(ArrayList<Customer> customers){
        for(int i = 0; i < 8; i++){
            new Thread(customers.get(i)).start();
        }
        System.out.println(semaphore.availablePermits());
        /* 
        Customer tempCustomer;
        int size = customers.size();
        for(int i = 0 ; i < size; i++){
            tempCustomer = customers.get(i);
            if(customers.get(i).getArriveTime() == 0){
                tempCustomer.setSeatedTime(0);
                eventQueue.add(new TimeEvent(tempCustomer, true, tempCustomer.getEatingTime()));
                new Thread(customers.get(i)).start();
            }
            else{
                //eventQueue.add(new TimeEvent(tempCustomer, false, tempCustomer.getArriveTime()));
            }
        }
        System.out.println(semaphore.availablePermits());
        //nextEvent();*/
    }

}
