package Business;

import Utils.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final int MAX_QUEUE_SIZE = 20;

    private Queue<Task> taskQueue;
    private Random random;

    public App() {
        taskQueue = new LinkedList<>();
        random = new Random();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        boolean useRiskyApproach = random.nextBoolean();
        System.out.println("Using " + (useRiskyApproach ? "risky" : "safe") + " approach.");

        System.out.print("Enter the size of the task queue: ");
        int queueSize = scanner.nextInt();
        scanner.nextLine();

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new Task");
            System.out.println("2. View the next Task");
            System.out.println("3. Mark the next Task as done");
            System.out.println("4. View the number of Tasks remaining");

            System.out.println("5. View the space remaining in the queue");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

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
    }
}


