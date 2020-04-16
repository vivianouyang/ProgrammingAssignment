package program;
import java.util.*; 

public class AlwaysDefect implements Player{
	
	public String name; 
	public List<Integer> playerHistory; 
	public int payOff; 
	
	public AlwaysDefect() {
		name = "AD"; 
		playerHistory = new ArrayList<Integer>(); 
		payOff = 0; 
	}
	
	public String getName() {
		return name;
	}

	public int action(int numOfRounds, List<Integer> opponentHistory) {
		return 1;
	}

	public List<Integer> getPlayerHistory() {
		return playerHistory;
	}

	public int getCumulativePayoff() {
		return payOff;
	}
	
	public void addPayoff(int i) {
		payOff += i; 
	}

	public void addHistory(int i) {
		playerHistory.add(i);
	}

	public void deleteHistory() {
		playerHistory.clear();
	}

	public void deletePayoff() {
		payOff = 0; 
	}
	
	public int compareTo(Player comparePlayer) {
		
		if(this.getCumulativePayoff() > comparePlayer.getCumulativePayoff()) {
			return 1; 
		}
		else if(this.getCumulativePayoff() < comparePlayer.getCumulativePayoff()) {
			return -1; 
		}
		else {
			return 0;
		}
	}
}
