import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Operator {
	float binaryOperator(float first, float second);
}

public class Calculations {
	
	public static List<String> getArithmeticRepresentations(int first, int second, int third) {
		List<Integer> inputList = Arrays.asList(
			new Integer[] {first, second, third}
		);
		List<List<Integer>> permutations = Permutator.getPermutations(inputList);
		return getRepresentationsFromPermutations(permutations);
	}
	
	public static List<String> getArithmeticRepresentations(int first, int second, int third, int fourth) {
		List<Integer> inputList = Arrays.asList(
				new Integer[] {first, second, third, fourth}
			);
		List<List<Integer>> permutations = Permutator.getPermutations(inputList);
		return getRepresentationsFromPermutations(permutations);
	}
	
	private static List<String> getRepresentationsFromPermutations(List<List<Integer>> permutations) {
		List<String> representations = new ArrayList<>();
		for(List<Integer> numbers : permutations) {
			int firstNumber = numbers.get(0);
			int secondNumber = numbers.get(1);
			int thirdNumber = numbers.get(2);
			if(numberOperates(firstNumber, secondNumber, thirdNumber, Calculations::add)) {
				representations.add(operatorRepresentation(firstNumber, secondNumber, thirdNumber, "+")); 
			}
			if(numberOperates(firstNumber, secondNumber, thirdNumber, Calculations::subtract)) {
				representations.add(operatorRepresentation(firstNumber, secondNumber, thirdNumber, "-")); 
			}
			if(numberOperates(firstNumber, secondNumber, thirdNumber, Calculations::multiply)) {
				representations.add(operatorRepresentation(firstNumber, secondNumber, thirdNumber, "*"));
			}
			if(numberOperates(firstNumber, secondNumber, thirdNumber, Calculations::divide)) {
				representations.add(operatorRepresentation(firstNumber, secondNumber, thirdNumber, "/"));
			}
		}
		return representations;
	}
	
	private static boolean numberOperates(int first, int second, int third, Operator o) {
		return o.binaryOperator(first, second) == third;
	}
	
	private static String operatorRepresentation(int first, int second, int third, String operator) {
		return first + " " + operator + " " + second + " = " + third;
	}
	
	private static float add(float first, float second) {
		return first + second;
	}
	
	private static float subtract(float first, float second) {
		return first - second;
	}
	
	private static float multiply(float first, float second) {
		return first * second;
	}
	
	private static float divide(float first, float second) {
		return first / second;
	}
	
}
