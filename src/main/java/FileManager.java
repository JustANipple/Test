
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager{
    
    public FormulaManager manager;
    
    public FileManager() {
        this.manager = WaterBalance.forManager;
    }
    
    //Creazione del file per stazione meteo
    public void stationFileSetup() {
        try {
          File myObj = new File("WSreport.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
        }
    }
    
    //I dati possono essere richiesti per ognuno per inserirli manualmente
    //in questo caso vengono messi fissi riferendoci alla riga 8 dell'excel
    //inviato da Laura, per verificarne il funzionamento e sistemare i calcoli
    public void stationDatasRegistration() {
        manager.ws.setMinTemp(0.3);
        manager.ws.setMaxTemp(1.3);
        manager.ws.setAvgTemp(0.8);
        manager.ws.setRain(3.8);
        manager.ws.setRs(0.86);
        manager.ws.setRhMin(90);
        manager.ws.setRhMax(94);
        manager.ws.setWind(0.4);
        manager.ws.setAltitudine(232);
        manager.ws.setLatitudine(0.785281808113982);
        manager.ws.setDate("01-01-2021");
        String[] parts = manager.ws.getDate().split("-");
        manager.ws.setJulianDay(Integer.valueOf(parts[0]));
    }
    
    //Scrittura dei dati sul file
    public void stationDatasReport() {        
        try(FileWriter writer = new FileWriter("WSreport.txt",true)) {
            writer.write("JulianDay: " + manager.ws.getJulianDay() + "\n" +
                        "date(DD-MM-YYYY): " + manager.ws.getDate() + "\n" +
                        "minTemp(C): " + manager.ws.getMinTemp() + "\n" +
                        "maxTemp(C): " + manager.ws.getMaxTemp() + "\n" +
                        "avgTemp(C): " + manager.ws.getAvgTemp() + "\n" +
                        "rain(mm): " + manager.ws.getRain() + "\n" +
                        "rs(W/m^2): " + manager.ws.getRs() + "\n" +
                        "rhMin(%): " + manager.ws.getRhMin() + "\n" +
                        "rhMax(%): " + manager.ws.getRhMax() + "\n" +
                        "wind(m/s): " + manager.ws.getWind() + "\n" +
                        "altitude(m): " + manager.ws.getAltitudine() + "\n" +
                        "latitude(m): " + manager.ws.getLatitudine() + "\n" +
                        "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Creazione e intestazione del file di campo
    public void fieldFileSetup() {
        try {
          File myObj = new File("FieldReport.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Richiesta dei dati da inserire e settaggio dei parametri
    //Questi valori vengono richiesti una volta per campo, in quanto le costanti
    //del suolo rimangono invariate per tutto il periodo attivo
    public void fieldDatasRegistration() {
        manager.field.setSabbia(31.2);
        manager.field.setArgilla(50.1);
        manager.field.setCorg(0.79);
        manager.field.setProfmm(150);
        manager.field.setKc(0.3);
    }
    
    //Scrittura dei primi dati sul file
    public void fieldDatasReport() {
        try(FileWriter writer = new FileWriter("FieldReport.txt",true)) {
            writer.write("sabbia(%): " + manager.field.getSabbia() + "\n" +
                        "argilla(%): " + manager.field.getArgilla() + "\n" +
                        "carbonio organico/corg(%): " + manager.field.getCorg() + "\n" +
                        "prof(mm): " + manager.field.getProfmm() + "\n" +
                        "kc: " + manager.field.getKc() + "\n" +
                        "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Creazione e intestazione del file contenente le costanti del suolo
    public void constantFileSetup() {
        try {
          File myObj = new File("ConsReport.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
        }
    }
    
    //non serve un constantDatasRegistration perchè i valori vengono tutti calcolati
    
    //Scrittura su file delle costanti del suolo
    public void constantDatasReport() {
        try(FileWriter writer = new FileWriter("ConsReport.txt",true)) {
            writer.write("PROFmm (mm): " + manager.calculations.get("PROFmm") + "\n" +
                        "PA (%v/v): " + manager.calculations.get("PAperc") + "\n" +
                        "Lir (%v/v): " + manager.calculations.get("LirPerc") + "\n" +
                        "CC (%v/v): " + manager.calculations.get("CCperc") + "\n" +
                        "CIM (%v/v): " + manager.calculations.get("CIMper") + "\n" +
                        "PAmm (mm): " + manager.calculations.get("PAmm") + "\n" +
                        "Lirmm (mm): " + manager.calculations.get("Lirmm") + "\n" +
                        "CCmm (mm): " + manager.calculations.get("CCmm") + "\n" +
                        "CIMmm (mm): " + manager.calculations.get("CIMmm") + "\n" +
                        "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Scorrimento di tutti e 4 i file per trovare il valore registrato dell'ultimo giorno
    public double valueFromFile(String str) {
        
        ArrayList<String> files = new ArrayList<>();
        files.add("WSreport.txt");
        files.add("FieldReport.txt");
        files.add("ConsReport.txt");
        files.add("Calculations.txt");
        
        double value = 0;
        
        for(int i = 0; i < files.size(); i++) {
            try(Scanner toScan = new Scanner(Paths.get(files.get(i)))) {
                while(toScan.hasNextLine()) {
                    String[] parts = toScan.nextLine().split(":");
                    if(parts[0].contains(str)) {
                        value = Double.valueOf(parts[1].trim());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } 
        }
        return value;
    }
    
    //Creazione del file per il contenimento dei calcoli
    public void calculationFileSetup() {
        try {
          File myObj = new File("Calculations.txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
          } else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Scrittura del calcoli su file
    public void calculationsReport() {
        try(FileWriter writer = new FileWriter("Calculations.txt",true)) {
            writer.write(manager.ws.getJulianDay() + "\n");
            writer.write(manager.ws.getDate() + "\n");
            for(String key : this.manager.calculations.keySet()) {
                writer.write(key + ": " + this.manager.calculations.get(key) + "\n");
            }
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}