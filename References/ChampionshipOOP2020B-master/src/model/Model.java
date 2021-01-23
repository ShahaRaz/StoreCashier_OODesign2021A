package model;

import java.util.ArrayList;
import java.util.Scanner;

import listeners.ChampionshipListenable;

public class Model {
	public final static int NUMBER_OF_PLAYERS = 8;
	Tournament tour = new Tournament();
	String gameChoice;
	private ArrayList<ChampionshipListenable> allListeners;

	public Model() {
		allListeners = new ArrayList<ChampionshipListenable>();

	}

	public Model(Scanner scan) {
		chooseGameType(scan);
		System.out.println(this.tour.getType().name());
		mainMenu(scan);
	}
	private void chooseGameType(Scanner scan) {
		boolean flag = false;
		String gameMenu = "Menu:\n 1) Tennis (1)\n 2) Basketball (2)\n" + " 3) Soccer (3)\n";
		do {
			System.out.println(gameMenu);
			gameChoice = scan.nextLine();
			switch (gameChoice) {
			case "1":
				setTourType("Tennis");
				flag = true;
				break;
			case "2":
				setTourType("Basketball");
				flag = true;
				break;
			case "3":
				setTourType("Soccer");
				flag = true;
				break;
			default:

				System.out.println("wrong input,try again");
			}

		} while (flag != true);

	}
	
	public void setTourType(String gameType) {
		tour.setType(gameType);
	}

	private void mainMenu(Scanner scan) {

		String mainMenu = "Menu:\n 1) Add participant\n 2) Start Championship\n " + "3) Exit\n 4) Auto add";
		boolean flag = false;
		do {
			System.out.println(mainMenu);
			String mainChoice = scan.nextLine();
			String playerName;
			switch (mainChoice) {
			case "1":
				System.out.println("Enter the Team/Participant name");
				playerName = scan.nextLine();
				addPlayer(playerName);
				break;
			case "2":
				if (tour.getNumOfPlayerInRound() != NUMBER_OF_PLAYERS)
					System.out.println("you must insert 8 teams/participants in order to start a championship!");
				else {
					System.out.println("You chose to start a Championship ! ");
					tour.showParticipants();
					playChampionship(tour, scan);
				}
				break;

			case "3":
				System.out.println("Bye thank you for playing ");
				flag = true;
				break;
			case "4":
				for (char alphabet = 'A'; alphabet < NUMBER_OF_PLAYERS + 'A'; alphabet++) {
					addPlayer(Character.toString(alphabet));
				}
				break;

			default:
				System.out.println("wrong input,try again");
			}

		} while (flag != true);

	}

	public void playChampionship(Tournament tour, Scanner scn) {
		System.out.println("The " + tour.getType().name() + " Championship has begun!");
		for (int i = 0; i < tour.getNumOfPlayerInRound(); i += 2) {
			playGame(tour, tour.getTeams().get(i), tour.getTeams().get(i + 1), scn);
			
		}
		for (int i = 0; i < tour.getNumOfPlayerInRound(); i++) {
			if (tour.getTeams().get(i).getWinsCounter() == 1)
				System.out.println(tour.getTeams().get(i));

		}
		tour.secondRound();
		for (int i = 0; i < tour.getNumOfPlayerInRound(); i += 2) {
			playGame(tour, tour.getTeams().get(i), tour.getTeams().get(i + 1), scn);
		}
		for (int i = 0; i < tour.getNumOfPlayerInRound(); i++) {
			if (tour.getTeams().get(i).getWinsCounter() == 2)
				System.out.println(tour.getTeams().get(i));
		}
		tour.thirdRound();
		playGame(tour, tour.getTeams().get(0), tour.getTeams().get(1), scn);
		System.out
				.println("The winning team of the " + tour.getType().name() + " Championship is : " + tour.getWinner());
		tour.reset();
	}

	public void playGame(Tournament tour, Player p1, Player p2, Scanner scn) {
		if (tour.getType().name().equalsIgnoreCase("tennis")) {
			tennisGame(p1, p2, scn);
		} else if (tour.getType().name().equalsIgnoreCase("basketball")) {
			basketballGame(p1, p2, scn);
		} else {
			soccerGame(p1, p2, scn);
		}

	}

	public void tennisGame(Player p1, Player p2, Scanner scanTennis) {

		int p1Sets = 0;
		int p2Sets = 0;
		int p1Score, p2Score;
		for (int i = 1; i <= 5; i++) {
			System.out.println("Enter the scores of the #" + i + " set for each player/team");
			do {
				p1Score = enterPlayerScore(p1, "scores", "# " + i, "set", 0, 10, scanTennis);
				p2Score = enterPlayerScore(p2, "scores", "# " + i, "set", 0, 10, scanTennis);

				if (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
						|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
						|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12)
					System.out.println("not valid scores , try again");
			} while (p1Score > 7 || p2Score > 7 || p1Score < 0 || p2Score < 0
					|| (Math.abs(p1Score - p2Score) < 2 && (p1Score + p2Score) < 13)
					|| (p1Score != 6 && p2Score != 6) && (p1Score + p2Score) < 12);
			if (p1Score > p2Score)
				p1Sets++;
			else
				p2Sets++;
			if (p1Sets == 3) {
				announceWinner(p1);
				i = 6; // break;
			} else if (p2Sets == 3) {
				announceWinner(p2);
				i = 6; // break;
			}
		}

	}

	public void basketballGame(Player p1, Player p2, Scanner scanBasketball) {
		int sum1 = 0;
		int sum2 = 0;
		int score1, score2;

		for (int i = 1; i <= 4; i++) {
			do {
				score1 = enterPlayerScore(p1, "points", "# " + i, "quarter", 0, 60, scanBasketball);
				score2 = enterPlayerScore(p2, "points", "# " + i, "quarter", 0, 60, scanBasketball);
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
		}
		if (sum1 > sum2) {
			announceWinner(p1);
		} else if (sum2 > sum1) {
			announceWinner(p2);
		} else {
			int extraTimeCounter = 0;
			System.out.println(p1.getName() + " and " + p2.getName()
					+ " scores are equal , we are going to extra time until scores differ!");
			while (sum1 == sum2) {
				do {
					extraTimeCounter++;

					score1 = enterPlayerScore(p1, "points", "# " + extraTimeCounter, "Extra time half", 0, 60,
							scanBasketball);

					score2 = enterPlayerScore(p2, "points", "# " + extraTimeCounter, "Extra time half", 0, 60,
							scanBasketball);
				} while (score1 < 0 || score2 < 0);
				sum1 += score1;
				sum2 += score2;
			}
			if (sum1 > sum2) {
				announceWinner(p1);
			} else {
				announceWinner(p2);
			}
		}
	}

	private static void announceWinner(Player winner) {
		System.out.println(winner.getName() + " is the Winner ");
		winner.uppWins();
	}

	private static int enterPlayerScore(Player p, String scoreName, String roundCount, String gameSequence,
			int minResult, int maxResult, Scanner scn) {
		if (minResult < 0)
			minResult = 0;
		System.out.println("Please enter how many " + scoreName + " " + p.getName() + " scored in the " + roundCount
				+ " " + gameSequence);
		String userInput = "";
		int result = -1;
		boolean validResult = false;
		while (validResult == false) {
			userInput = scn.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch (Exception e) {
				System.err.println("haha, no exception thrown");
			}
			if (result >= minResult && result <= maxResult) {
				validResult = true;
				break;
			}
			System.out.println("please insert valid result between: " + minResult + " -> " + maxResult);
		}
		return result;
	}

	public void soccerGame(Player p1, Player p2, Scanner scanSoccer) {
		int sum1 = 0;
		int sum2 = 0;
		int score1, score2;

		for (int i = 1; i <= 2; i++) {
			do {
//				System.out.println("Please enter how many goals " + p1.getName() + " scored in the #" + i + " half");
//				score1 = scanSoccer.nextInt();
				score1 = enterPlayerScore(p1, "goals", "# " + i, "half", 0, 10, scanSoccer);
//				System.out.println("Please enter how many goals " + p2.getName() + " scored in the #" + i + " half");
//				score2 = scanSoccer.nextInt();
				score2 = enterPlayerScore(p2, "goals", "# " + i, "half", 0, 10, scanSoccer);

				if (score1 < 0 || score2 < 0)
					System.out.println("there is an issue with the scores you entered , try again!");
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
		}
		if (sum1 > sum2) {
			announceWinner(p1);
		} else if (sum2 > sum1) {
			announceWinner(p2);
		} else { // tie
			System.out.println(p1.getName() + " and " + p2.getName()
					+ " scores are equal , we are going to extra time , if scores stay equal, we'll move to penalty kicks ");
			do {
				score1 = enterPlayerScore(p1, "goals", "", "extra time", 0, 10, scanSoccer);
				score2 = enterPlayerScore(p2, "goals", "", "extra time", 0, 10, scanSoccer);

				if (score1 < 0 || score2 < 0)
					System.out.println("there is an issue with the scores you entered , try again");
			} while (score1 < 0 || score2 < 0);
			sum1 += score1;
			sum2 += score2;
			if (sum1 > sum2) {
				announceWinner(p1);
			} else if (sum2 > sum1) {
				announceWinner(p2);
			} else {
				System.out.println("Score is " + sum1 + "-" + sum2);
				System.out.println(" There's still a tie between " + p1.getName() + " and " + p2.getName());
				System.out.println("Moving to penalty shots , good luck to the goalkeepers!");
				sum1 = 0;
				sum2 = 0;
				String ans;
				for (int i = 0; i < 5; i++) {
					System.out.println("#" + (i + 1) + " shot for " + p1.getName()
							+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
					ans = scanSoccer.nextLine();
					if (i == 0)
						ans = scanSoccer.nextLine();
					if (ans.equalsIgnoreCase("yes"))
						sum1++;
					if ((sum2 + (5 - (i + 1))) < sum1) {
						announceWinner(p1);
						i = 5;
					} else {
						System.out.println("#" + (i + 1) + " shot for " + p2.getName()
								+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
						ans = scanSoccer.nextLine();

						if (ans.equalsIgnoreCase("yes"))
							sum2++;
					}
					if ((sum1 + (5 - (i + 1))) < sum2) {
						announceWinner(p2);
						i = 5;
					}

				}
				if (p1.getWinsCounter() == p2.getWinsCounter() && sum1 > sum2) {
					announceWinner(p1);
				} else if (p1.getWinsCounter() == p2.getWinsCounter() & sum2 > sum1) {
					announceWinner(p2);
				} else if (p1.getWinsCounter() == p2.getWinsCounter()) {
					int extraKickCounter = 0;
					System.out.println(" penalty shots are tied ! \n extra 1 shot each time until score differs");
					while (sum1 == sum2) {
						extraKickCounter++;
						System.out.println("#" + extraKickCounter + "  extra penalty shot for " + p1.getName()
								+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
						ans = scanSoccer.nextLine();
						if (ans.equalsIgnoreCase("yes"))
							sum1++;
						System.out.println("#" + extraKickCounter + " extra penalty shot for " + p2.getName()
								+ "\n is it goal ? (enter 'yes' for a goal and 'no' for a miss)");
						ans = scanSoccer.nextLine();
						if (ans.equalsIgnoreCase("yes"))
							sum2++;
					}
					if (p1.getWinsCounter() == p2.getWinsCounter() && sum1 > sum2) {
						announceWinner(p1);
					} else if (p1.getWinsCounter() == p2.getWinsCounter()) {
						announceWinner(p2);
					}
				}

			}

		}
	}
	public void checkScoreBoard(int gameStage,ArrayList <Integer> p1Score
			,ArrayList <Integer> p2Score,int gameId) {
		String result = tour.checkBoard(gameStage, p1Score, p2Score,gameId);
		tour.resetSums();
		
		String[] splitCase = result.split("@",2);
		if(splitCase[0].contains("ERROR")) {
			fireModelAlertScoresNotValid(gameId,splitCase[0] ,splitCase[1]); 
		}
		else if(splitCase[0].contains("TIE")) {
			fireModelAnnounceRoundResults(gameId,"TIE in round #" + gameId ,splitCase[1]); 
		}
		else if(splitCase[1].contains("Won")) {
			fireModelAnnounceRoundResults(gameId,splitCase[0] ,splitCase[1]); 
			System.out.println("return winner");
		}
		else 
			System.out.println("Error at checkScoreBoard, check me in Model 378");
		
	}
	

	

	@Override
	public String toString() {
		return super.toString();
	}

	public void addPlayer(String name) {
		Player addMe;
		try {
		addMe = new Player(name);
		}catch (Exception e)
		{
			fireModelUpdateNameInvalid(e.getMessage());
			return; // terminate
		} 
		boolean beenAdded =  tour.addParticipant(addMe);		
		if (beenAdded) 
			fireModelAddedName(name);
		else {
			fireModelUpdateNameAlreadyExists(name);
		}
	}
	/*		NO NEED TO CHECK, SINCE WE HIDE "add" BUTTON WHEN FULL- SO ONLY OPTION LEFT IS : NAME ALREADY EXISTS
			if (isNameInTourAlready(name)) { // maybe delete, and simply hide the Add button 
				fireModelUpdateNameAlreadyExists(name);
			}
			else {
				fireModelUpdateNoRoom();
			}
		

		}
	}
	
	private boolean isNameInTourAlready(String name) {
		for (Player p : tour.getTeams().getList())
		{
			if(p.getName().contains(name)) { // ignoreEq didn't work
				return true;
			}
		}
		return false;
	}
	*/

	private void fireModelUpdateNameInvalid(String message) {
		for(ChampionshipListenable cl : allListeners) {	
			cl.modelUpdateNameInvalid(message);		
		}
	}

	private void fireModelAddedName(String name) {
		for(ChampionshipListenable cl : allListeners) {
			cl.modelAddedName(name);
		}
	}
	
	 protected void fireModelUpdateNameAlreadyExists(String name) {
		 for(ChampionshipListenable cl : allListeners) {
			 cl.modelUpdatePlayerAlreadyAdded(name);
		 }
		}

	public void sendPlayerNameNRounds(int gameId,int gameState) {
		String[] playersPlaying = getPlayersInGivenRound(gameId);
		int numOfRounds = tour.getNumberOfRounds(gameState);
		fireModelSendRoundFormat(numOfRounds,playersPlaying[0],playersPlaying[1],gameState,gameId);
	}
	
	private String[] getPlayersInGivenRound(int gameNumber) {
		String player1,player2;
		player1 = tour.getTeams().get(2*gameNumber).getName();
		player2 = tour.getTeams().get((2*gameNumber)+1).getName();
		String[] playersPlaying = {player1,player2};
		return playersPlaying;
	}

	private void fireModelSendRoundFormat(int numOfRounds, String player1, String player2,int gameState,int gameId) {
		 for(ChampionshipListenable cl : allListeners) {
			 System.out.println("in model fireModelSendRoundFormat() game stats: " + gameState);
			 cl.modelSendGameFormat(numOfRounds,player1,player2,gameState,gameId);
		 }
	}
	private void fireModelAlertScoresNotValid(int gameId,String headLine, String message ) {
		 for(ChampionshipListenable cl : allListeners) {
			 cl.modelAlertScoresNotValid(gameId,headLine,message);
		 }
	}
	
	private void fireModelAnnounceRoundResults(int gameId, String headLine, String message) {
		 for(ChampionshipListenable cl : allListeners) {
			 cl.modelAnnounceRoundResults(gameId,headLine,message);
		 }
	}

	// cnstrcrr - > 1) game type -> 2)
	
	public void registerListener(ChampionshipListenable l) {
		allListeners.add(l);
	}

	public void filterLosersFromTeams() {
		tour.filteringLostPlayers();
	}

	//announce 

	// gameId - Define game in current round (8 players - 4 games -> gameNumber [0,3])
	// GAMESTAGE / gameState:
			//	Football: [0,1] are reg halfs , [2,3] are extra time halfs , [4-8] are penalties, 8+ are 1shot penalties..
			// 	Basketball: [0,1,2,3] are reg quarters , 4+ are extra times
			//	Tennis: [0-4] all regular rounds.
	
}
