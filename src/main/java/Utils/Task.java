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
    public void setOwner(String owner) {
        this.owner = owner;
    }
        public void setDescription(String description) {
            this.description = description;
        }
    public void setDeadline(LocalDate deadline) {
        if (deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past.");
        }
        this.deadline = deadline;
    }

    }
