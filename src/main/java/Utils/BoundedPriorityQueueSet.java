package Utils;

import java.util.LinkedList;
import java.util.NoSuchElementException;

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

    /**
     * Inserts  a Task to the queue based on its priority. Checks
     * if the queue is full and throws an IllegalStateException if it is
     * if duplicate elements are found, throws a DuplicateElementException.
     *
     * @param task The Task to be added
     * @throws IllegalStateException if the queue is full
     * @throws DuplicateElementException if the Task is redundant
     */
    public void add(Task task) throws DuplicateElementException {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        int position = calcPosition(task);
        queue.add(position, task);
    }

    /**
     * Inserts  a Task to the queue based on its priority.
     * Checks if the queue is full or duplicate elems are found and returns false if it is
     *
     *
     * @param task The Task to be added.
     * @return true if the Task could be added, else false
     */
    public boolean offer(Task task) {
        if (isFull() || queue.contains(task)) {
            return false;
        }
        try {
            int position = calcPosition(task);
            queue.add(position, task);
            return true;
        } catch (DuplicateElementException e) {
            return false;
        }
    }

    /**
     * Gets a copy of the first Task in the queue
     * throws no exception if the queue is empty
     *
     * @return The first Task in the queue
     * @throws NoSuchElementException if the queue is empty
     */

    public Task element() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return queue.getFirst();
    }
    /**
     * returns a copy of the first Task in the queue
     * throws no exception
     *
     * @return The first Task in the queue, or null if the queue is empty
     */
    public Task peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }

    /**
     * Deletes the first Task in the queue and returns it as a task object
     * throws exception if the queue is empty
     *
     * @return The first Task in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Task remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.removeFirst();
    }
    /**
     * Deletes the first Task in queue and returns it as a task object
     * throws no exception if the queue is empty
     *
     * @return The first Task in the queue, or null if the queue is empty
     */

    public Task poll() {
        if (isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }



}
