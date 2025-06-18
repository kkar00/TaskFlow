package com.spring.taskflow.domain.dto.tasks;

import java.util.List;

public class TaskListDto {
    private List<TaskListResponseDto> taskList;

    public TaskListDto(List<TaskListResponseDto> taskListResponseDto) {
        this.taskList = taskListResponseDto;
    }

    public List<TaskListResponseDto> getTaskListDto() {
        return taskList;
    }
}
