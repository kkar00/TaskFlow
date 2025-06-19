package com.spring.taskflow.domain.dto.tasks;

import com.spring.taskflow.domain.enumdata.Status;

public class TaskUpdateStatusRequestDto {
    private Status status;

    public Status getStatus() {
        return status;
    }
}
