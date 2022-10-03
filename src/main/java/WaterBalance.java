
public class WaterBalance {
    
    public static WeatherStation ws;
    public static Field field;
    public static FormulaManager forManager;
    public static FileManager fileManager;
    
    public static void main(String[] args) {
        
        ws = new WeatherStation();
        field = new Field();
        forManager = new FormulaManager(ws, field);
        WaterBalance.fileManager = new FileManager();
        
        //Test dei metodi e delle tempistiche
        long start = System.currentTimeMillis();
        
        //Stazione meteo
        fileManager.stationFileSetup();
        fileManager.stationDatasRegistration();
        //fileManager.stationDatasReport();
        
        //Campo
        fileManager.fieldFileSetup();
        fileManager.fieldDatasRegistration();
        //fileManager.fieldDatasReport();
        
        //Chiamata funziona costanti per registrarle e salvarle su file
        forManager.constant();
        
        //Costanti del suolo
        fileManager.constantFileSetup();
        //fileManager.constantDatasReport();
        
        forManager.ete();
        fileManager.CalculationsReport();
        
        
        long end = System.currentTimeMillis();
        
        System.out.println("\nTime: " + (end-start) + " milliseconds");
    }
}
