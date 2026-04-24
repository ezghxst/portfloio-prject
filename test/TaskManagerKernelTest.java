import static org.junit.Assert.*;
import org.junit.Test;

public class TaskManagerKernelTest {

    @Test
    public void testAddAndCount() {
        TaskManager1L m = new TaskManager1L();

        m.addTask("A");
        m.addTask("B");

        assertEquals(2, m.getTaskCount());
    }

    @Test
    public void testGetTaskName() {
        TaskManager1L m = new TaskManager1L();

        m.addTask("A");

        assertEquals("A", m.getTaskName(0));
    }

    @Test
    public void testCompleteTask() {
        TaskManager1L m = new TaskManager1L();

        m.addTask("A");
        m.completeTask(0);

        assertTrue(m.isTaskCompleted(0));
    }

    @Test
    public void testRemoveTask() {
        TaskManager1L m = new TaskManager1L();

        m.addTask("A");
        m.addTask("B");

        m.removeTask(0);

        assertEquals(1, m.getTaskCount());
        assertEquals("B", m.getTaskName(0));
    }
}