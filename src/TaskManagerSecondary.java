public abstract class TaskManagerSecondary implements TaskManager {

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = this.getTaskCount();

        for (int i = 0; i < count; i++) {
            boolean completed = this.isTaskCompleted(i);
            String name = this.getTaskName(i);

            String status = completed ? "[✔]" : "[ ]";
            result.append(i + ": " + status + " " + name + "\n");
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof TaskManager)) {
            return false;
        }

        TaskManager other = (TaskManager) obj;

        if (this.getTaskCount() != other.getTaskCount()) {
            return false;
        }

        for (int i = 0; i < this.getTaskCount(); i++) {
            if (this.isTaskCompleted(i) != other.isTaskCompleted(i)) {
                return false;
            }
            if (!this.getTaskName(i).equals(other.getTaskName(i))) {
                return false;
            }
        }

        return true;
    }
}