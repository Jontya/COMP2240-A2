import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class P1{
    private static String filename;
    private ArrayList<Farmer> northFarmers;
    private ArrayList<Farmer> southFarmers;
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
        readFile();
        for(int i = 0; i < northFarmers.size(); i++){
            System.out.println(northFarmers.get(i).getFarmerID() + " " + southFarmers.get(i).getFarmerID());
        }
    }

    private void readFile() throws Exception{
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        northFarmers = new ArrayList<>();
        southFarmers = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        try{
            String[] input = scanner.nextLine().split("[,]", 0);
            if(input[0].charAt(0) == 'N'){
                initFarmerLists(input[0], input[1]);
            }
            else{
                initFarmerLists(input[1], input[0]);
            }
        } 
        finally{
            scanner.close();
        }
    }

    private void initFarmerLists(String north, String south){
        for(int i = 0; i < Character.getNumericValue(north.charAt(north.length()-1)); i++){
            northFarmers.add(new Farmer("N_Farmer" + (i+1)));
        }

        for(int i = 0; i < Character.getNumericValue(south.charAt(south.length()-1)); i++){
            southFarmers.add(new Farmer("S_Farmer" + (i+1)));
        }
    }
} 