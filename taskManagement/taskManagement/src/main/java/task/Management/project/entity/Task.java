package task.Management.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
