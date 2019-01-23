import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculator {
	
	private static String getUserInput(String message) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		String input = scanner.nextLine();
		System.out.println("OK");
		return input;
	}
	
	public static void use() {
		System.out.println("Welcome to calculator! (enter 'q' to quit)");
		String userInput = "";
		do {
			userInput = getUserInput("How many numbers would you like to calculate (3 or 4).");
			if (userInput.equals("3")) {
				List<Integer> numbersGiven = getNumbersFromUserInput(3);
				System.out.println(Calculations.getArithmeticRepresentations(
						numbersGiven.get(0), 
						numbersGiven.get(1), 
						numbersGiven.get(2)));
				
			}
			else if (userInput.equals("4")) {
				List<Integer> numbersGiven = getNumbersFromUserInput(4);
				System.out.println(Calculations.getArithmeticRepresentations(
						numbersGiven.get(0), 
						numbersGiven.get(1), 
						numbersGiven.get(2), 
						numbersGiven.get(3)));
			}
		} while(!userInput.equals("q"));
		System.out.println("Thank you for using our calculator!");
	}
	
	private static List<Integer> getNumbersFromUserInput(int count) {
		List<Integer> numbers = new ArrayList<>();
		int numbersTaken = 0;
		while(numbersTaken != count) {
			String input = getUserInput("Enter number " + (numbersTaken + 1) + ": ");
			if (Pattern.matches("[0-9]+", input)) {
				numbers.add(Integer.parseInt(input));
				numbersTaken++;
			}
		}
		return numbers;
	}
}
