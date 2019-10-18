import java.util.List;

public class Round {

	
	public static int play(Punter punter, List<Die> dice, Face pick, int bet ) {		
		if (punter == null) {
		    throw new IllegalArgumentException("Punter cannot be null.");
		}
		if (pick == null) {
		    throw new IllegalArgumentException("Pick cannot be null.");
		}
		if (bet < 0) {
		    throw new IllegalArgumentException("Bet cannot be negative.");
		}

        punter.placeBet(bet);
        
		int matches = 0;
		for (Die d : dice) {
		    d.roll();
			if (d.getFace().equals(pick)) { 
				matches += 1;
			}
		}
		int winnings = matches * bet;

		if (matches > 0) {	
		    punter.returnBet();
			punter.receiveWinnings(winnings);
		}
		else {
		    punter.loseBet();
		}
		
        return winnings;		
	}
	
}
