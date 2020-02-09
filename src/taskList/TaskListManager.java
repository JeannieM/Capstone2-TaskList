package taskList;

import java.time.LocalDate;
import java.util.*;

public class TaskListManager {
	public List<Task> taskList = new ArrayList<Task>(
			//I initiated my list with a few tasks for easier testing
			Arrays.asList((new Task("Kiera", "Wash Dishes", LocalDate.parse("2020-02-10"))),
					(new Task("Bonnie", "Do Laundry", LocalDate.parse("2020-02-12"))),
					(new Task("Haley", "Dust", LocalDate.parse("2020-02-15")))));

	// display the menu of options to the user
	public void displayMenu() {
		System.out.printf("" + "     1. | Display Task List\n" + "     2. | Add Task\n" + "     3. | Remove Task\n"
				+ "     4. | Mark Task Complete\n" + "     5. | Quit\n\n");
	}

	// add a task to the task list
	public void addTask(Scanner scnr) {
		String teamMember;
		String description;
		LocalDate dueDate;
		
		System.out.println("ADD TASK");
		teamMember = Validator.getString(scnr, "Team Member: ");
		description = Validator.getString(scnr, "Task Description: ");
		dueDate = Validator.getDate(scnr, "Enter a due date: ");
		taskList.add(new Task(teamMember, description, dueDate));
		System.out.println("Success! Task has been added!");
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
				}else {
					System.out.println(
							" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \n");
				}
				i++;
			}
		}
	}

	@SuppressWarnings("resource")
	public void removeTask(int taskNum) throws Exception {
		Scanner scnr = new Scanner(System.in);
		if (taskNum <= taskList.size() && taskNum > 0) {
			if (Validator.yesOrNo(scnr, "Are you sure?").equals("yes")) {
				taskList.remove(taskNum - 1);
				System.out.println("Success! Task has been removed!");
				displayTaskList();
			}
		} else {
			throw new Exception("You entered a number outside the range of tasks. Please try again.");
		}
	}

	public void completeTask(int taskNum) throws Exception {
		
		if (taskNum <= taskList.size() && taskNum > 0) {
			Task thisTask = taskList.get(taskNum - 1);
			thisTask.setCompletion("Complete");
			System.out.println("Success! Task has been marked complete!");
			displayTaskList();
		} else {
			throw new Exception("You entered a number outside the range of tasks. Please try again.");
		}
	}
}
