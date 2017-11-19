package wanroj.supanat.pomodoro_knight.Model;

/**
 * Created by mild supanat on 18/11/2560.
 */

public class TaskToDo {

    private static TaskToDo taskToDoInstance;
    private int id, target, workInterval;
    private String taskName;



    public static TaskToDo getTaskToDoInstance(){
        if(taskToDoInstance == null){
            taskToDoInstance = new TaskToDo();
        }
        return taskToDoInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWorkInterval() {
        return workInterval;
    }

    public void setWorkInterval(int workInterval) {
        this.workInterval = workInterval;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
