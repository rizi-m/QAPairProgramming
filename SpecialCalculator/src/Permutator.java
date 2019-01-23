import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutator {
	
	public static List<List<Integer>> getPermutations(List<Integer> numbers) {
		if (numbers.size() == 3) {
			return permuteListOf3(numbers);
		}
		throw new InvalidParameterException("Numbers list was too short");
	}
	
	private static List<List<Integer>> permuteListOf3(List<Integer> numbers) {
		if (numbers.size() != 3) {
			throw new InvalidParameterException("Numbers list was too short");
		}
		List<List<Integer>> result = new ArrayList<>();
		for(int i = 0; i < 3; ++i) {
			Integer first = numbers.get(i % 3);
			Integer second = numbers.get((i + 1) % 3);
			Integer third = numbers.get((i + 2) % 3);
			List<Integer> firstPermutation = Arrays.asList(
				new Integer[] {first, second, third}
			);
			List<Integer> secondPermutation = Arrays.asList(
				new Integer[] {first, third, second}
			);
			result.add(firstPermutation);
			result.add(secondPermutation);
		}
		return result;
	}
	
}
