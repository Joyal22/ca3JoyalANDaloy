package Utils;
import java.time.LocalDate;
public class Task {

    private String owner;
    private String description;
    private LocalDate deadline;

    public Task(String owner, String description, LocalDate deadline) {
        if (deadline.compareTo(LocalDate.now()) <= 0) {
            throw new IllegalArgumentException("Deadline cannot be before or equal to the current date");
        }
        this.owner = owner;
        this.description = description;
        this.deadline = deadline;
    }
}
