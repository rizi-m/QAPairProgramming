import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AI {
	
	public static Move getMove(int difficulty, Move playerInput) {
		Random rand = new Random();
		float random = rand.nextFloat();
		switch (difficulty) {
			case 2: return moveWithPreference(0.6f, playerInput.losesTo());
			case 3: return moveWithPreference(0.7f, playerInput.losesTo());
			default: return randomMoveFrom(Move.values());
		}
	}
	
	private static Move randomMoveFrom(Move[] moves) {
		Random rand = new Random();
		int randomPosition = rand.nextInt(moves.length);
		return moves[randomPosition];
	}
	
	private static Move moveWithPreference(float probabilty, List<Move> preferences) {
		Random rand = new Random();
		if (rand.nextFloat() < probabilty) {
			return preferences.get(0);
		}
		List<Move> movesWithoutPreference = Arrays.stream(Move.values())
				.filter(move -> !Arrays.asList(preferences).contains(move))
				.collect(Collectors.toList());

		return randomMoveFrom(movesWithoutPreference
				.toArray(new Move[movesWithoutPreference.size()]));
	}
}
