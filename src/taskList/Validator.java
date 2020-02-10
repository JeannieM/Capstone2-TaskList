package taskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Validator {

	/**
	 * Get any valid integer.WITH PROMPT
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		System.out.print(prompt);
		while (!scnr.hasNextInt()) {
			scnr.nextLine(); // clear bad line.
			System.err.println("Sorry, I can't read that. Enter a whole number, using digits.");
			System.out.println(prompt);
		}
		int result = scnr.nextInt();
		scnr.nextLine(); // clear for next line of input.
		return result;
	}
	/**
	 * Get any valid integer.WITHOUT PROMPT
	 */
	public static int getInt(Scanner scnr) {
		// This approach use "hasNext" look ahead.
		
		while (!scnr.hasNextInt()) {
			scnr.nextLine(); // clear bad line.
			System.err.println("Sorry, I can't read that. Enter a whole number, using digits.");
			
		}
		int result = scnr.nextInt();
		scnr.nextLine(); // clear for next line of input.
		return result;
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scnr.hasNextDouble();
			if (!isValid) {
				scnr.nextLine(); // clear bad line.
				System.err.println("Sorry, I can't read that. Enter a number, using digits.");
			}
		} while (!isValid);

		double number = scnr.nextDouble();
		scnr.nextLine(); // clear for next line of input.
		return number;
	}

	/**
	 * Get any string.
	 */
	// FIXME
	public static String getString(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.println(prompt);
		return scnr.nextLine();
	}

	/**
	 * Get any valid integer between min and max.
	 * EDITED TO USE getInt scnr, no prompt, so prompt will not print twice
	 */
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		System.out.println(prompt);
		do {
			number = getInt(scnr);

			if (number < min) {
				isValid = false;
				System.err.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.err.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scnr, prompt);

			if (number < min) {
				isValid = false;
				System.err.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.err.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		System.out.println(prompt);
		do {
			input = scnr.nextLine();

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.err.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	/**
	 * Get a string of input from the user that must match the given regex AND
	 * prints a custom error message.
	 */
	public static String getStringMatchingRegexCustomError(Scanner scnr, String prompt, String regex,
			String errorMessage) {
		boolean isValid = false;
		String input;
		System.out.println(prompt);
		
		do {
			input = scnr.nextLine();

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println(errorMessage);
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

	/**
	 * Get a yes or no from the user.
	 */
	public static String yesOrNo(Scanner scnr, String prompt) {

		String input;
		input = getString(scnr, prompt);
		do {
			if (input.toLowerCase().startsWith("y")) {
				return "yes";
			} else if (input.toLowerCase().startsWith("n")) {
				return "no";
			} else {
				System.err.println("Please enter either yes or no: ");
				input = scnr.nextLine();
				// scnr.nextLine();//clear?
			}
		} while (true);// use this to always loop unless the loop has been escaped!
		// If use variable boolean instead, method isn't sure that the variable will
		// always be true
		// and method will require a return value outside the loop
		// return "Error reading user input. No yes or no detected.";
	}

	/**
	 * Return a true or a false based on the response for the above Validator
	 */
	public static boolean goAgain(Scanner scnr, String prompt) {
		if (yesOrNo(scnr, prompt).equalsIgnoreCase("yes")) {
			return true;
		} else {
			return false;
		}
	}
	// add more methods here to validate other needs as they come up

	public static LocalDate getDate(Scanner scnr, String prompt) {
		String input;
		System.out.println(prompt);
		do {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
				input = scnr.nextLine();
				if (input.equalsIgnoreCase("today")) {
					return LocalDate.now();
				}
				LocalDate date = LocalDate.parse(input, formatter);
				return date;
			} catch (DateTimeParseException e) {
				System.err.println("Please enter a valid date (YYYY-MM-DD):");
			}
		} while (true);
	}
}