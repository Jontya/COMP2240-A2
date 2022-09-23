import java.util.ArrayList;

public class Parlour {
    private static final int MAXSEATS = 5;
    private ArrayList<Customer> finishedList;
    private boolean capacityStatus;
    private int customersEating;
    private int currTime;

    public Parlour(){
        finishedList = new ArrayList<>();
        customersEating = 0;
        currTime = 0;
        capacityStatus = false;
    }

    public int getCurrTime(){
        return currTime;
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
        int pos = 1;
        while(!finishedList.isEmpty()){
            for(int i = 0; i < finishedList.size(); i++){
                Customer temp = finishedList.get(i);
                if(Character.getNumericValue(temp.getCustomerID().charAt(temp.getCustomerID().length() - 1)) == pos){
                    output += String.format("%-11s %-10d %-8d %-1d", temp.getCustomerID(), temp.getArriveTime(), temp.getSeatedTime(), temp.getFinishedTime()) + "\n";
                    finishedList.remove(i);
                }
            }
            pos++;
        }
        return output;
    }
}
