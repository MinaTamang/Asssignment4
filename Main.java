import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
        String mainMenu = "\n" +
                "Fish Prawn Crab\n\n" +
                "Please Select:\n\n" +
                "\tP: Play interactively\n" +
                "\tB: Batch Mode\n\n" +
                "\tQ: Quit\n\n" +
                "Selection: ";
                
        Punter punter = null;
        List<Die> dice = setUpDice();
        
        String ans = null;
        boolean stop = false;
        while (!stop) {
            System.out.print(mainMenu);
            ans = console.nextLine();
            
            switch (ans.toUpperCase()) {
            
            case "P":
                punter = setUpPunter(console);
                InteractiveGame.play(punter, dice);
                break;
                
            case "B":
                punter = setUpPunter(console);
                BatchModeGame.play(punter, dice);
                break;
                
            case "Q": 
                stop = true;
                break;
                
            default:
                System.out.println("Invalid selection");                
            }   
        }
        console.close();
        System.out.println("\nTerminating");                
    }
    
    
    
    private static List<Die> setUpDice() {
        
        Die d1 = new Die();
        Die d2 = new Die();
        Die d3 = new Die();        
        List<Die> dice = new ArrayList<>(Arrays.asList(d1, d2, d3));

        return dice;
    }



    private static Punter setUpPunter(Scanner console) {
        
        System.out.print("\nEnter player name: ");
        String name = console.nextLine();
        
        String ans = null;
        int initialBalance = 100;
        System.out.print(String.format("\nEnter initial balance (default %d): ", initialBalance));
        ans = console.nextLine();
        try {
            initialBalance = Integer.parseInt(ans);
        }
        catch (NumberFormatException e) {
            System.out.println(String.format("Invalid entry, using default: %d.", initialBalance));
        }
        
        int limit = 10;        
        System.out.print(String.format("\nEnter limit (default %d): ", limit));
        ans = console.nextLine();
        try {
            limit = Integer.parseInt(ans);
        }
        catch (NumberFormatException e) {
            System.out.println(String.format("Invalid entry, using default: %d.", limit));
        }
        
        System.out.println(String.format("\nInitialising player %s with balance $%d.00 and limit $%d.00", 
                    name, initialBalance, limit));
        
        Punter punter = new Punter(name, initialBalance, limit);
        
        return punter;       
    }

}
