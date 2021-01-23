package listeners;

public interface ChampionshipListenable {

	void modelAddedName(String name); // change me if needed, just examples
	void modelUpdatePlayerAlreadyAdded(String name);
	void modelUpdateWinner();
	void modelUpdateNoRoom();
	void modelUpdateNameInvalid(String message);
	public void modelSendGameFormat(int numOfRounds, String player1, String player2,int gameState,int gameId);// state: tie braker / penalties.. 
	void modelAlertScoresNotValid(int gameId, String headLine, String message);
	void modelAnnounceRoundResults(int gameId, String headLine, String message);

	
}
