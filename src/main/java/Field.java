
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Field {
    
    private double sabbia;
    private double argilla;
    private double corg;
    private double profmm;
    private double kc;    
    
    public Field() {
        
        this.sabbia = 0;
        this.argilla = 0;
        this.corg = 0;
        this.profmm = 0;
        this.kc = 0;
        
    }

    public double getSabbia() {
        return sabbia;
    }
    public double getArgilla() {
        return argilla;
    }
    public double getCorg() {
        return corg;
    }
    public double getProfmm() {
        return profmm;
    }
    public double getKc() {
        return kc;
    } 

    public void setSabbia(double sabbia) {
        this.sabbia = sabbia;
    }
    public void setArgilla(double argilla) {
        this.argilla = argilla;
    }
    public void setCorg(double corg) {
        this.corg = corg;
    }
    public void setProfmm(double profmm) {
        this.profmm = profmm;
    }
    public void setKc(double kc) {
        this.kc = kc;
    }
    
    //Creazione e intestazione del file di campo
    public void FieldFileSetup() {
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
    
    //Dato che per un campo di sensori c'è solo una riga di valori che servono per
    //calcolare le costanti del suolo
    //Non solo valori simulabili con la randomizzazione, quindi conviene prenderli fissi da file
    
    //Richiesta dei dati da inserire e settaggio dei parametri
    public void FieldFirstDatas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("sabbia(%):");
        setSabbia(Double.valueOf(scanner.nextLine()));
        System.out.println("argilla(%):");
        setArgilla(Double.valueOf(scanner.nextLine()));
        System.out.println("carbonio organico/corg(%):");
        setCorg(Double.valueOf(scanner.nextLine()));
        System.out.println("prof(mm):");
        setProfmm(Double.valueOf(scanner.nextLine()));
        System.out.println("kc");
        setKc(Double.valueOf(scanner.nextLine()));
    }
    
    //Chiamare questo metodo direttamente dopo la richiesta dei primi dati
    
    //Scrittura dei primi dati sul file
    public void FieldFirstRegistration() {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append(this.sabbia).append(";")
                .append(this.argilla).append(";")
                .append(this.corg).append(";")
                .append(this.profmm).append(";")
                .append(this.kc);
        try(FileWriter writer = new FileWriter("FieldReport.txt",true)) {
            writer.write(firstRow.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
