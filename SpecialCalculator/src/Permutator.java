import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutator {
	
	public static List<List<Integer>> getPermutations(List<Integer> numbers) {
		if (numbers.size() == 3) {
			return permuteListOf3(numbers);
		}
		if (numbers.size() == 4) {
			return permuteListOf4(numbers);
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
	
	private static List<List<Integer>> permuteListOf4(List<Integer> numbers) {
		if (numbers.size() != 4) {
			throw new InvalidParameterException("Numbers list was too short");
		}
		int first = numbers.get(0);
		int second = numbers.get(1);
		int third = numbers.get(2);
		int fourth = numbers.get(3);
		
		List<Integer> firstList = Arrays.asList(
			new Integer[] {first, second, third}
		);
		
		List<Integer> secondList = Arrays.asList(
				new Integer[] {first, second, fourth}
		);
		
		List<Integer> thirdList = Arrays.asList(
				new Integer[] {first, third, fourth}
		);
		
		List<Integer> fourthList = 	Arrays.asList(
				new Integer[] {second, third, fourth}
		);
		
		List<List<Integer>> firstPermutations = permuteListOf3(firstList);
		List<List<Integer>> secondPermutations = permuteListOf3(secondList);
		List<List<Integer>> thirdPermutations = permuteListOf3(thirdList);
		List<List<Integer>> fourthPermutations = permuteListOf3(fourthList);
		
		List<List<Integer>> result = new ArrayList<>();
		result.addAll(firstPermutations);
		result.addAll(secondPermutations);
		result.addAll(thirdPermutations);
		result.addAll(fourthPermutations);
		
		return result;
	}
	
	
	
}
