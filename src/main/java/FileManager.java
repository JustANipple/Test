import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileManager {
    
    private final WeatherStation ws;
    
    public FileManager(WeatherStation ws) {
        this.ws = ws;
    }
    
    public ArrayList<Double> lastDayFile() {
        ArrayList<Double> nums = new ArrayList<>();
        //creazione o controllo del file
        try {
            File myFile = new File("Report");
            if(myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        //lettura e salvataggio dell'ultima riga del file
        String row = "";
        try (Scanner scanner = new Scanner(Paths.get("Report"))) {
            while(scanner.hasNextLine()) {
                row = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        String[] values = row.split(";");
        nums.add(Double.valueOf(values[0]));
        nums.add(Double.valueOf(values[1]));
        nums.add(Double.valueOf(values[2]));
        nums.add(Double.valueOf(values[3]));
        nums.add(Double.valueOf(values[4]));
        nums.add(Double.valueOf(values[5]));
        
        return nums;
    }
    
    public double getLast(String data) {
        ArrayList<Double> nums = lastDayFile();
        switch (data) {
           case "rain":
               return nums.get(0);
           case "ete":
               return nums.get(1);
           case "CCmm":
               return nums.get(2);
           case "CIMmm":
               return nums.get(3);
           case "UMIDmm":
               return nums.get(4);
           case "IRRIGutile":
               return nums.get(5);
           default:
               return 0;
        } 
    }
    
    public void write(HashMap calculations) {
        //creazione o controllo del file
        try {
            File myFile = new File("Report");
            if(myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        //scrittura del file di Report
        try {
            PrintWriter output = new PrintWriter(new FileWriter ("Report", true));
            StringBuilder row = new StringBuilder();
            row.append(ws.getRain())
                    .append(";")
                    .append(calculations.get("ete"))
                    .append(";")
                    .append(calculations.get("CCmm"))
                    .append(";")
                    .append(calculations.get("CIMmm"))
                    .append(";")
                    .append(calculations.get("UMIDmm"))
                    .append(";")
                    .append(calculations.get("IRRIGutile"));
            output.append(row.toString());
            output.append("\n");
            output.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}