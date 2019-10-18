import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Tester {
	@Test
	public void bug1Test()
	{
		Punter punter = new Punter("Test Punter",100,20);
		punter.placeBet(10);
		punter.state = Punter.State.BETTING;
		punter.loseBet();
		assertEquals(90, punter.getBalance());

	}

	@Test
	public void bug2Test()
	{
		Punter punter = new Punter("Test Punter",100,20);
		punter.placeBet(10);
		//punter.state = Punter.State.BETTING;
		punter.returnBet();
		punter.receiveWinnings(10);
		assertEquals(110, punter.getBalance());
	}

	@Test
	public void bug3Test()
	{
		Punter punter = new Punter("Test Punter",100,20);
		punter.placeBet(80);

	}

	@Test
	public void bug4Test()
	{
		int win =0;
		for(int i=0;i<10000;i++)
		{
			Die d1 = new Die();
			Die d2 = new Die();
			Die d3 = new Die();        
			List<Die> dice = new ArrayList<>(Arrays.asList(d1, d2, d3));
			int matches=0;
			for (Die d : dice) {
				d.roll();
				if (d.getFace().equals(Face.getByIndex(0))) { 
					matches += 1;
				}
			}
			if(matches>0)
				win++;
		}
		assertEquals(0.42, win/10000.0,0.01);

	}
}
