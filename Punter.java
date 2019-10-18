
public class Punter {
    private static final int DEFAULT_LIMIT = 0;
    private static final int DEFAULT_BALANCE = 100;
    
    enum State { NOT_BETTING, BETTING, RECEIVING_WINNINGS };
    State state;
    
	private String name;
	private int balance;
    private int limit;
    private int currentBet;
	
    public Punter(String name, int balance, int limit) {
        if (name == null || name .isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (limit < 0 || limit > balance) {
            throw new IllegalArgumentException("Limit cannot be negative or greater than initial balance");
        }
        this.name = name;
        this.balance = balance;
        this.limit = limit;
        this.state = State.NOT_BETTING;
        this.currentBet = 0;
    }
        
    public Punter(String name, int balance) {
        this(name, balance, DEFAULT_LIMIT);
    }
        
    public Punter(String name) {
        this(name, DEFAULT_BALANCE, DEFAULT_LIMIT);
    }
        
	public String getName() { return name; }
	public int getBalance() { return balance; }
	public int getLimit() { return limit; }

	
	
	public void setLimit(int limit) {
		if (limit < 0) {
		    throw new IllegalArgumentException("Limit cannot be negative.");
		}
		if (limit > balance) {
		    throw new IllegalArgumentException("Limit cannot be greater than balance.");
		}
		if (state.equals(State.BETTING)) {
		    throw new RuntimeException("Cannot set limit while betting");
		}
		this.limit = limit;
	}

	
	
	public boolean balanceExceedsLimitBy(int amount) {
		return (balance - amount > limit);
	}

	
	
	public void placeBet(int bet) {
		if (bet < 0) {
		    throw new IllegalArgumentException("Bet cannot be negative.");
		}
		if (!balanceExceedsLimitBy(bet)) {
		    throw new IllegalArgumentException("Placing bet would go below limit.");
		}
        if (state.equals(State.NOT_BETTING)) {
            balance = balance - bet;
            currentBet = bet;
            state = State.BETTING;
        }
	}

	
	
    public void returnBet() {
        if (state.equals(State.BETTING)) {
            balance = balance + currentBet;
            currentBet = 0;
            state = State.NOT_BETTING;
        }
    }

    
    public void receiveWinnings(int winnings) {
        if (state.equals(State.RECEIVING_WINNINGS)) {
            if (winnings < 0) {
                throw new IllegalArgumentException("Winnings cannot be negative.");
            }       
            balance = balance + winnings;
            state = State.NOT_BETTING;
        }
	}

	
    public void loseBet() {
        if (state.equals(State.BETTING)) {
            balance = balance - currentBet;
            currentBet = 0;
            state = State.NOT_BETTING;
        }
    }

	
	public String toString() {
		return String.format("Player: %s, Balance: %d, Limit: %d", name, balance, limit);
	}

}
