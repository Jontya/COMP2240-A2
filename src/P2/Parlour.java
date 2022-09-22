import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class Parlour {
    private static final int MAXSEATS = 5;
    private ArrayList<Customer> finishedList;
    private PriorityQueue<Integer> eventQueue;
    private Semaphore mutex;
    private Semaphore block;
    private int customersEating;
    private boolean capacityStatus;
    private int currTime;


    public Parlour(){
        eventQueue = new PriorityQueue<>();
        finishedList = new ArrayList<>();
        mutex = new Semaphore(1, true);
        block = new Semaphore(MAXSEATS, true);
        customersEating = 0;
        currTime = 0;
        capacityStatus = false;
    }

    public int getCurrTime(){
        return currTime;
    }

    public boolean getCapacityStatus(){
        return capacityStatus;
    }

    public void acquireBlock(){
        try{
            block.acquire();
        }
        catch (Exception e){
            return;
        }
    }

    public void acquireMutex(){
        try{
            mutex.acquire();
        }
        catch (Exception e){
            return;
        }
    }

    public void releaseBlock(){
        block.release();
    }

    public void releaseMutex(){
        mutex.release();
    }

    public boolean sitDown(){
        if(!capacityStatus){
            customersEating++;

            if(customersEating == MAXSEATS){
                capacityStatus = true;
            }

            acquireBlock();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean leave(){
        customersEating--;

        if(customersEating == 0 && capacityStatus){
            capacityStatus = false;
        }

        block.release();
        return true;
    }

    public void addNewEvent(int time){
        eventQueue.add(time);
    }

    public void insertIntoList(){
        if(!eventQueue.isEmpty()){
            currTime = eventQueue.remove();
            eventQueue.add(eventQueue.peek());
        }
    }

    public void incTime(){
        if(!eventQueue.isEmpty()){
            currTime = eventQueue.remove();
        }
    }

    public void addToFinishedList(Customer customer){
        finishedList.add(customer);
    }


    public void runParlour(ArrayList<Customer> customers){
        Customer temp;
        int size = customers.size();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < size; i++){
            temp = customers.remove(0);
            if(temp.getArriveTime() == 0){
                eventQueue.add(temp.getEatingTime());
            }
            else{
                eventQueue.add(temp.getArriveTime());
            }
            threads.add(new Thread(temp));
        }

        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }

        incTime();
        while(finishedList.size() != size);
        System.out.println(generateReport());
    }

    public String generateReport(){
        String output = "Customer   Arrives     Seats    Leaves\n";
        int pos = 1;
        while(!finishedList.isEmpty()){
            for(int i = 0; i < finishedList.size(); i++){
                Customer temp = finishedList.get(i);
                if(Character.getNumericValue(temp.getCustomerID().charAt(temp.getCustomerID().length() - 1)) == pos){
                    output += String.format("%-11s %-10d %-8d %-1d", temp.getCustomerID(), temp.getArriveTime(), temp.getSeatedTime(), temp.getLeaveTime()) + "\n";
                    finishedList.remove(i);
                }
            }
            pos++;
        }
        return output;
    }
}
