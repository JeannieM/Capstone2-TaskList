package taskList;

import java.time.LocalDate;
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
			userAction = Validator.getInt(scnr, "What would you like to do?", 1, 9);

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
							"[qQ\\d]+[uitUIT]*", "Please enter the number of a list item, or the letter \"Q\":");
					// if user entered Q:
					if (taskNum.toLowerCase().startsWith("q")) {
						break;
						// if user did not enter Q:
					} else if (!taskNum.toLowerCase().startsWith("q")) {
						// catch input matching regex but out of bounds
						// (with custom Exception message thrown by method)
						try {
							// Since I offered the option of numbers or a letter I must
							// parse the int from the String:
							tlm.removeTask(scnr, Integer.parseInt(taskNum));
							break;
						}
						//FIXME I cannot figure out why the app is printing the prompt from the Validator at the 
						//top of the next loop before it is printing the exception message below...
						//BUT it happens only in the first iteration
						catch (Exception e) {
							System.err.println(e.getMessage());// This exception says "Please try again"
						}
					}
				} while (true);

				break;
			case 4: // Mark task complete
				System.out.println("MARK TASK COMPLETE");
				do {
					taskNum = Validator.getStringMatchingRegexCustomError(scnr,
							"Enter the number of the task you would like to mark complete, or \"Q\" to go back:",
							"[\\d+Qq]", "Please enter the number of a list item, or the letter \"Q\":");
					// if user did not enter Q:
					if (!taskNum.toLowerCase().startsWith("q")) {
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
					} else if (taskNum.toLowerCase().startsWith("q")) {
						break;
					}
				} while (tryAgain);
			default:
				break;

			case 5://Display Tasks due before a certain date
				System.out.println("SHOW TASKS DUE ON OR BEFORE X DATE");
				tlm.displayTasksDueBeforeDate(Validator.getDate(scnr, "Please enter the date in question (YYYY-MM-DD):"));
				
			case 6: //Display Tasks assigned to certain Team member
				System.out.println("SHOW TASKS FOR X TEAM MEMBER");
				tlm.displayTasksByTeamMember(scnr, "Please enter name of Team Member");
				
			case 7://Edit a task
				System.out.println("EDIT EXISTING TASK");
				tlm.editTask(scnr, "Please enter number of task you wish to edit:");
				
			case 8://Sort list
				System.out.println("SORT LIST BY DUE DATE");
				tlm.sortList();
			}

		} while (userAction != 9);
		System.out.println("Thank you, Goodbye!");
	}
}
