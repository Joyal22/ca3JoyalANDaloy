package Utils;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task> {
    private String owner;
    private String description;
    private LocalDate deadline;
    /**
     * Create a new Task with  owner, description, and deadline.
     *
     * @param owner      The owner of the task.
     * @param description A brief description of the task.
     * @param deadline   The deadline for completing the task (must be in the future).
     * @throws IllegalArgumentException if the deadline is before or equal to the current date.
     */
    public Task(String owner, String description, LocalDate deadline) {
        if (!deadline.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be before or equal to the current date Please Insert New Date");
        }
        this.owner = owner;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * No-argument constructor for creating an empty Task.
     */
    public Task() {
    }

    /**
     * Gets the owner of the task.
     * @return The owner's name.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the task.
     * @param owner The owner's name.
     */

    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the of the task.
     * @return The task.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the Deadline of the task.
     * @return The deadline.
     */
    public LocalDate getDeadline() {
        return deadline;
    }
    /**
     * Sets the deadline for completing the task.
     * @param deadline The task deadline.
     * @throws IllegalArgumentException if the deadline is in the past.
     */
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
    /**
     * Compares this Task with another Task based on their deadlines.
     * @param other The Task to compare with.
     * @return a negative integer, zero, or a positive integer as this Task's deadline is earlier than, equal to, or later than
     *         the specified Task's deadline.
     */
    @Override
    public int compareTo(Task other) {
        return this.deadline.compareTo(other.deadline);
    }
    /**
     * Generates a hash code for this Task based on its owner, description, and deadline.
     * @return The hash code.
     */

    @Override
    public int hashCode() {
        return Objects.hash(owner, description, deadline);
    }
    /**
     * Returns the task to a string.
     * @return string containing owner, description, and deadline.
     */
    @Override
    public String toString() {
        return "Task{" +
                "owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }



}

