package com.spring.taskflow.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="dashboards")
@EntityListeners(AuditingEntityListener.class)
public class Dashboard {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "total_task", nullable = false)
    private int totalTask;

    @Column(name = "done_tasks", nullable = false)
    private int doneTasks;

    @Column(name = "in_progress_tasks", nullable = false)
    private int inProgressTasks;

    @Column(name = "todo_tasks", nullable = false)
    private int todoTasks;

    @Column(name = "overdue_tasks", nullable = false)
    private int overdueTasks;

    @Column(name = "done_tasks_ratio", nullable = false)
    private double doneTasksRatio;


    // 생성자
    /**
     * 기본생성자
     */
    public Dashboard() {}

    public Dashboard(Long userId,
                     int totalTask,
                     int doneTasks,
                     int inProgressTasks,
                     int todoTasks,
                     int overdueTasks,
                     double doneTasksRatio) {
        this.userId = userId;
        this.totalTask = totalTask;
        this.doneTasks = doneTasks;
        this.inProgressTasks = inProgressTasks;
        this.todoTasks = todoTasks;
        this.overdueTasks = overdueTasks;
        this.doneTasksRatio = doneTasksRatio;
    }


    // 기능
}
