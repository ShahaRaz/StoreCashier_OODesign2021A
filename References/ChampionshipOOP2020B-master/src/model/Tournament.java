package model;

import java.util.ArrayList;


public class Tournament {
	int sum1,sum2;
	public enum gameType {
		Tennis, Basketball, Soccer
	};

	private gameType type;
	private Set teams;

	public Tournament() {
		teams = new Set();
	}

	public boolean addParticipant(Player partic) {
		return teams.add(partic);
	}

	public gameType getType() {
		return type;
	}

	protected void filteringLostPlayers() {
		System.out.println("___________________filteringLostPlayers__________________");
		for(Player p : teams.getList()) {
			System.out.print( p + "\t");
		}
		Set tempTeam = new Set();
		if(teams.size()==1)
			return; //should'nt get here at-all
		int winningScore = getWonScore();
		for(Player p:teams.getList()) { // only half won
			if(p.getWinsCounter()==winningScore)
				tempTeam.add(p);
		}
		this.teams=tempTeam;
		
	}
	
	private int getWonScore() {
		//getting score from two first, since one of them must have the winning count of victorius
		int score0 = teams.get(0).getWinsCounter();
		int score1 = teams.get(1).getWinsCounter();
		if(score0>score1) 
			return score0;
		else
			return score1;

	}
	
	public void secondRound() {
		Set teams4 = new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 1)
				teams4.add(teams.get(i));
		}
		setTeams(teams4);
		System.out.println("moving to the semi-finals ! - 4 teams are left");
	}

	public void thirdRound() {
		Set teams2 = new Set();
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 2)
				teams2.add(teams.get(i));
		}
		setTeams(teams2);
		System.out.println("moving to the finals ! - 2 teams are left");
	}

	public Player getWinner() {
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			if (teams.get(i).getWinsCounter() == 3)
				return teams.get(i);
		}
		return null;
	}

	public Set getTeams() {
		return teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

	public void setType(String name) {
		this.type = gameType.valueOf(name);
	}

	public void showParticipants() {
		teams.showList();
	}

	public int getNumOfPlayerInRound() {
		return teams.size();
	}

	public void reset() {
		for (int i = 0; i < getNumOfPlayerInRound(); i++) {
			teams.reset();

		}
	}

	public int getNumberOfRounds(int gameState) {
		switch (type) {
		case Basketball:
			if (gameState < 4)
				return 4;
			else
				return 1;
		case Soccer:
			if (gameState < 4)
				return 2;
			else if (gameState == 4)
				return 5;
			else // gameState > 8 penalties 1-1
				return 1;
		case Tennis:
			return 5; // TODO if 3-0 happens, we also stop
		default:
			return -1; // noTypeSelected
		}
	}

	private boolean enterRoundScore(int gameStage, int p1Score, int p2Score) {
		// gameStage will say which part (for example, basketBall have 4 quarters, then
		// 2 even-brakers,
		// so valid input for basketball games would be betweeen 1& 6 //
		// must use the entire logic in the model for each score
		// FIXME

		switch (type) {
		case Tennis:
			
			if (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
					|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
					|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12) {
				System.out.println("not valid scores , try again"); // fire NOT VALID SCORE
				return false;
			} else {
				if (p1Score > p2Score)
					sum1++;
				else
					sum2++;
				return true;
			}
		case Basketball:
			if (p1Score < 0 || p2Score < 0) {
				System.out.println("fire NOT VALID"); // OR THROWS
				return false;
			} else {
				sum1 += p1Score;
				sum2 += p2Score;
				return true;
			}
		case Soccer:
			if ((p1Score < 0 || p2Score < 0) && (gameStage != 2)) {
				System.out.println("fire NOT VALID"); // OR THROWS
				return false;
			} else {
				if ((p1Score < 0 || p2Score < 0 || p2Score > 1 || p1Score > 1) && (gameStage == 2)) {
					System.out.println("fire NOT VALID"); // OR THROWS
					return false;
				}
			}
			sum1 += p1Score;
			sum2 += p2Score;
			return true;
		}

		return true; // all
	}

	protected String checkBoard(int gameStage, ArrayList<Integer> p1Score, ArrayList<Integer> p2Score, int gameId) {
		sum1 = 0;
		sum2 = 0;
		int i=0;
		int totalRoundsPlayed = p1Score.size(); // for readability
		for(Integer ingr:p1Score) {
			System.out.print(ingr);
		}
		System.out.println("\nin Tour.checkBoard() , totalRoundsPlayed = " + totalRoundsPlayed+"\ni= " + i);
		for (; i < totalRoundsPlayed; i++) {
			System.out.println("i = " + i + "sum1 = " + sum1 + "\tsum2 = " + sum2);
			if (!enterRoundScore(i, p1Score.get(i), p2Score.get(i)))  { //updates sum1&sum2
				return printInstructions(i + 1);			
			}

		}
		System.out.println("sum1 = " + sum1 + "\tsum2 = " + sum2);

		String winnerName;
		if (sum1 > sum2) {
			// player 1 win logic
			winnerName=teams.get(2*gameId).getName();
			teams.upWins(2*gameId);
			return winnerName + "@"  + winnerName + "Won and go to next stage!";
			
		} else {
			if (sum2 > sum1) {
				winnerName=teams.get((2*gameId)+1).getName();
				teams.upWins((2*gameId ) + 1);
				return winnerName + "@"  + winnerName + "Won and go to next stage!";
			} else {
				// draw
				gameStage=gameStage+i;
				return"TIE@" + gameStage +" rounds and scores are equal we still don't have a winner";

			}
		}
	}

	private String printInstructions(int gameStage) { /* need to change syso to fx messages */
		// TODO print to view a short explanation of valid values
		// maybe add a red boxes on invalid rounds
		
		switch (type) {
		case Tennis:
			return"ERROR in round #" +gameStage + "@ set scores need to be between [0-6] while 6 is the goal in order"
					+ " to win \n with difference of 2 points at least,"
					+ " if both players win at least 5 games the winner must win 7 games ";
		case Soccer:
			if (gameStage <5)
				return"ERROR in round #" +gameStage + "@scores must be higher than 0";
			else
				return
						"ERROR in round #" +gameStage + "@we are in deciding penalties phase , goal = 1 , miss = 0"
				+ " \n other values are not valid!";

		case Basketball:
			return "ERROR in round #" +gameStage + "@scores must be higher than 0";
			
		default: 
			return "ERROR no game type selected!@please select from the radio-box";
		}
	}
	protected void resetSums() {
		sum1=0;
		sum2=0;
	}
	

}
