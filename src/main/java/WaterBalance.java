
public class WaterBalance {
    
    public static void main(String[] args) {
           
        //da utilizzare sulla piattaforma per far inserire SO o Corg
        /*
        System.out.println("1.SO\n2.Corg");
        int choice = Integer.valueOf(scanner.nextLine());
        double corg = 0;
        while(true) {
            if(choice == 1) {
                System.out.println("SO:");
                corg = Double.valueOf(scanner.nextLine()) / 1.725;
                break;
            } else if (choice == 2) {
                System.out.println("Corg:");
                corg = Double.valueOf(scanner.nextLine());
                break;
            }
        }
        */        
        
        long start = System.currentTimeMillis();
        
        TextUI ui = new TextUI();
        ui.start();
        
        long end = System.currentTimeMillis();
        
        System.out.println("Time: " + (end-start) + " milliseconds");
    }
}
