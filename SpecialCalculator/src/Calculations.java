import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Operator {
	int operator(int first, int second);
}

public class Calculations {
	
	public static List<String> getArithmeticRepresentations(int first, int second, int third) {
		List<Integer> inputList = Arrays.asList(
			new Integer[] {first, second, third}
		);
		List<String> representations = new ArrayList<>();
		List<List<Integer>> permutations = Permutator.getPermutations(inputList);
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
		return o.operator(first, second) == third;
	}
	
	private static String operatorRepresentation(int first, int second, int third, String operator) {
		return first + " " + operator + " " + second + " = " + third;
	}
	
	private static int add(int first, int second) {
		return first + second;
	}
	
	private static int subtract(int first, int second) {
		return first - second;
	}
	
	private static int multiply(int first, int second) {
		return first * second;
	}
	
	private static int divide(int first, int second) {
		return first / second;
	}
	
}
