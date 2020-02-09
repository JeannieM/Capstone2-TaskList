package taskList;

/**
 * @author JeannieMcCarthy
 *
 */
import java.util.*;

public class TaskListApp {

	public static void main(String[] args) {

		// variables
		Scanner scnr = new Scanner(System.in);
		TaskListManager tlm = new TaskListManager();
		int userAction;
		String taskNum;
		boolean tryAgain = false;

		// Welcome user
		System.out.println("Hello! Let's Manage some Tasks, shall we?\n");

		// enter the loop
		do {
			// display menu
			tlm.displayMenu();

			// Take chosen option from user
			userAction = Validator.getInt(scnr, "What would you like to do?", 1, 5);

			// determine action
			switch (userAction) {

			case 1:// Display
				tlm.displayTaskList();
				break;

			case 2:// Add Task, then display list
				tlm.addTask(scnr);
				break;

			case 3:// remove task
				System.out.println("REMOVE TASK");

				do {
					// Validate that the input is either a number or the letter Q
					taskNum = Validator.getStringMatchingRegexCustomError(scnr,
							"Enter the number of the task you would like to remove, " + "or \"Q\" to go back:",
							"[\\d+Qq]", "Please enter the number of a list item, or the letter \"Q\":");
					// if user entered Q:
					if (taskNum.equalsIgnoreCase("Q")) {
						break;
						// if user did not enter Q:
					} else if (!taskNum.equalsIgnoreCase("Q")) {
						// catch input matching regex but out of bounds
						// (with custom Exception message thrown by method)
						try {
							// Since I offered the option of numbers or a letter I must
							// parse the int from the String:
							tlm.removeTask(Integer.parseInt(taskNum));
							tryAgain = false;
						} catch (Exception e) {
							System.err.println(e.getMessage());// This exception says "Please try again"
							tryAgain = true;
						}
					}
				} while (tryAgain);

				break;
			case 4: // Mark task complete
				System.out.println("MARK TASK COMPLETE");
				do {
					taskNum = Validator.getStringMatchingRegexCustomError(scnr,
							"Enter the number of the task you would like to mark complete, or \"Q\" to go back:",
							"[\\d+Qq]", "Please enter the number of a list item, or the letter \"Q\":");
					// if user did not enter Q:
					if (!taskNum.equalsIgnoreCase("Q")) {
						// catch input matching regex but out of bounds (with custom Exception message
						// thrown by method)
						try {
							// Since I offered the option of numbers or a letter I must parse the int from
							// the String:
							tlm.completeTask(Integer.parseInt(taskNum));
							tryAgain = false;
						} catch (Exception e) {
							System.err.println(e.getMessage());
							tryAgain = true;
						}
						// if user did enter Q:
					} else if (taskNum.equalsIgnoreCase("Q")) {
						break;
					}
				} while (tryAgain);
			default:
				break;

			}

		} while (userAction != 5);
		System.out.println("Thank you, Goodbye!");
	}
}
