
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WeatherStation {
    
    private double minTemp;
    private double maxTemp;
    private double avgTemp;
    private double rain;
    private double rs;
    private double rhMin;
    private double rhMax;
    private double wind;
    private double altitudine;
    private double latitudine;
    private String date;
    private int julianDay;
    
    public WeatherStation() {
        
        this.minTemp = 0;
        this.maxTemp = 0;
        this.avgTemp = 0;
        this.rs = 0;
        this.rain = 0;
        this.rhMin = 0;
        this.rhMax = 0;
        this.wind = 0;
        this.altitudine = 0;
        this.latitudine = 0;
        this.date = "";
        this.julianDay = 0;
        
    }
    
    public double getMinTemp() {
        return minTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
    public double getAvgTemp() {
        return avgTemp;
    }
    public double getRain() {
        return rain;
    }
    public double getRs() {
        return rs;
    }
    public double getRhMin() {
        return rhMin;
    }
    public double getRhMax() {
        return rhMax;
    }
    public double getWind() {
        return wind;
    }
    public double getAltitudine() {
        return altitudine;
    }
    public double getLatitudine() {
        return latitudine;
    }
    public String getDate() {
        return date;
    }
    public int getJulianDay() {
        return julianDay;
    }


    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }
    public void setRain(double rain) {
        this.rain = rain;
    }
    public void setRs(double rs) {
        this.rs = rs;
    }
    public void setRhMin(double rhMin) {
        this.rhMin = rhMin;
    }
    public void setRhMax(double rhMax) {
        this.rhMax = rhMax;
    }
    public void setWind(double wind) {
        this.wind = wind;
    }
    public void setAltitudine(double altitudine) {
        this.altitudine = altitudine;
    }
    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setJulianDay(int julianDay) {
        this.julianDay = julianDay;
    }
    
    //Intestazione del file di report della WS
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
    
    //Scrittura dei primi dati chiesti da console per simulazione dei dati
    //Potrebbe essere sostituita con dei valori standard che vengono randomizzati
    public void stationFirstDatas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("minTemp(°C):");
        setMinTemp(Double.valueOf(scanner.nextLine()));
        System.out.println("maxTemp(°C):");
        setMaxTemp(Double.valueOf(scanner.nextLine()));
        System.out.println("avgTemp(°C):");
        setAvgTemp(Double.valueOf(scanner.nextLine()));
        System.out.println("rain(mm):");
        setRain(Double.valueOf(scanner.nextLine()));
        System.out.println("rs(W/m^2):");
        setRs(Double.valueOf(scanner.nextLine()));
        System.out.println("rhMin(%):");
        setRhMin(Double.valueOf(scanner.nextLine()));
        System.out.println("rhMax(%):");
        setRhMax(Double.valueOf(scanner.nextLine()));
        System.out.println("wind(m/s)");
        setWind(Double.valueOf(scanner.nextLine()));
        System.out.println("altitude(m):");
        setAltitudine(Double.valueOf(scanner.nextLine()));
        System.out.println("latitude(m):");
        setLatitudine(Double.valueOf(scanner.nextLine()));
        System.out.println("date(DD-MM-YYYY)");
        String dmy = scanner.nextLine();
        setDate(dmy);
        String[] parts = dmy.split("-");
        setJulianDay(Integer.valueOf(parts[0]));
        
    }
    
    //Scrittura sul file dei dati impostati da StationFirstDatas
    //Si potrebbe direttamente chiamare questo metodo una volta generati randomicamente i valori, in modo da scriverli subito
    public void stationFirstRegistration() {
        StringBuilder firstRow = new StringBuilder();
        firstRow.append(this.minTemp).append(";")
                .append(this.maxTemp).append(";")
                .append(this.avgTemp).append(";")
                .append(this.rain).append(";")
                .append(this.rs).append(";")
                .append(this.rhMin).append(";")
                .append(this.rhMin).append(";")
                .append(this.rhMax).append(";")
                .append(this.wind).append(";")
                .append(this.altitudine).append(";")
                .append(this.latitudine).append(";")
                .append(this.date);
        try(FileWriter writer = new FileWriter("WSreport.txt",true)) {
            writer.write(firstRow.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
