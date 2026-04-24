
public interface TaskManager extends TaskManagerKernel {

    /**
     * Returns a string representation of tasks.
     */
    @Override
    String toString();

    /**
     * Compares two TaskManagers.
     */
    @Override
    boolean equals(Object obj);
}