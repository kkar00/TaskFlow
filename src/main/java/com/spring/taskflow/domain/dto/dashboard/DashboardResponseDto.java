package com.spring.taskflow.domain.dto.dashboard;

public class DashboardResponseDto {
    private long totalTask;
    private long doneTasks;
    private long inProgressTasks;
    private long todoTasks;
    private long overDueTasks;
    private double doneTasksRatio;

    public DashboardResponseDto(long totalTask, long doneTasks, long inProgressTasks, long todoTasks, long overDueTasks) {
        this.totalTask = totalTask;
        this.doneTasks = doneTasks;
        this.inProgressTasks = inProgressTasks;
        this.todoTasks = todoTasks;
        this.overDueTasks = overDueTasks;
        this.doneTasksRatio = (doneTasks / totalTask);
    }

    public long getTotalTask() {
        return totalTask;
    }

    public long getDoneTasks() {
        return doneTasks;
    }

    public long getInProgressTasks() {
        return inProgressTasks;
    }

    public long getTodoTasks() {
        return todoTasks;
    }

    public long getOverDueTasks() {
        return overDueTasks;
    }

    public double getDoneTasksRatio() {
        return doneTasksRatio;
    }
}
