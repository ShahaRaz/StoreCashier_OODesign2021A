package controller;
import view.View;

import java.util.ArrayList;

import listeners.ChampionshipListenable;
import listeners.ViewListenable;
import model.Model;


public class Controller implements ChampionshipListenable , ViewListenable {
	private Model theModel;
	private View theView;
	

	public Controller(Model m, View v) {
		theModel = m;
		theView = v;
		
		theModel.registerListener(this);
		theView.registerListener(this); //throws
	}


	@Override
	public void modelUpdateWinner() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modelUpdateNoRoom() {
		theView.alertNoRoom();
	}




	@Override
	public void modelAddedName(String name) {
		theView.addNewName(name);
	}
	
	@Override
	public void modelSendGameFormat(int numOfRounds, String player1, String player2,int gameState,int gameId) {
		theView.opensPlayGameWindow(numOfRounds,player1,player2,gameState,gameId);
	}


	@Override
	public void modelUpdatePlayerAlreadyAdded(String name) {
		theView.alertPlayerAlreadyExists(name);
	}

	@Override
	public void modelUpdateNameInvalid(String message) {
		theView.alertNameNotValid(message);
	}





	@Override
	public void modelAlertScoresNotValid(int gameId, String headLine, String message) {
		theView.alertScoreBoardNotValid(gameId,headLine,message);
	}


	@Override
	public void modelAnnounceRoundResults(int gameId, String headLine, String message) {
		theView.announceGameResults(gameId,headLine,message);
	}


	@Override
	public void viewAskToPlayGame(ArrayList<Integer> p1Score, ArrayList<Integer> p2Score, int gameStage, int gameId) {
		// view needs to send all the boxes of input to 2 arraylists
		theModel.checkScoreBoard(gameStage, p1Score, p2Score,gameId);
		
		// TODO trace forward & backward the logic here \
		// ALSO back to the view: if results arnt valid... written in one of the functions.
	}


	@Override
	public void viewStartChamp(String gameType) {
		theModel.setTourType(gameType);
	}


	@Override
	public void viewSetGamesType() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void viewAskForGameFormat(int gameId,int gameState) {
		System.out.println("in new at viewAskForGameFormat(), game state: " +gameState);
		theModel.sendPlayerNameNRounds(gameId,gameState);
	}


	@Override
	public void viewAskToAddPlayer(String name) {
		theModel.addPlayer(name);
	}


	@Override
	public void viewDeclareEndOfLeaugeRound() {
		theModel.filterLosersFromTeams();
	}


		//DEFINITIONS
	// gameNumber / gameId - Define game in current round (8 players - 4 games -> gameNumber [0,3]first round, [4,5] second round [6] last round)
	// gameStage - [0 - regular], [1 - ExtraTime], [2 - Penalties] (in football), in tennis [1=2=3=...n (till tieBreak)]
	// gameType 
	// numOfRounds - how many rounds there are in a game -> 
	//	-> for example: football has 2 rounds in regular, 2 rounds in extraTime & 5 rounds[shots] in penalties

}