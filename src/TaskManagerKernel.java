import components.standard.Standard;

public interface TaskManagerKernel extends Standard<TaskManager> {

    void addTask(String task);

    String removeTask();

    boolean isEmpty();
}