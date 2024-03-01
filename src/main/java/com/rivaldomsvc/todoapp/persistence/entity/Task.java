package com.rivaldomsvc.todoapp.persistence.entity;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String title;
    private String description;
    private LocalDateTime createdDate;
    private  LocalDateTime eta;
    private boolean finished;
    private TaskStatus taskStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEta() {
        return eta;
    }

    public void setEta(LocalDateTime eta) {
        this.eta = eta;
    }

    public void setCreatedDate(LocalDateTime now) {
    }

    public void setFinished(boolean b) {
    }

    public void setTaskStatus(TaskStatus taskStatus) {
    }

}
