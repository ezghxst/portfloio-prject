import components.standard.Standard;

/**
 * Kernel interface for TaskManager.
 */
public interface TaskManagerKernel extends Standard<TaskManager> {


   
    void addTask(String task);

   
    void removeTask(int index);

   
    void completeTask(int index);

    
    int getTaskCount();

    
    String getTaskName(int index);

    
    boolean isTaskCompleted(int index);
}