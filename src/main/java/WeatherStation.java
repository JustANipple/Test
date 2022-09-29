
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        setMinTemp(0.3);
        setMaxTemp(1.3);
        setAvgTemp(0.8);
        setRain(3.8);
        setRs(0.86);
        setRhMin(90);
        setRhMax(94);
        setWind(0.4);
        setAltitudine(232);
        setLatitudine(0.785281808113982);
        setDate("01-01-2021");
        String[] parts = getDate().split("-");
        setJulianDay(Integer.valueOf(parts[0]));
        
        System.out.println("minTemp(°C): " + getMinTemp());        
        System.out.println("maxTemp(°C): " + getMaxTemp());        
        System.out.println("avgTemp(°C): " + getAvgTemp());        
        System.out.println("rain(mm): " + getRain());
        System.out.println("rs(W/m^2): " + getRs());        
        System.out.println("rhMin(%): " + getRhMin());
        System.out.println("rhMax(%): " + getRhMax());
        System.out.println("wind(m/s): " + getWind());
        System.out.println("altitude(m): " + getAltitudine());
        System.out.println("latitude(m): " + getLatitudine());
        System.out.println("date(DD-MM-YYYY): " + getDate());
        System.out.println("JulianDay: " + getJulianDay());        
    }
    
    //Scrittura dei dati sul file
    public void stationDatasReport() {
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
                .append(this.date).append("\n");
        try(FileWriter writer = new FileWriter("WSreport.txt",true)) {
            writer.write(firstRow.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
