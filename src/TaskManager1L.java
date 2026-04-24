import java.util.ArrayList;
/**
 * CONVENTION:
 * - tasks is an ArrayList of Task objects.
 * - Each Task stores a name and completion status.
 * - The index represents task order.
 *
 * CORRESPONDENCE:
 * - tasks.get(i).name is the name of task i
 * - tasks.get(i).completed is whether task i is complete
 */
public class TaskManager1L extends TaskManagerSecondary {

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
    public TaskManager1L() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(String task) {
        tasks.add(new Task(task));
    }

    @Override
   public void removeTask(int index) {
    tasks.remove(index);
}
    @Override
    public void completeTask(int index) {
        tasks.get(index).completed = true;
    }

    @Override
    public int getTaskCount() {
        return tasks.size();
    }

    @Override
    public String getTaskName(int index) {
        return tasks.get(index).name;
    }

    @Override
    public boolean isTaskCompleted(int index) {
        return tasks.get(index).completed;
    }
}