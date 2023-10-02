package com.taskmanagement;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("Task Management System");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task newTask = new Task(title, description);
                    taskManager.addTask(newTask);
                    System.out.println("Task added successfully.");
                    break;
                case 2:
                    List<Task> tasks = taskManager.getTasks();
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println("Task #" + i);
                        System.out.println(task);
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.print("Enter the index of the task to mark as complete: ");
                    int index = scanner.nextInt();
                    taskManager.markTaskComplete(index);
                    System.out.println("Task marked as complete.");
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
