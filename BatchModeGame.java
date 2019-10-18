import java.util.List;
import java.util.Scanner;

public class BatchModeGame {
    
    
    static Scanner console = new Scanner(System.in);
    
    public static void play(Punter punter, List<Die> dice) {
        System.out.println("\nplayBatchMode"); 
        
        int initialBalance = punter.getBalance();
        
        String ans = null;
        System.out.print("\nHow many games? (default 1000): ");
        ans = console.nextLine();
        int numberOfGames = 1000;        
        try {
            numberOfGames = Integer.parseInt(ans);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid entry, using default.");
        }
        
        int stdBet = 10;        
        System.out.print(String.format("\nEnter standard bet (default %d): ",stdBet));
        ans = console.nextLine();
        try {
            stdBet = Integer.parseInt(ans);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid entry, using default.");
        }

        boolean useStandardPick = false;
        Face pick = null;
        System.out.print(String.format("\nUse standard pick or random (S/R): "));
        ans = console.nextLine();
        if (ans.equalsIgnoreCase("S")) {
            String betPrompt = "Select Symbol: 1 - Fish, 2 - Prawn, 3 - Crab, 4 - Rooster, 5 - Gourd, 6 - Stag";
            System.out.println(betPrompt);
            ans = console.nextLine();
            int selection;
            try {
                selection = Integer.parseInt(ans);
                if (selection < 1 || selection > 6) {
                    throw new NumberFormatException();
                }
                pick = Face.getByIndex(selection-1);
                System.out.println(String.format("Using standard pick %s.", pick));
                useStandardPick = true;
            }
            catch (NumberFormatException e) {
                System.out.println(String.format("Invalid entry, using random pick."));
            }
        }
        System.out.println(String.format("\nStarting batch mode game for %s with initial balance %d, limit $%d, and standard bet %d.00", 
                    punter.getName(), initialBalance, punter.getLimit(), stdBet));
        
        
        int roundCount = 0;

        while (roundCount != numberOfGames && punter.balanceExceedsLimitBy(stdBet)) {
            if (!useStandardPick) {
                pick = Face.getRandom();
            }
            System.out.println(String.format("\n%s bets %d on %s, starting with balance $%d", 
                        punter.getName(), stdBet, pick, punter.getBalance()));
            
            int winnings = Round.play(punter, dice, pick, stdBet);
            roundCount++;
            
            System.out.println(String.format("\nRolled %s, %s, %s", 
                        dice.get(0).getFace(), dice.get(1).getFace(), dice.get(2).getFace()));
            
            if (winnings > 0) {
                System.out.println(String.format("\n%s won %d, balance now %d\n\n",
                        punter.getName(), winnings, punter.getBalance()));
            }
            else {
                System.out.println(String.format("\n%s lost %d, balance now %d\n\n",
                        punter.getName(), stdBet, punter.getBalance()));
            }
        }
        System.out.println(String.format("Player leaves game with $%d after %d rounds, having started with $%d", 
                punter.getBalance(), roundCount, initialBalance));
        
    }


}
