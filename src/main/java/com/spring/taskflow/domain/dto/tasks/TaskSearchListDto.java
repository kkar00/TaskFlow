package com.spring.taskflow.domain.dto.tasks;

import java.util.List;

public class TaskSearchListDto {
    private List<TaskSearchListResponseDto> SearchList;

    public TaskSearchListDto(List<TaskSearchListResponseDto> SearchList) {
        this.SearchList = SearchList;
    }

    public List<TaskSearchListResponseDto> getTaskSearchList() {
        return SearchList;
    }
}
