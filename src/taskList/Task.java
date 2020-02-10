package taskList;
/**
 * @author beanmccarthy
 */
import java.time.*;

public class Task implements Comparable<Task> {
//	DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
//	String formattedDate = dateFormatter.format(LocalDate.now());
	
	//variables here
	private String teamMember;
	private String description;
	private LocalDate dueDate;
	private String completion = "Incomplete";
	
	//constructor
	public Task(String teamMember, String description, LocalDate dueDate) {
		this.teamMember = teamMember;
		this.description = description;
		this.dueDate = dueDate;
	}
	//getters
	public String getTeamMember() {
		return teamMember;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getCompletion() {
		return completion;
	}
	//setters
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}
	@Override
	public String toString() {
		return 	String.format("| %-15s| %-25s| %-15s| %-15s|\n", teamMember,
				description, String.valueOf(dueDate), completion);
	}
	
	@Override
	public int compareTo(Task task2) {
		int comparisonVal;
		comparisonVal = dueDate.compareTo(task2.getDueDate());
		return comparisonVal;
	}
	
	
	
	
	
}
