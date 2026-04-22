public interface TaskManagerInterface extends TaskManagerKernel {

    int size();

    boolean contains(String task);

    void clear();
}