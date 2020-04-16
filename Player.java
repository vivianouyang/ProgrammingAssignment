package program;
import java.util.*; 

public interface Player extends java.lang.Comparable<Player>{

	public String getName(); 
	public List<Integer> getPlayerHistory();
	public int getCumulativePayoff(); 
 	public int action(int numOfRounds, List<Integer> opponentHistory);
 	public void addPayoff(int i);
 	public void addHistory(int i);
 	public void deleteHistory();
 	public void deletePayoff();
 	public int compareTo(Player comparePlayer); 
}
