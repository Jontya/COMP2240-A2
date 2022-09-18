import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class P1{
    private static String filename;
    public static void main(String[] args) throws Exception{
        try{
            filename = args[0];
            P1 farmerBridgeProblem = new P1();
            farmerBridgeProblem.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Filename Argument");
        }
    }

    private void run() throws Exception{
        ArrayList<Farmer> farmers = readFile();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < farmers.size(); i++){
            threads.add(new Thread(farmers.get(i)));
        }
        
        for(int i = 0; i < threads.size(); i++){
            threads.get(i).start();
        }
    }

    private ArrayList<Farmer> readFile() throws Exception{
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        ArrayList<Farmer> farmer = new ArrayList<>();
        Bridge bridge = new Bridge();
        Scanner scanner = new Scanner(file);

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

        return farmer;
    }
} 