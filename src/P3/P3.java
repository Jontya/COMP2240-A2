//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** P3:
*** Main class for problem 3. Reads file input, creates a new thread for each customer and begins
*** the simulation. Extras: Validates filename and command line inputs.
**/
//---------------------------------------------------------------------------------------------------

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class P3 {
    private static String filename;
    public static void main(String[] args) throws Exception{
        // Checks command line arguments
        try{
            // Runs the program
            filename = args[0];
            P3 iceCreamParlour = new P3();
            iceCreamParlour.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Filename Argument");
        }
    }

    private void run() throws Exception{
        // Creates new parlour object and creates a list of all customers
        MonitorParlour parlour = new MonitorParlour();
        ArrayList<MonitorCustomer> customers = readFile(parlour);

        // Creates an arraylist with each thread
        ArrayList<Thread> threads = new ArrayList<>();
        while(!customers.isEmpty()){
            threads.add(new Thread(customers.remove(0)));
        }

        // Starts each thread
        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }

        // Runs the parlour simulation
        parlour.runParlour(threads.size());
    }

    private ArrayList<MonitorCustomer> readFile(MonitorParlour parlour) throws Exception{

        // Checks if the file exists
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        // Creates a new scanner object and customer list
        ArrayList<MonitorCustomer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        // Reads each customer from the file
        try{
            String input = scanner.next();
            while(!input.equals("END")){
                customers.add(new MonitorCustomer(Integer.parseInt(input), scanner.next(), Integer.parseInt(scanner.next()), parlour));
                input = scanner.next();
            }
        } 
        finally{
            scanner.close();
        }

        // Returns the list of all customers
        return customers;
    }
}
