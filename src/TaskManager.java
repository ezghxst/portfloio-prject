import java.util.ArrayList;

/**
 * A simple TaskManager proof-of-concept.
 */
public class TaskManager {

    /**
     * Inner class representing a Task.
     */
    private class Task {
        String name;
        boolean completed;

        Task(String name) {
            this.name = name;
            this.completed = false;
        }
    }

    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task.
     */
    public void addTask(String name) {
        tasks.add(new Task(name));
    }

    /**
     * Removes a task by index.
     */
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    /**
     * Marks a task as completed.
     */
    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).completed = true;
        }
    }

    /**
     * Displays all tasks.
     */
    public void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String status = t.completed ? "[✔]" : "[ ]";
            System.out.println(i + ": " + status + " " + t.name);
        }
    }

    /**
     * Returns number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Main method demonstrating functionality.
     */
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Add tasks
        manager.addTask("Finish homework");
        manager.addTask("Go to the gym");
        manager.addTask("Study for exam");

        System.out.println("Initial Tasks:");
        manager.displayTasks();

        // Complete a task
        manager.completeTask(0);

        System.out.println("\nAfter completing first task:");
        manager.displayTasks();

        // Remove a task
        manager.removeTask(1);

        System.out.println("\nAfter removing second task:");
        manager.displayTasks();

        // Show task count
        System.out.println("\nTotal tasks: " + manager.getTaskCount());
    }
}