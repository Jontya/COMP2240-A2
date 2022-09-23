//---------------------------------------------------------------------------------------------------
/** COMP2240 A2
*** Jonty Atkinson (C3391110)
*** 23/09/22
***
*** P1:
*** Main class for problem one. Reads file input, creates a new thread for each farmer and begins
*** the simulation. Extras: Validates filename and command line inputs.
**/
//---------------------------------------------------------------------------------------------------

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class P1{
    private static String filename;
    public static void main(String[] args) throws Exception{
        // Checks command line arguments
        try{
            // Runs the program
            filename = args[0];
            P1 farmerBridgeProblem = new P1();
            farmerBridgeProblem.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Filename Argument");
        }
    }

    private void run() throws Exception{
        // Reads file for farmer info and creates a list for threads
        ArrayList<Farmer> farmers = readFile();
        ArrayList<Thread> threads = new ArrayList<>();

        // Creates a new farmer thread
        while(!farmers.isEmpty()){
            threads.add(new Thread(farmers.remove(0)));
        }
        
        // Starts each thread
        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }
    }

    // Reads farmer information in from a file
    private ArrayList<Farmer> readFile() throws Exception{
        // Checks if filename exists
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        ArrayList<Farmer> farmer = new ArrayList<>();
        Bridge bridge = new Bridge();
        Scanner scanner = new Scanner(file);

        // Creates the new farmer objects
        try{
            String[] input = scanner.nextLine().split("[,]", 0);
            for(int i = 0; i < Character.getNumericValue(input[0].charAt(input[0].length() - 1)); i++){
                farmer.add(new Farmer("N_Farmer" + (i + 1), bridge, "South"));
            }

            for(int i = 0; i < Character.getNumericValue(input[1].charAt(input[1].length() - 1)); i++){
                farmer.add(new Farmer("S_Farmer" + (i + 1), bridge, "North"));
            }
        } 
        finally{
            scanner.close();
        }

        // Returns the list of farmerss
        return farmer;
    }
} 