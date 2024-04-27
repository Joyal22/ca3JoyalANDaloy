package Utils;
import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private String owner;
    private String description;
    private LocalDate deadline;

    public Task(String owner, String description, LocalDate deadline) {
        if (deadline.compareTo(LocalDate.now()) <= 0) {
            throw new IllegalArgumentException("Deadline cannot be before or equal to the current date Please Insert New Date");
        }
        this.owner = owner;
        this.description = description;
        this.deadline = deadline;
    }

    // No argument constructor
    public Task() {
    }

    //Setters
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past.");
        }
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;}
        Task task = (Task) o;
        return Objects.equals(owner, task.owner) &&
                Objects.equals(description, task.description) &&
                Objects.equals(deadline, task.deadline);


    }
    @Override
    public int hashCode() {
        return Objects.hash(owner, description, deadline);
    }


}

