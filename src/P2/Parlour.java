import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class Parlour {
    private static final int MAXSEATS = 5;
    private PriorityQueue<Customer> finishedList;
    private Semaphore mutex;
    private int customersEating;
    private boolean capacityStatus;
    private int currTime;


    public Parlour(){
        finishedList = new PriorityQueue<>();
        mutex = new Semaphore(1, true);
        customersEating = 0;
        currTime = 0;
        capacityStatus = false;
    }

    public int getCurrTime(){
        return currTime;
    }

    public void acquireMutex(){
        try{
            mutex.acquire();
        }
        catch (Exception e){
            return;
        }
    }

    public void releaseMutex(){
        mutex.release();
    }

    public boolean sitDown(int eatingTime){
        if(!capacityStatus){
            customersEating++;
            if(customersEating == MAXSEATS){
                capacityStatus = true;
            }
            return true;
        }
        
        return false;
    }

    public synchronized boolean leave(Customer customer){
        if(customer.getEatingTime() + customer.getSeatedTime() == currTime){
            customersEating--;
            customer.setFinishTime(currTime);
            finishedList.add(customer);
            if(customersEating == 0 && capacityStatus){
                capacityStatus = false;
            }
            return true;
        }
        return false;
    }
    
    
    public void runParlour(ArrayList<Customer> customers){
        Customer temp;
        int size = customers.size();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < size; i++){
            temp = customers.remove(0);
            threads.add(new Thread(temp));
        }

        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }

        while(finishedList.size() != size){
            try{
                Thread.sleep(10);
            }
            catch (Exception e){
                return;
            }  
            currTime++;
        }
        System.out.println(generateReport());
    }

    public String generateReport(){
        String output = "Customer   Arrives     Seats    Leaves\n";
        while(!finishedList.isEmpty()){
            Customer temp = finishedList.remove();
            output += String.format("%-11s %-10d %-8d %-1d", temp.getCustomerID(), temp.getArriveTime(), temp.getSeatedTime(), temp.getFinishedTime()) + "\n";
        }
        return output;
    }
}
