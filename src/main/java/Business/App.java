package Business;

import Utils.BoundedPriorityQueueSet;
import Utils.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/*
 * The application class is the main class that runs the menue driven application
 * @author aloysius and joyal
 * @version 1.0
 */

public class App {
    private BoundedPriorityQueueSet taskQueue;
    private Random random;

    /*
     * constructor initializes the taskQueue and random objects
     */
    public App() {
        taskQueue = new BoundedPriorityQueueSet(10);
        random = new Random();
    }

    /*
     * Runs the menu driven application contains menue itself and the logic to handle the user input and other processes
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        boolean safemthd = random.nextBoolean();

        System.out.print("Enter the size of the task queue: ");
        int queueSize = sc.nextInt();
        sc.nextLine();

//prints the menu
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new Task");
            System.out.println("2. View the next Task");
            System.out.println("3. Mark the next Task as done");
            System.out.println("4. View the number of Tasks remaining");

            System.out.println("5. View the space remaining in the queue");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            //switch case to handle the user input
            switch (choice) {
                case 1:
                    addTask(sc,safemthd);
                    break;
                case 2:
                    viewNxtTask(safemthd);
                    break;
                case 3:
                    markTaskAsDone(safemthd);
                    break;

                case 4:
                    System.out.println("Tasks remaining: " + taskQueue.size());
                    break;
                case 5:
                    System.out.println("Space remaining in queue: " + (queueSize - taskQueue.size()));
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        saveTasksToFile();
        guessYourFate(sc,safemthd);
        sc.close();

    }
    /*
     * Adds a new task to the queue.based on wether the safe or risky strategy is used
     * @param sc The scanner object used to read input
     * @param safeMthd  boolean value indicating whether the safe strategy is used
     */
    private void addTask(Scanner sc,boolean safeMthd) {
        // ask the user for task details
        System.out.print("Enter the owner of the task: ");
        String owner = sc.nextLine();
        System.out.print("Enter the description of the task: ");
        String description = sc.nextLine();
        System.out.print("Enter the deadline YYYY-MM-DD: ");
        String deadlineStr = sc.nextLine();

        try {
            LocalDate deadline = LocalDate.parse(deadlineStr);
            //creates a new task object and add it to the queue

            Task task = new Task(owner, description, deadline);
            //adds the task to the queue based on the risky or safe strategy
            if (safeMthd) {
                taskQueue.offer(task);
            } else {
                taskQueue.add(task);
            }
            System.out.println("Task added ");

        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }
    /*
     * prints out the next task in the queue.based on whether the safe or risky strategy is used
     * if no tasks are in the queue, a message is printed making the user aware
     * @param safeMthd  boolean value indicating whether the safe strategy is used
     *
     */
    private void viewNxtTask(boolean safeMthd) {
        //
        Task task = null;
        //gets the next task in the queue based on the safe or risky strategy
        if (safeMthd) {
            task = taskQueue.peek();
        } else {
            try {
                task = taskQueue.element();
            } catch (NoSuchElementException e) {
                System.out.println("No tasks in the queue.");
                return;
            }
        }
        //if tasks available. print the next task
        if (task != null) {
            System.out.println("Next task: " + task);
        } else {
            System.out.println("No tasks in the queue.");
        }
    }
    /*
     * Marks the next task in the queue as done.based on whether the safe or risky strategy is used
     * if no tasks are in the queue. a message is printed making the user aware
     * tasks are removed from the queue and printed out
     *
     * @param safeMthd  boolean value indicating whether the safe strategy is used
     *
     */
    private void markTaskAsDone(boolean safeMthd) {
        Task removed = null;

        //removes the next task in the queue based on the safe or risky strategy
        if (safeMthd) {
            removed = taskQueue.poll();
        } else {
            try {
                removed = taskQueue.remove();
            } catch (NoSuchElementException e) {
                System.out.println("No tasks in the queue.");
                return;
            }
        }
        //if tasks available. print the next task
        if (removed != null) {
            System.out.println("Completed task: " + removed);
            if (taskQueue.isEmpty()) {
                System.out.println("Congratulations! All tasks are completed.");
            }
        } else {
            System.out.println("No tasks in the queue.");
        }
    }
    /*
     * Saves all tasks in the queue to a file named tasks.txt
     */
    private void saveTasksToFile() {
        String fileName = "tasks.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            //for each task in the queue.  write the task to the file
            for (Task task : taskQueue.getQueue()) {
                writer.write(task.toString() + "\n");
            }
            System.out.println("All tasks successfully accounted for " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to save tasks Xp : " + e.getMessage());
        }
    }

    /*
     * Asks the user to guess whether the safe or risky strategy was used
     * then heralds the user their luck in life based on their guess
     *
     * @param sc The scanner object used to read input
     * @param safeMthd  boolean value indicating whether the safe strategy is used
     */
    private void guessYourFate(Scanner sc, boolean safeMthd) {
        System.out.print("Guess the approach used 'risky' or 'safe' : ");
        while (true) {
            String guess = sc.nextLine();
            if (guess.equalsIgnoreCase("risky") && !safeMthd) {
                System.out.println("What can I say, you are having an awesome day");
                break;
            } else if (guess.equalsIgnoreCase("safe") && safeMthd) {
                System.out.println("What can I say, you are having an awesome day");
                break;
            } else {
                System.out.println("Please enter a correct option 'risky' or 'safe' : ");
            }
        }
    }

    /*
     * Main method to run the application
     */
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}


