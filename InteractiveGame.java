import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InteractiveGame {
    
    static Scanner console = new Scanner(System.in);
    
    public static void play(Punter punter, List<Die> dice) {
        System.out.println("\nplayInteractive"); 
        
        int initialBalance = punter.getBalance();

        int stdBet = 10;        
        System.out.print(String.format("\nEnter standard bet (default %d): ",stdBet));
        String ans = console.nextLine();
        try {
            stdBet = Integer.parseInt(ans);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid entry, using default.");
        }
        
        System.out.println(String.format("\nStarting interactive game for %s with initial balance $%d.00, limit $%d.00, and standard bet %d.00", 
                    punter.getName(), initialBalance, punter.getLimit(), stdBet));
        String betPrompt = "Select Symbol: 1 - Fish, 2 - Prawn, 3 - Crab, 4 - Rooster, 5 - Gourd, 6 - Stag";
        
        Random random = new Random();
        int roundCount = 0;
        boolean stop = false;
        while (!stop) {
            int selection;
            Face pick = null;

            System.out.println(betPrompt);
            ans = console.nextLine();
            try {
                selection = Integer.parseInt(ans);
                if (selection < 1 || selection > 6) {
                    throw new NumberFormatException();
                }
                pick = Face.getByIndex(selection-1);
                System.out.println(String.format("Selected %s.", pick));
            }
            catch (NumberFormatException e) {
                selection = random.nextInt(6);
                pick = Face.getByIndex(selection);
                System.out.println(String.format("Invalid entry, using %s.", pick));
            }
            
            System.out.print(String.format("\nEnter  bet (default $%d): ", stdBet));
            ans = console.nextLine();
            int bet = stdBet;        
            try {
                bet = Integer.parseInt(ans);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid entry, using default.");
            }
            
            if (!punter.balanceExceedsLimitBy(bet)) {
                System.out.println(String.format("Betting %d could go below limit, voiding bet", bet));
                continue;
            }
            
            System.out.println(String.format("\n%s bets %d on %s, starting with balance $%d", 
                        punter.getName(), bet, pick, punter.getBalance()));
            
            int winnings = Round.play(punter, dice, pick, bet);
            roundCount++;
            
            System.out.println(String.format("\nRolled %s, %s, %s", 
                        dice.get(0).getFace(), dice.get(1).getFace(), dice.get(2).getFace()));
            
            if (winnings > 0) {
                System.out.println(String.format("\n%s won %d, balance now %d\n\n",
                        punter.getName(), winnings, punter.getBalance()));
            }
            else {
                System.out.println(String.format("\n%s lost %d, balance now %d\n\n",
                        punter.getName(), bet, punter.getBalance()));
            }
            
            System.out.println("\nPlay again (Y/N)? (default: Y) ");
            ans = console.nextLine();
            if (ans.equalsIgnoreCase("N")) {
                stop = true;
            }
            
        }
        System.out.println(String.format("Player leaves game with $%d after %d rounds, having started with $%d", 
                punter.getBalance(), roundCount, initialBalance));
        
    }

}
