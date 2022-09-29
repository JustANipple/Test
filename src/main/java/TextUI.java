
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pc
 */
public class TextUI {
    
    private final Scanner scanner;
    private WeatherStation ws;
    private Field field;
    
    public TextUI() {
        this.scanner = new Scanner(System.in);
        this.ws = new WeatherStation();
        this.field = new Field();
    }
    
    public void start() {
        //ws.stationFileSetup();
        //ws.stationDatasRegistration();
        //ws.stationDatasReport();
        
        //field.FieldFileSetup();
        //field.FieldDatasRegistration();
        //field.FieldDatasReport();
    }
}
