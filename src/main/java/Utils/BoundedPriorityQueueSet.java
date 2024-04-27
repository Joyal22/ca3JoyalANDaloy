package Utils;

import java.util.LinkedList;

public class BoundedPriorityQueueSet {
    private LinkedList<Task> queue;
    private int maxSize;


    /**
     * Default constructor create a queue with maximum size of 10.
     */
    public BoundedPriorityQueueSet() {

        this(10);
    }


    /**
     *  constructor with parameter allows setting a custom maximum size.
     *
     * @param maxSize The maximum size of the queue.
     * @throws IllegalArgumentException if maxSize is less than or equal to 0.
     */
    public BoundedPriorityQueueSet(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("MAX SIZE MUST BE MORE THAN ZERO");
        }
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }


    /**
     * Returns the current size of the queue.
     * @return The size of the queue.
     */
    public int size() {

        return queue.size();
    }


    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {

        return queue.isEmpty();
    }


    /**
     * Checks if the queue is full
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {

        return queue.size() >= maxSize;
    }

    /**
     * Calculates the position where a new Task should be inserted based on its priority.
     * Handles duplicate elements and throws a DuplicateElementException if a duplicate is found.
     *
     * @param task The Task to be inserted.
     * @return The position where the Task should be inserted.
     * @throws DuplicateElementException if a duplicate Task is found.
     */
    private int calcPosition(Task task) throws DuplicateElementException {
        for (int i = 0; i < this.queue.size(); i++) {
            if (this.queue.get(i).equals(task)) {
                throw new DuplicateElementException("Duplicate element found");
            }
            if (this.queue.get(i).compareTo(task) > 0) {
                return i;
            }
        }
        return this.queue.size();
    }


}
