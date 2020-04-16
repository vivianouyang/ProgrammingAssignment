package program;
import java.util.*; 

public class Grudger implements Player{

	public String name;
	
	public List<Integer> playerHistory; 

	public int payOff; 
	
	public Grudger() {
		name = "G"; 
		playerHistory = new ArrayList<Integer>();
		payOff = 0; 
	}
	
	public String getName() {
		return name;
	}

	public int action(int numOfRounds, List<Integer> opponentHistory) {
		if(numOfRounds == 0) {
			return 0; 
		} 
		else if(opponentHistory.size() != 0 && !hasDefect(opponentHistory)){
			return 0;
		}
		else {
			return 1; 
		}
		
	}
	
	public boolean hasDefect(List<Integer> opponentHistory) {
		for(Integer in : opponentHistory) {
			if(in.intValue() == 1) {
				return true;
			}
		}
		return false; 
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
