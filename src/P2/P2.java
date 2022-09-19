import java.io.File;
import java.util.Scanner;
import java.util.PriorityQueue;

public class P2 {
    private static String filename;
    public static void main(String[] args) throws Exception{
        try{
            filename = args[0];
            P2 farmerBridgeProblem = new P2();
            farmerBridgeProblem.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Filename Argument");
        }
    }

    private void run() throws Exception{
        Parlour parlour = new Parlour();
        PriorityQueue<Customer> customers = readFile(parlour);
        parlour.setCustomers(customers);
        parlour.runParlour();
    }

    private PriorityQueue<Customer> readFile(Parlour parlour) throws Exception{

        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        PriorityQueue<Customer> customers = new PriorityQueue<>();
        Scanner scanner = new Scanner(file);

        try{
            String input = scanner.next();
            while(!input.equals("END")){
                customers.add(new Customer(Integer.parseInt(input), scanner.next(), Integer.parseInt(scanner.next()), parlour));
                input = scanner.next();
            }
        } 
        finally{
            scanner.close();
        }

        return customers;
    }
}
