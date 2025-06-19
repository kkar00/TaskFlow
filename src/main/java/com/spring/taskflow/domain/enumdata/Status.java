package com.spring.taskflow.domain.enumdata;

public enum Status {
    TODO,
    IN_PROGRESS,
    DONE;

    public boolean transitionTo(Status newStatus) {
        if (this == TODO && newStatus == IN_PROGRESS) {
            return true;
        }
        if (this == IN_PROGRESS && newStatus == DONE) {
            return true;
        }
        return false;
    }
}
