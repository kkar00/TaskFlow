package com.spring.taskflow.domain.dto.tasks;

import java.util.List;

public class TaskList {
    private List<TaskListDto> taskList;

    public TaskList(List<TaskListDto> taskListDto) {
        this.taskList = taskListDto;
    }

    public List<TaskListDto> getTaskListDto() {
        return taskList;
    }
}
