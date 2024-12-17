package com.taskSync.TaskSync_backend.entity;

import com.taskSync.TaskSync_backend.enums.TypeStatus;
import com.taskSync.TaskSync_backend.enums.TypeTags;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TypeTags tags;
    private TypeStatus Status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(int id, String title, String description, LocalDate dueDate, TypeTags tags, TypeStatus status, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.tags = tags;
        Status = status;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TypeTags getTags() {
        return tags;
    }

    public void setTags(TypeTags tags) {
        this.tags = tags;
    }

    public TypeStatus getStatus() {
        return Status;
    }

    public void setStatus(TypeStatus status) {
        Status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
