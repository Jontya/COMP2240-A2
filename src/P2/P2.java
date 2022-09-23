import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class P2 {
    private static String filename;
    public static void main(String[] args) throws Exception{
        try{
            filename = args[0];
            P2 iceCreamParlour= new P2();
            iceCreamParlour.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Filename Argument");
        }
    }

    private void run() throws Exception{
        Parlour parlour = new Parlour();
        ArrayList<Customer> customers = readFile(parlour);
        parlour.runParlour(customers);
    }

    private ArrayList<Customer> readFile(Parlour parlour) throws Exception{

        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        ArrayList<Customer> customers = new ArrayList<>();
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
