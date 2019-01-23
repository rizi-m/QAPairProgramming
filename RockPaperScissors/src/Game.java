import java.awt.Color;
import java.util.List;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
	
	private int totalGamesPlayed = 0;
	private int computerWins = 0;
	private int playerWins = 0;
	private int ties = 0;
	
	List<Move> history = new ArrayList<>();
	
	public void play() {
		String input = "";
		
		while(!input.equals("quit")) {
			input = getUserInput();
			if (checkForMove(input)) {
				totalGamesPlayed ++;
				Move playerMove = Move.getMoveFromString(input);
				Move aiMove = AI.getMove(1, playerMove);
				history.add(playerMove);
				history.add(aiMove);
				int moveResult = compareMoves(playerMove, aiMove);
				if (moveResult == 1) {
					System.out.println("Player wins!");
					playerWins++;
				} else if (moveResult == -1) {
					System.out.println("Computer wins!");
					computerWins++;
				} else {
					System.out.println("DRAW");
					ties++;
				}
			}
		}
		if (totalGamesPlayed > 0) {
			displayResults();
		}

		System.out.println("Thanks for playing!");
	}
	
	private boolean checkForMove(String input) {
		return Arrays.
				stream(Move.values())
				.anyMatch(move -> input.contains(move.toString()));
	}
	
	private String getUserInput() {
		System.out.println("Please enter a move ('quit' to exit): ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	private int compareMoves(Move firstMove, Move secondMove) {
		if(firstMove.getBeats().contains(secondMove)) {
			return 1;
		}
		if(secondMove.getBeats().contains(firstMove)) {
			return -1;
		}
		return 0;
	}
	
	private List<Move> getMostPicked() {
		List<List<Move>> splitHistory = new ArrayList<>();
		for(Move currentMove : Move.values()) {
			splitHistory.add(
					history.stream()
						.filter(move -> move.equals(currentMove))
						.collect(Collectors.toList())
				);
		}
		return splitHistory.stream().max(Comparator.comparing(List::size)).get();
	}
	
	private void displayResults() {
		System.out.println("\n–––––––––––––––––––––––––––––––––––");
		System.out.println("Total Games played: " + totalGamesPlayed);
		System.out.println("Your wins " + playerWins);
		System.out.println("CPU wins : " + computerWins);
		System.out.println("Ties: " + ties);
		List<Move> mostPicked = getMostPicked();
		Move mostPlayed = mostPicked.get(0);
		System.out.println("Most picked move: " + mostPlayed + ". Played " + mostPicked.size() + " time which is " + (((float)mostPicked.size()/(float)history.size()) * 100) + "%.");
		
	}
	
}

enum Move {
	ROCK("rock", 
			new String[] {"scissors"}, 	//beats
			new String[] {"paper"}), 	//loses to
	PAPER("paper", 
			new String[] {"rock"}, 
			new String[] {"scissors"}),
	SCISSORS("scissors", 
			new String[] {"paper"},
			new String[] {"rock"});
	
	private String move;
	private String[] beats;
	private String[] loses;
	
	Move(String move, String[] beats, String[] loses) {
		this.move = move;
		this.beats = beats;
		this.loses = loses;
	}
	
	public List<Move> losesTo() {
		return Arrays.stream(loses)
				.map(Move::getMoveFromString)
				.collect(Collectors.toList());
	}
	
	public List<Move> getBeats() {
		return Arrays.stream(beats)
				.map(Move::getMoveFromString)
				.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return move;
	}
	
	public static Move getMoveFromString(String input) {
		for(Move move : Move.values()) {
			if (input.contains(move.toString())) {
				return move;
			}
		}
		throw new InvalidParameterException("Move from user is not valid.");
	}
	
	public boolean equals(Move o) {
		return move.equals(o.toString());
	}
}