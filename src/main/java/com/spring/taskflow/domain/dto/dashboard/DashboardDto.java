package com.spring.taskflow.domain.dto.dashboard;

public class DashboardDto {
    private int totalTask;
    private int doneTasks;
    private int isProgressTasks;
    private int todoTasks;
    private int overDueTasks;
    private double doneTasksRatio;

    public DashboardDto(int totalTask, int doneTasks, int isProgressTasks, int todoTasks, int overDueTasks, double doneTasksRatio) {
        this.totalTask = totalTask;
        this.doneTasks = doneTasks;
        this.isProgressTasks = isProgressTasks;
        this.todoTasks = todoTasks;
        this.overDueTasks = overDueTasks;
        this.doneTasksRatio = doneTasksRatio;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public int getDoneTasks() {
        return doneTasks;
    }

    public int getIsProgressTasks() {
        return isProgressTasks;
    }

    public int getTodoTasks() {
        return todoTasks;
    }

    public int getOverDueTasks() {
        return overDueTasks;
    }

    public double getDoneTasksRatio() {
        return doneTasksRatio;
    }
}
