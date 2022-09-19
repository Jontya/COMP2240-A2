import java.util.concurrent.*;
import java.util.PriorityQueue;

public class Parlour {
    private static final int MAXSEATS = 5;
    private PriorityQueue<Customer> customers;
    private Semaphore semaphore;
    private int openSeats;
    private int currTime;

    public Parlour(){
        openSeats = MAXSEATS;
        currTime = 0;
        semaphore = new Semaphore(MAXSEATS, true);
        customers = new PriorityQueue<>();
    }

    public void setCustomers(PriorityQueue<Customer> _customers){
        customers = _customers;
    }

    public void decOpenSeats(){
        openSeats--;
    }

    public void incOpnSeats(){
        openSeats++;
    }

    public synchronized void incCurrTime(){
        currTime++;
    }

    public Semaphore getSemaphore(){
        return semaphore;
    }

    public int getCurrentTime(){
        return currTime;
    }

    public int getOpenSeats(){
        return openSeats;
    }

    public boolean checkEvent(){
        
        return false;
    }

    public void runParlour(){
        int size = customers.size();
        for(int i = 0; i < size; i++){
            if(customers.peek().getArriveTime() <= currTime){
                openSeats--;
                new Thread(customers.remove()).start();
            }
        }

        while(customers.size() != 0){
            if(openSeats == 0){
                while(openSeats != 5){
                    // Loops until all seats are free
                }
            }

            for(int i = 0; i < openSeats; i++){
                if(customers.peek().getArriveTime() <= currTime){
                    openSeats--;
                    new Thread(customers.remove()).start();
                }
            }
        }
    }
}
