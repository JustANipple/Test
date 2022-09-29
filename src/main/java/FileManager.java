
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager{
    
    public FormulaManager manager;
    
    public FileManager() {
        this.manager = WaterBalance.forManager;
    }
    
    //Creazione e intestazione del file per stazione meteo
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
        try(FileWriter writer = new FileWriter("WSreport.txt",true)) {
            writer.write("minTemp" + "-" + "maxTemp" + "-" + "avgTemp" + "-" +
                    "rain" + "-" + "rs" + "-" + 
                    "rhMin" + "-" + "rhMin" + "-" + "rhMax" + "-" +
                    "wind" + "-" + "altitude" + "-" + "latitude" + "-" +
                    "date" + "\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //I dati possono essere richiesti per ognuno per inserirli manualmente
    //in questo caso vengono messi fissi riferendoci alla riga 8 dell'excel
    //inviato da Laura, per verificarne il funzionamento e sistemare i calcoli
    public void stationDatasRegistration() {
        manager.ws.getAvgTemp();
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
        
        System.out.println("minTemp(°C): " + manager.ws.getMinTemp());        
        System.out.println("maxTemp(°C): " + manager.ws.getMaxTemp());        
        System.out.println("avgTemp(°C): " + manager.ws.getAvgTemp());        
        System.out.println("rain(mm): " + manager.ws.getRain());
        System.out.println("rs(W/m^2): " + manager.ws.getRs());        
        System.out.println("rhMin(%): " + manager.ws.getRhMin());
        System.out.println("rhMax(%): " + manager.ws.getRhMax());
        System.out.println("wind(m/s): " + manager.ws.getWind());
        System.out.println("altitude(m): " + manager.ws.getAltitudine());
        System.out.println("latitude(m): " + manager.ws.getLatitudine());
        System.out.println("date(DD-MM-YYYY): " + manager.ws.getDate());
        System.out.println("JulianDay: " + manager.ws.getJulianDay());        
    }
    
    //Scrittura dei dati sul file
    public void stationDatasReport() {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append(manager.ws.getMinTemp()).append(";")
                .append(manager.ws.getMaxTemp()).append(";")
                .append(manager.ws.getAvgTemp()).append(";")
                .append(manager.ws.getRain()).append(";")
                .append(manager.ws.getRs()).append(";")
                .append(manager.ws.getRhMin()).append(";")
                .append(manager.ws.getRhMax()).append(";")
                .append(manager.ws.getAltitudine()).append(";")
                .append(manager.ws.getAltitudine()).append(";")
                .append(manager.ws.getLatitudine()).append(";")
                .append(manager.ws.getDate()).append("\n");
        try(FileWriter writer = new FileWriter("WSreport.txt",true)) {
            writer.write(firstRow.toString());
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
        try(FileWriter writer = new FileWriter("FieldReport.txt",true)) {
            writer.write("sabbia(%)" + "-" + "argilla(%)" + "-" +
                    "carbone organico/corg(%)" + "-" + "prof(mm)" + "-" + "kc" + "\n");
        } catch (Exception e) {
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
        
        System.out.println("sabbia(%): " + manager.field.getSabbia());
        System.out.println("argilla(%): " + manager.field.getArgilla());
        System.out.println("carbonio organico/corg(%): " + manager.field.getCorg());
        System.out.println("prof(mm): " + manager.field.getProfmm());
        System.out.println("kc: " + manager.field.getKc());
    }
    
    //Scrittura dei primi dati sul file
    public void fieldDatasReport() {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append(manager.field.getSabbia()).append(";")
                .append(manager.field.getArgilla()).append(";")
                .append(manager.field.getCorg()).append(";")
                .append(manager.field.getProfmm()).append(";")
                .append(manager.field.getKc()).append("\n");
        try(FileWriter writer = new FileWriter("FieldReport.txt",true)) {
            writer.write(firstRow.toString());
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
        try(FileWriter writer = new FileWriter("ConsReport.txt",true)) {
            writer.write("PROF mm" + "PA ");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Scrittura a console dei valori calcolati per le costanti
    public void constantDataRegistration() {
        System.out.println("PROFmm (mm): " + manager.calculations.get("PROFmm"));
        System.out.println("PA (%v/v): " + manager.calculations.get("PAperc"));
        System.out.println("Lir (%v/v): " + manager.calculations.get("LirPerc"));
        System.out.println("CC (%v/v): " + manager.calculations.get("CCperc"));
        System.out.println("CIM (%v/v): " + manager.calculations.get("CIMper"));
        System.out.println("PAmm (mm): " + manager.calculations.get("PAmm"));
        System.out.println("Lirmm (mm): " + manager.calculations.get("Lirmm"));
        System.out.println("CCmm (mm): " + manager.calculations.get("CCmm"));
        System.out.println("CIMmm (mm): " + manager.calculations.get("CIMmm"));     
    }
    
    //Scrittura su file delle costanti del suolo
    public void constantDataReport() {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append(manager.calculations.get("PROFmm")).append(";")
                .append(manager.calculations.get("PAperc")).append(";")
                .append(manager.calculations.get("LirPerc")).append(";")
                .append(manager.calculations.get("CCperc")).append(";")
                .append(manager.calculations.get("CIMper")).append(";")
                .append(manager.calculations.get("PAmm")).append(";")
                .append(manager.calculations.get("Lirmm")).append(";")
                .append(manager.calculations.get("CCmm")).append(";")
                .append(manager.calculations.get("CIMmm")).append("\n");
        try(FileWriter writer = new FileWriter("ConsReport.txt",true)) {
            writer.write(firstRow.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}