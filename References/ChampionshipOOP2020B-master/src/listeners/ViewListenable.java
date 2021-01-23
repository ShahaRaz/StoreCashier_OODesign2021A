package listeners;

import java.util.ArrayList;

public interface ViewListenable {
	 // change me if needed, just example

	public void viewAskToPlayGame(ArrayList<Integer> p1Score,ArrayList<Integer> p2Score,
			int gameStage,int gameId);
	
	void viewAskToAddPlayer(String name);
	
	void viewStartChamp(String gameType); 
	
	void viewSetGamesType(); // Tennis/Basketball/soccer
	
	public void viewAskForGameFormat(int gameNumber,int gameState);

	public void viewDeclareEndOfLeaugeRound();

	
}


//gameStage  = 0 - Normal , 1 - Extra-Time , 2 - Pendelties
// gameId =    which game is playing (game 0 is between players 0 and 1, game 2 is between 4 and 5) 

