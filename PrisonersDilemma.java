package program;
import java.util.*; 

public class PrisonersDilemma {
	
	public static int n = 100; 
	
	public static int m = 5; 
	
	public static double p = 0.05; 
	
	public static int k = 20; 
	

	
	public static void payoff(Player one, Player two, int numOfRounds) {
		
		int oneMove = one.action(numOfRounds, two.getPlayerHistory()); 
		int twoMove = two.action(numOfRounds, one.getPlayerHistory()); 
		
		one.addHistory(oneMove);
		two.addHistory(twoMove);
		
		
		if(oneMove == 0 && twoMove == 0) {
			one.addPayoff(3);
			two.addPayoff(3);
		}
		
		if(oneMove == 0 && twoMove == 1) {
			two.addPayoff(5);
		}
		
		if(oneMove == 1 && twoMove == 0) {
			one.addPayoff(5);
		}
		
		if(oneMove == 1 && twoMove == 1) {
			one.addPayoff(1); 
			two.addPayoff(1);
		}
	}
	
	public static void play(List<Player> allPlayers) {
		for(int i = 0; i < allPlayers.size() - 1; i ++) {
			for(int j = i+1; j < allPlayers.size(); j++) {
				for(int k = 0; k < m; k ++) {
					payoff(allPlayers.get(i), allPlayers.get(j), k);
				}
				
				allPlayers.get(i).deleteHistory();
				allPlayers.get(j).deleteHistory();
				
			}
		}
	}
	
	public static void generation(List<Player> allPlayers, int generation) {
		double tft = 0; 
		int totalTft = 0; 
		double g = 0; 
		int totalG = 0; 
		double ac = 0; 
		int totalAC = 0; 
		double ad = 0; 
		int totalAD = 0; 
		
		for(Player play: allPlayers) {
			if(play.getName().equals("T4T")) {
				tft += 1; 
				totalTft += play.getCumulativePayoff(); 
			}
			
			if(play.getName().equals("G")) {
				g += 1; 
				totalG += play.getCumulativePayoff();
			}
			
			if(play.getName().equals("AC")) {
				ac += 1; 
				totalAC += play.getCumulativePayoff(); 
			}
			
			if(play.getName().equals("AD")) {
				ad += 1; 
				totalAD += play.getCumulativePayoff(); 
			}
		}
		
		
		double avgTft = totalTft / (tft); 
		if(tft == 0) {
			avgTft = 0; 
		}
		double avgG = totalG / (g); 
		if(g == 0) {
			avgG = 0; 
		}
		double avgAC = totalAC / (ac); 
		if(ac == 0) {
			avgAC = 0; 
		}
		double avgAD = totalAD / (ad); 
		if(ad == 0) {
			avgAD = 0; 
		}
		
		
		tft = (tft/n) * 100; 
		g = (g/n) * 100; 
		ac = (ac/n) * 100; 
		ad = (ad/n) * 100; 
		
		
		int total = totalTft + totalG + totalAC + totalAD; 
		
		System.out.println("Generation " + (generation + 1) + ": TFT:" + (int)tft + " G:" + (int)g + " AC:" + (int)ac + " AD:" + (int)ad);
		System.out.println("Generation " + (generation + 1) + ": TFT:" + totalTft + " G:" + totalG + " AC:" + totalAC + " AD:" + totalAD + " TOTAL:" + total); 
		System.out.println("Generation " + (generation + 1) + ": TFT:" + avgTft + " G:" + avgG + " AC:" + avgAC + " AD:" + avgAD);
		System.out.println(); 
	
	}
	
	
	public static void removeAndReplicate(List<Player> allPlayers, double p) {
		int numToRemove = (int)(p * n); 
	
		Collections.sort(allPlayers, Collections.reverseOrder());
		
		for(int i = 0; i < numToRemove; i++) {
			allPlayers.remove(allPlayers.size() - 1); 
		}
		
		for(int i = 0; i < numToRemove; i++) {
			Player clonePlayer; 
			if(allPlayers.get(i).getName().equals("T4T")) {
				clonePlayer = new TitForTat(); 
			}
			else if(allPlayers.get(i).getName().equals("G")) {
				clonePlayer = new Grudger();
			}
			else if(allPlayers.get(i).getName().equals("AC")) {
				clonePlayer = new AlwaysCooperate();
			}
			else {
				clonePlayer = new AlwaysDefect();
			}
			
			allPlayers.add(clonePlayer); 
		}
		
		
	}

	
	public static void main(String[] args) {
		List<Player> allPlayers = new ArrayList<Player>();
		
//		for(int i = 0; i < n; i ++) {
//			if(i%4 == 0) {
//				allPlayers.add(new TitForTat()); 
//			}
//			else if (i%4 == 1) {
//				allPlayers.add(new Grudger()); 
//			}
//			else if (i%4 == 2) {
//				allPlayers.add(new AlwaysCooperate()); 
//			}
//			else {
//				allPlayers.add(new AlwaysDefect()); 
//			}
//		}
		
		for(int i = 0; i < 34; i ++) {
			allPlayers.add(new AlwaysDefect()); 
		}
		
		for(int i = 0; i < 33; i++) {
			allPlayers.add(new TitForTat()); 
		}
		
		for(int i = 0; i < 33; i++) {
			allPlayers.add(new AlwaysCooperate()); 
		}
		
		
		for(int i = 0; i < k; i++) {
			play(allPlayers);
			
			generation(allPlayers, i); 
			
			removeAndReplicate(allPlayers, p); 
			
			
			for(Player play: allPlayers) {
				play.deletePayoff();
			}

		}
	
		
		
	}
	
	
}
