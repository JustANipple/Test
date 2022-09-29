
public class WaterBalance {
    
    public static WeatherStation ws;
    public static Field field;
    public static FormulaManager forManager;
    public static FileManager fileManager;
    
    public static void main(String[] args) {
        
        ws = new WeatherStation();
        field = new Field();
        WaterBalance.fileManager = new FileManager();
        forManager = new FormulaManager(ws, field);
        
        //Test dei metodi e delle tempistiche
        long start = System.currentTimeMillis();
        
        fileManager.constantFileSetup();
        fileManager.constantDataRegistration();
        fileManager.constantDataReport();
        
        long end = System.currentTimeMillis();
        
        System.out.println("\nTime: " + (end-start) + " milliseconds");
    }
}
