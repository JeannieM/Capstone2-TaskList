package taskList;

import java.time.LocalDate;
import java.util.*;

public class TaskListManager {
	// variables
	boolean goAgain = false;
	public List<Task> taskList = new ArrayList<Task>(
			// I initiated my list with a few tasks for easier testing
			Arrays.asList((new Task("Kiera", "Wash Dishes", LocalDate.parse("2020-02-21"))),
					(new Task("Bonnie", "Do Laundry", LocalDate.parse("2020-02-20"))),
					(new Task("Kiera", "Rake Leaves", LocalDate.parse("2020-02-11"))),
					(new Task("Bonnie", "Clean Gutters", LocalDate.parse("2020-02-18"))),
					(new Task("Haley", "Mop Floors", LocalDate.parse("2020-02-17"))),
					(new Task("Haley", "Dust", LocalDate.parse("2020-02-15")))));
	//sort the list (by due date)
	
	// display the menu of options to the user
	public void displayMenu() {
		System.out.printf("     1. | Display Task List\n" + "     2. | Add Task\n" + "     3. | Remove Task\n"
				+ "     4. | Mark Task Complete\n" + "     5. | Display Tasks Due before a certain date\n"
				+ "     6. | Display Tasks Assigned to a certain Team Member\n" + "     7. | Edit an existing Task\n"
				+ "     8. | Sort Tasks by Due Date\n"
				+ "     9. | Quit\n\n");
	}

	// add a task to the task list
	public void addTask(Scanner scnr) {
		String teamMember;
		String description;
		LocalDate dueDate;

		System.out.println("ADD TASK");
		teamMember = Validator.getString(scnr, "Team Member: ");
		description = Validator.getString(scnr, "Task Description: ");
		dueDate = Validator.getDate(scnr, "Enter a due date (YYYY-MM-DD): ");
		taskList.add(new Task(teamMember, description, dueDate));
		System.out.println("Success! Task has been added:");
		displayTaskList();
	}

	public void displayTaskList() {
		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
		} else {
			// heading row:
			System.out.println(" -----------------------------------------------------------------------------------");
			System.out.printf("| %-4s| %-15s| %-25s| %-15s| %-15s|\n", "#", "Team Member", "Description", "Due Date",
					"Status");
			System.out.println("|-----------------------------------------------------------------------------------|");
			// loop through array for rest of rows
			int i = 1;
			for (Task task : taskList) {
				System.out.printf("| %-4d| %-15s| %-25s| %-15s| %-15s|\n", i, task.getTeamMember(),
						task.getDescription(), String.valueOf(task.getDueDate()), task.getCompletion());
				// I want the last row to look different:
				if (i < taskList.size()) {
					System.out.println(
							"|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
				} else {
					System.out.println(
							" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
				}
				i++;
			}
		}
	}

	public void removeTask(Scanner scnr, int taskNum) throws Exception {
		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
		}
		if (taskNum <= taskList.size() && taskNum > 0) {
			if (Validator.yesOrNo(scnr, "Are you sure?").equals("yes")) {
				taskList.remove(taskNum - 1);
				System.out.println("Success! Task has been removed:");
				displayTaskList();
			}
		} else {
			throw new Exception("You entered a number outside the range of tasks. Please try again.");
		}
	}

	public void completeTask(int taskNum) throws Exception {
		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
		}
		if (taskNum <= taskList.size() && taskNum > 0) {
			Task thisTask = taskList.get(taskNum - 1);
			thisTask.setCompletion("Complete");
			System.out.println("Success! Task has been marked complete!");
			displayTaskList();
		} else {
			throw new Exception("You entered a number outside the range of tasks. Please try again.");
		}
	}

	public void displayTasksDueBeforeDate(LocalDate userDate) {
		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
		} else {
			// heading row:
			System.out.println(" -----------------------------------------------------------------------------------");
			System.out.printf("| %-4s| %-15s| %-25s| %-15s| %-15s|\n", "#", "Team Member", "Description", "Due Date",
					"Status");
			System.out.println("|-----------------------------------------------------------------------------------|");
			// loop through array for rest of rows
			int i = 1;
			for (Task task : taskList) {
				if (task.getDueDate().compareTo(userDate) <= 0) {
					System.out.printf("| %-4d| %-15s| %-25s| %-15s| %-15s|\n", i, task.getTeamMember(),
							task.getDescription(), String.valueOf(task.getDueDate()), task.getCompletion());
					System.out.println(
							"|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
					i++;
				} else {
					continue;
				}
			}
			if (i == 1) {
				System.out.println(
						"|  There are no tasks due on or before that date. Time to party!                    |");
				System.out.println(
						"|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
			}
			System.out.println();
		}
	}

	public void displayTasksByTeamMember(Scanner scnr, String prompt) {

		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
		} else {
			System.out.println(prompt);
			String teamMember = scnr.nextLine();
			// heading row:
			System.out.println(" -----------------------------------------------------------------------------------");
			System.out.printf("| %-4s| %-15s| %-25s| %-15s| %-15s|\n", "#", "Team Member", "Description", "Due Date",
					"Status");
			System.out.println("|-----------------------------------------------------------------------------------|");
			// loop through array for rest of rows
			int i = 1;
			for (Task task : taskList) {
				if (task.getTeamMember().equalsIgnoreCase(teamMember)) {
					System.out.printf("| %-4d| %-15s| %-25s| %-15s| %-15s|\n", i, task.getTeamMember(),
							task.getDescription(), String.valueOf(task.getDueDate()), task.getCompletion());
					System.out.println(
							"|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
					i++;
				} else {
					continue;
				}
			}
			if (i == 1) {
				System.out.println(
						"|  There are no tasks assigned to that team member. They got off easy that time!    |");
				System.out.println(
						"|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
			}
			System.out.println();
		}
	}

	public void editTask(Scanner scnr, String prompt) {
		if (taskList.isEmpty()) {
			System.err.println("There are no tasks! Please add a task.");
			return;
		}
		// ask user which task to edit
		int taskNum = Validator.getInt(scnr, prompt, 1, taskList.size());

		// get task and print it out
		Task task = taskList.get(taskNum - 1);
		System.out.printf("| %-15s| %-25s| %-15s| %-15s|\n", task.getTeamMember(), task.getDescription(),
				String.valueOf(task.getDueDate()), task.getCompletion());
		// give user numerical options for editing
		System.out.println("What do you want to edit about this task?\n");
		do {
			System.out.printf("     1. | Team Member\n" + "     2. | Description\n" + "     3. | Due Date\n"
					+ "     4. | Completion\n\n");

			int userAction = Validator.getInt(scnr);

			switch (userAction) {

			case 1:
				System.out.println("Enter new Team Member name:\n");
				task.setTeamMember(scnr.nextLine());
				break;
			case 2:
				System.out.println("Enter new Description:\n");
				task.setDescription(scnr.nextLine());
				break;
			case 3:
				task.setDueDate(Validator.getDate(scnr, "Enter new Due Date:\n"));
				break;
			case 4:
				System.out.println("Type \"Complete\" or \"Incomplete\"");
				task.setCompletion(scnr.nextLine());
				break;
			}
			goAgain = Validator.goAgain(scnr, "Need to make more changes to this task?");
		} while (goAgain);

		System.out.println("Success! Changes have been made:");
		System.out.println(task.toString());

	}
	
	public void sortList() {
		taskList.sort(null);
		displayTaskList();
	}
}
